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
        //super.doGet(req, resp);
        //System.out.println("get");

        RequestDispatcher rd = request.getRequestDispatcher("/views/header.jsp");
        rd.include(request, response);
        //System.out.println("after header");
        int pageNum = (int)(Math.ceil(customerService.getRecordsCount()/10f));
        if(pageNum==0)
        {
            pageNum = 1;
        }

        List<CustomerDto>  customerList = customerService.getCustomerList(1);
        request.getSession(false).setAttribute("customerList",customerList);
        request.getSession(false).setAttribute("CustomerPageNo",1);
        //request.getSession(false).setAttribute("startInd",0);
        request.setAttribute("customerTotalPage",pageNum);
        rd = request.getRequestDispatcher("/views/previewCustomers.jsp");
        rd.include(request, response);
        //System.out.println("after body");

        rd = request.getRequestDispatcher("/views/footer.jsp");
        rd.include(request, response);
        //System.out.println("after footer");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<CustomerDto> customerList;

        int pageNum = (int) (Math.ceil(customerService.getRecordsCount() / 10f));

        if (pageNum == 1) {
            response.getWriter().write("1");
        } else {

            //System.out.println(request.getParameter("goal"));
            int pageNo = (Integer) (request.getSession(false).getAttribute("CustomerPageNo"));

            if (request.getParameter("goal").equals("next")) {
                pageNo++;
                if (pageNo > pageNum) {
                    pageNo = pageNum;
                }
            } else {

                pageNo--;
                if (pageNo == 0) pageNo = 1;
            }
            request.getSession(false).setAttribute("CustomerPageNo", pageNo);
            //System.out.println("paggggge " + pageNo);
            customerList = customerService.getCustomerList(pageNo);
            customerList.forEach((c) -> System.out.println(c.getEmail()));
            request.getSession(false).setAttribute("customerList", customerList);
            //response.getWriter().write(pageNo+" of "+pageNum); //1:5
            CustomerTable customerTable = new CustomerTable(customerList, pageNo, pageNum);
            Gson gson = new Gson();
            String json = gson.toJson(customerTable);
            System.out.println(json);
            response.getWriter().write(json); //1:5
        }
    }
}
