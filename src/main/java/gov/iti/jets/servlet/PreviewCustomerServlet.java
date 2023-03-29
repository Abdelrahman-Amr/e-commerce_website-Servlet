package gov.iti.jets.servlet;

import com.google.gson.Gson;
import gov.iti.jets.dto.CustomerDto;
import gov.iti.jets.dto.CustomerTable;
import gov.iti.jets.service.CustomerService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

public class PreviewCustomerServlet extends HttpServlet {

    CustomerService customerService;

    @Override
    public void init()
    {
        customerService = CustomerService.getInstance();

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession httpSession = request.getSession(false);

        RequestDispatcher rd = request.getRequestDispatcher("/views/header.jsp");
        rd.include(request, response);
        int pageNum = (int)(Math.ceil(customerService.getRecordsCount()/10f));
        if(pageNum==0)
        {
            pageNum = 1;
        }

        List<CustomerDto>  customerList = customerService.getCustomerList(1);
        httpSession.setAttribute("customerList",customerList);
        httpSession.setAttribute("CustomerPageNo",1);
        //request.getSession(false).setAttribute("startInd",0);
        httpSession.setAttribute("customerTotalPage",pageNum);
        rd = request.getRequestDispatcher("/views/previewCustomers.jsp");
        rd.include(request, response);

        rd = request.getRequestDispatcher("/views/footer.jsp");
        rd.include(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<CustomerDto> customerList;

        HttpSession httpSession = request.getSession(false);
        int pageNum = (int) (Math.ceil(customerService.getRecordsCount() / 10f));

        if (pageNum == 1) {
            response.getWriter().write("1");
        } else {

            int pageNo = (Integer) (httpSession.getAttribute("CustomerPageNo"));

            if (request.getParameter("goal").equals("next")) {
                pageNo++;
                if (pageNo > pageNum) {
                    pageNo = pageNum;
                }
            } else {

                pageNo--;
                if (pageNo == 0) pageNo = 1;
            }
            httpSession.setAttribute("CustomerPageNo", pageNo);

            customerList = customerService.getCustomerList(pageNo);

            httpSession.setAttribute("customerList", customerList);

            CustomerTable customerTable = new CustomerTable(customerList, pageNo, pageNum);
            Gson gson = new Gson();
            String json = gson.toJson(customerTable);
            response.getWriter().write(json); //1:5
        }
    }
}
