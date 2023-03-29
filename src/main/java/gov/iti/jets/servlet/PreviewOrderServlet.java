package gov.iti.jets.servlet;

import com.google.gson.Gson;
import gov.iti.jets.dto.OrderMasterTableDto;
import gov.iti.jets.dto.OrderTable;
import gov.iti.jets.entity.OrderMaster;
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

public class PreviewOrderServlet extends HttpServlet {

    OrderMasterService orderMasterService;

    @Override
    public void init()
    {
        orderMasterService = OrderMasterService.getInstance();

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        request.getSession(false).setAttribute("filter",false);

        HttpSession httpSession = request.getSession(false);

        RequestDispatcher rd = request.getRequestDispatcher("/views/header.jsp");
        rd.include(request, response);

        int totalPages = (int)(Math.ceil(orderMasterService.getRecordsCount()/10f));
        if(totalPages==0)
        {
            totalPages = 1;
        }

        int pageNo = 1;

//        if(request.getSession(false).getAttribute("pageOrderNo") != null) {
//            pageNo = (Integer) request.getSession(false).getAttribute("pageOrderNo");
//        }

        List<OrderMaster>  orderList = orderMasterService.getOrderList(pageNo);

        httpSession.setAttribute("orderList",orderList);
        httpSession.setAttribute("pageOrderNo",pageNo);
        httpSession.setAttribute("totalOrderPages",totalPages);

        rd = request.getRequestDispatcher("/views/reviewOrder.jsp");
        rd.include(request, response);
        //System.out.println("after body");

        rd = request.getRequestDispatcher("/views/footer.jsp");
        rd.include(request, response);
        //System.out.println("after footer");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession httpSession = request.getSession(false);

        List<OrderMaster>  orderList;

        int pageNum = (int)(Math.ceil(orderMasterService.getRecordsCount()/10f));

        int pageNo = (Integer) (request.getSession(false).getAttribute("pageOrderNo"));

        if(request.getParameter("orderGoal") !=null) {
            if(request.getParameter("orderGoal").equals("next")) {
                pageNo++;
                if(pageNo>pageNum) {
                    pageNo = pageNum;
                }
            } else {

                pageNo--;
                if(pageNo==0) pageNo=1;
            }
        }

        orderList = orderMasterService.getOrderList(pageNo);

//        if(request.getParameter("filter") != null) {
//            pageNo = 1;
//            request.getSession(false).setAttribute("filter",true);
//            String customerEmail = request.getParameter("filter");
//            request.getSession(false).setAttribute("customerEmailOrder",customerEmail);
//            orderList = orderList.stream().filter((o)->
//                o.getCust().getEmail().equals(customerEmail)
//
//                ).toList();
//        } else if((boolean)request.getSession(false).getAttribute("filter")) {
//            String customerEmail = (String) request.getSession().getAttribute("customerEmailOrder");
//            orderList = orderList.stream().filter((o)->
//                    o.getCust().getEmail().equals(customerEmail)
//                    ).toList();
//        }
//        pageNum = (int)(Math.ceil(orderList.size()/10f));

        httpSession.setAttribute("pageOrderNo", pageNo );

        List<OrderMasterTableDto> orderDtoList = new ArrayList<>();
        orderList.forEach((o)->{
            OrderMasterTableDto orderMasterTableDto = toOrderMasterTableDto(o);
            orderDtoList.add(orderMasterTableDto);
        });
        OrderTable orderTable = new OrderTable(orderDtoList,pageNo,pageNum);
       Gson gson = new Gson();
        String json = gson.toJson(orderTable);
//        System.out.println(json);
        response.getWriter().write(json); //1:5
    }

    OrderMasterTableDto toOrderMasterTableDto(OrderMaster orderMaster) {
        return new OrderMasterTableDto(orderMaster.getId(),
                orderMaster.getCust().getEmail(), orderMaster.getCust().getAddress() , orderMaster.getDate() ,orderMaster.getTotal());
    }
}
