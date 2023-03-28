package gov.iti.jets.servlet;


import com.google.gson.Gson;
import gov.iti.jets.dto.CategoryDto;
import gov.iti.jets.dto.ProductDto;
import gov.iti.jets.dto.SizeDto;
import gov.iti.jets.persistence.dao.DBFactory;
import gov.iti.jets.service.CategoryService;
import gov.iti.jets.service.ProductService;
import gov.iti.jets.service.SizeService;
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
//        DBFactory.getInstance();
//        MyLocal.getInstance().set(DBFactory.getInstance().createEntityManager());
        CategoryService categoryService =  CategoryService.getInstance();
        SizeService sizeService =  SizeService.getInstance();

        List<CategoryDto> categoryDtos = categoryService.getAll();
        req.getServletContext().setAttribute("cats", categoryDtos);

        List<SizeDto> sizeDtos = sizeService.listAllSizes();
        req.getServletContext().setAttribute("sizes", sizeDtos);
        List<ProductDto> productDtos = productService.getOffersProducts();
        req.getServletContext().setAttribute("offerProducts", productDtos);
        productDtos = productService.getMostSellingProducts();
        req.getServletContext().setAttribute("mostProducts", productDtos);

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
            productDtos = productService.getPriorityProducts();

            req.getServletContext().setAttribute("priorityProducts", productDtos);
        }
        else if(sel==2)
        {
            productDtos = productService.getMostSellingProducts();
            req.getServletContext().setAttribute("mostProducts", productDtos);
        }else{
            productDtos = productService.getOffersProducts();
        }
        Gson gson = new Gson();
        String json = gson.toJson(productDtos);
        writer.write(json);
    }

}
