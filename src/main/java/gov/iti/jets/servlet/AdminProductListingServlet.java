package gov.iti.jets.servlet;

import com.google.gson.Gson;
import gov.iti.jets.dto.CategoryDto;
import gov.iti.jets.dto.ProductDto;
import gov.iti.jets.mapper.ProductMapper;
import gov.iti.jets.service.ProductService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mapstruct.factory.Mappers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminProductListingServlet extends HttpServlet {

    ProductService productService;
    ProductMapper productMapper;
    private static final int PRODUCTS_PER_PAGE = 9;

    @Override
    public void init() {
        productService =  ProductService.getInstance();
        productMapper = Mappers.getMapper(ProductMapper.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> params = new HashMap<>();
        params.put("page", req.getParameter("page"));
        params.put("catId", req.getParameter("catId"));
        if( req.getParameter("catId") !=null){
            long catId =  Long.parseLong(req.getParameter("catId"));
            List<CategoryDto> cats = (List<CategoryDto>) req.getServletContext().getAttribute("cats");
            for(CategoryDto cat : cats)
            {
                if(cat.getId() ==  catId)
                {
                    req.getServletContext().setAttribute("currentCat",cat.getName());
                    break;
                }
            }
        }else{
            req.getServletContext().setAttribute("currentCat","Shop");
        }

        params.put("price", req.getParameter("price"));
        int pageNo = 1;
        int pagination = 1;
        if (params.get("page") != null)
            pageNo = Integer.parseInt(params.get("page"));
        List<ProductDto> productDtos;

        productDtos = productService.getProductsByCriteria(params, (pageNo - 1) * PRODUCTS_PER_PAGE, PRODUCTS_PER_PAGE);
        Gson gson = new Gson();
        String json = gson.toJson(productDtos);

        pagination = (int) Math.ceil((double) productService.getNoOfReturnedProducts(params) / PRODUCTS_PER_PAGE);

        if (pagination < 1)
            pagination = 1;
        req.getServletContext().setAttribute("pagination", pagination);

        req.getServletContext().setAttribute("products", productDtos);
        Boolean isAJAX = ("XMLHttpRequest".equals(req.getHeader("x-requested-with")));

        req.getServletContext().setAttribute("isAJAX", isAJAX);
        RequestDispatcher rd;
        response.setContentType("text/html");
        if (!isAJAX) {
            rd = req.getRequestDispatcher("/views/header.jsp");
            rd.include(req, response);
        }

        rd = req.getRequestDispatcher("/views/admin-products.jsp");
        rd.include(req, response);

        if (!isAJAX) {
            rd = req.getRequestDispatcher("/views/footer.jsp");
            rd.include(req, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
