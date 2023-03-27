package gov.iti.jets.servlet;

import com.google.gson.Gson;
import gov.iti.jets.dto.OrderDetailsTable;
import gov.iti.jets.dto.OrderDetailsTableDto;
import gov.iti.jets.entity.OrderDetail;
import gov.iti.jets.entity.OrderMaster;
import gov.iti.jets.service.OrderDetailService;
import gov.iti.jets.service.OrderMasterService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailsServlet extends HttpServlet {

    OrderMasterService orderMasterService;

    OrderDetailService orderDetailService;

    @Override
    public void init()
    {
        orderMasterService = OrderMasterService.getInstance();
        orderDetailService = OrderDetailService.getInstance();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //disabled
        Long orderId = Long.parseLong(req.getParameter("orderId")); // 13
        System.out.println("order details id "+orderId);

        OrderMaster orderMaster = orderMasterService.getOrderMasterById(orderId);

        List<OrderDetail> orderDetailList = orderMaster.getOrderDetails();
        System.out.println("details order list size " + orderDetailList.size()); //13

        int totalPages = (int)(Math.ceil(orderDetailList.size()/10f)); // 2
        System.out.println("total page before " +totalPages);
        if(totalPages==0)
        {
            totalPages = 1;
        }
        if(orderDetailList.size()>10) {
            orderDetailList = orderDetailList.subList(0,10); // [0-9]
        }

        String status ="";
        if(totalPages>1) status = "disabled";

        System.out.println("total page after " + totalPages);
        req.getSession(false).setAttribute("totalOrderDetailPages",totalPages);
        req.getSession(false).setAttribute("status",status);
        req.getSession(false).setAttribute("orderId",orderId);
        req.getSession(false).setAttribute("pageOrderDetailNo",1);
        req.getSession(false).setAttribute("orderDetailList",orderDetailList);

        RequestDispatcher rd = req.getRequestDispatcher("/views/header.jsp");
        rd.include(req, resp);

        rd = req.getRequestDispatcher("/views/reviewOrderDetails.jsp");
        rd.include(req, resp);

        rd = req.getRequestDispatcher("/views/footer.jsp");
        rd.include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession httpSession = req.getSession(false);

//        int totalPage = (Integer)httpSession.getAttribute("totalOrderDetailPages");

        Long orderId = (Long) httpSession.getAttribute("orderId"); // 13

        OrderMaster orderMaster = orderMasterService.getOrderMasterById(orderId);

        List<OrderDetail> orderDetailList = orderMaster.getOrderDetails(); // 13

        int totalPage = (int)(Math.ceil(orderDetailList.size()/10f)); // 2
        System.out.println("total page " + totalPage);

        if(totalPage==1) {
            resp.getWriter().write("1");
        } else {

            int pageNo = (Integer) (httpSession.getAttribute("pageOrderDetailNo")); // 1
            System.out.println("page no " + pageNo);

            if (req.getParameter("orderDetailGoal").equals("next")) {
                pageNo++; // 2
                if (pageNo > totalPage) {
                    pageNo = totalPage;
                }
            } else {
                pageNo--;
                if (pageNo == 0) pageNo = 1;
            }  // 1 2 3 4 5 6 7 8 9 10 11 12 13
            // [0-9]  13 - 10 = 3

            httpSession.setAttribute("pageOrderDetailNo", pageNo); // 2

            int restOfElement = orderDetailList.size() - ((pageNo - 1) * 10);

            if (restOfElement > 10) restOfElement = 10;

            orderDetailList = orderDetailList.subList((pageNo - 1) * 10, ((pageNo - 1) * 10) + restOfElement);

            List<OrderDetailsTableDto> orderDetailDtoList = new ArrayList<>();

            orderDetailList.forEach((o) ->
                orderDetailDtoList.add(toOrderDetailsTableDto(o))
            );

            OrderDetailsTable orderDetailsTable = new OrderDetailsTable(orderDetailDtoList, pageNo, totalPage);

            Gson gson = new Gson();
            String json = gson.toJson(orderDetailsTable);
            System.out.println(json);
            resp.getWriter().write(json); //1:5
        }
    }

    OrderDetailsTableDto toOrderDetailsTableDto(OrderDetail o) {
        return  new OrderDetailsTableDto(o.getProduct().getName(), o.getProduct().getCatg().getName(),
                o.getSize(), o.getQuantity(), o.getPrice());
    }
}
