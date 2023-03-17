package gov.iti.jets.servlet;

import gov.iti.jets.dto.CustomerDto;
import gov.iti.jets.service.CustomerService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PreviewCustomerServlet extends HttpServlet {

    CustomerService customerService;
    List<CustomerDto> customerList;
    @Override
    public void init()
    {
        customerService = CustomerService.getInstance();
        customerList = new ArrayList<>();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //super.doGet(req, resp);
        //System.out.println("get");
        RequestDispatcher rd = request.getRequestDispatcher("/views/header.jsp");
        rd.include(request, response);
        //System.out.println("after header");
        customerList.clear();
        customerList = customerService.getCustomerList(1);
        request.getSession(false).setAttribute("customerList",customerList);
        request.getSession(false).setAttribute("pageNo",1);
        //request.getSession(false).setAttribute("startInd",0);

        rd = request.getRequestDispatcher("/views/previewCustomers.jsp");
        rd.include(request, response);
        //System.out.println("after body");

        rd = request.getRequestDispatcher("/views/footer.jsp");
        rd.include(request, response);
        //System.out.println("after footer");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        customerList.clear();
//        int startInd=0;
//        System.out.println(request.getSession(false).getAttribute("startInd"));
//        if(request.getSession(false).getAttribute("startInd")!=null) {
//            System.out.println("stating index null");
//            startInd = (Integer) request.getSession(false).getAttribute("startInd");
//        }
//        System.out.println(startInd);
        if(request.getParameter("goal")=="next") {
            int pageNo = (Integer) (request.getSession(false).getAttribute("pageNo"));
            request.getSession(false).setAttribute("pageNo", pageNo + 1);
            System.out.println("paggggge " + pageNo);
            customerList = customerService.getCustomerList(pageNo + 1);
            customerList.forEach((c) -> System.out.println(c.getEmail()));
            request.getSession(false).setAttribute("customerList", customerList);
            response.getWriter().write("1"); //1:5
        } else {
            response.getWriter().write("0");
        }
    }
}
