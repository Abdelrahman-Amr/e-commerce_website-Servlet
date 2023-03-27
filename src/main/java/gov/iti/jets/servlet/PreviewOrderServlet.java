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
        //super.doGet(req, resp);
        //System.out.println("get");

        RequestDispatcher rd = request.getRequestDispatcher("/views/header.jsp");
        rd.include(request, response);

        int totalPages = (int)(Math.ceil(orderMasterService.getRecordsCount()/10f));
        if(totalPages==0)
        {
            totalPages = 1;
        }

        List<OrderMaster>  orderList = orderMasterService.getOrderList(1);

        request.getSession(false).setAttribute("orderList",orderList);
        request.getSession(false).setAttribute("pageOrderNo",1);
        request.setAttribute("totalOrderPages",totalPages);

        rd = request.getRequestDispatcher("/views/reviewOrder.jsp");
        rd.include(request, response);
        //System.out.println("after body");

        rd = request.getRequestDispatcher("/views/footer.jsp");
        rd.include(request, response);
        //System.out.println("after footer");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<OrderMaster>  orderList;

        int pageNum = (int)(Math.ceil(orderMasterService.getRecordsCount()/10f));

        //System.out.println(request.getParameter("goal"));
        int pageNo = (Integer) (request.getSession(false).getAttribute("pageOrderNo"));

        if(request.getParameter("orderGoal").equals("next")) {
            pageNo++;
            if(pageNo>pageNum) {
                pageNo = pageNum;
            }
        } else {

            pageNo--;
            if(pageNo==0) pageNo=1;
        }
        request.getSession(false).setAttribute("pageOrderNo", pageNo );
        //System.out.println("paggggge " + pageNo);
        orderList = orderMasterService.getOrderList(pageNo);
        orderList.forEach((c) -> System.out.println(c.getId()));
        request.getSession(false).setAttribute("orderList", orderList);
        //response.getWriter().write(pageNo+" of "+pageNum); //1:5
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
                orderMaster.getCust().getEmail(),orderMaster.getDate() ,orderMaster.getTotal());
    }
}
