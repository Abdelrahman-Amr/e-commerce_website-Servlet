package gov.iti.jets.servlet;


import com.google.gson.Gson;
import gov.iti.jets.dto.CategoryDto;
import gov.iti.jets.dto.ProductDto;
import gov.iti.jets.service.CategoryService;
import gov.iti.jets.service.ProductService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class HomeServlet extends HttpServlet {

    ProductService productService;


    @Override
    public void init()
    {
        productService =  ProductService.getInstance();
    }
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        List<ProductDto> productDtos = productService.getOffersProducts();
        req.getServletContext().setAttribute("offerProducts", productDtos);

        req.getServletContext().setAttribute("priorityProducts", productService.getPriorityProducts());

        response.setContentType("text/html");
        RequestDispatcher rd = req.getRequestDispatcher("/views/header.jsp");
        rd.include(req, response);

        rd = req.getRequestDispatcher("/views/home.jsp");
        rd.include(req, response);

        rd = req.getRequestDispatcher("/views/footer.jsp");
        rd.include(req, response);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
            response.setContentType("application/json");
        PrintWriter writer  = response.getWriter();
        int sel = Integer.parseInt(req.getParameter("sel"));
        List<ProductDto> productDtos = null;
        if(sel==1)
        {
            req.getServletContext().setAttribute("priorityProducts", productService.getPriorityProducts());
        }
        else if(sel==2)
        {
            req.getServletContext().setAttribute("mostProducts", productService.getMostSellingProducts());


        }else{
            productDtos = productService.getOffersProducts();

        }
        Gson gson = new Gson();
        String json = gson.toJson(productDtos);
        writer.write(json);
    }

}
