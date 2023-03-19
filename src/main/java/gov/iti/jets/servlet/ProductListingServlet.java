package gov.iti.jets.servlet;

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
import java.util.List;

public class ProductListingServlet extends HttpServlet {

    ProductService productService;
    ProductMapper productMapper;
    private static final int PRODUCTS_PER_PAGE = 9;

    @Override
    public void init() {
        productService = new ProductService();
        productMapper = Mappers.getMapper(ProductMapper.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        String page = req.getParameter("page");
        int pageNo = 1;
        Long pagination = 1L;
        if (page != null)
            pageNo = Integer.parseInt(page);
        List<ProductDto> productDtos;
        if (req.getParameter("catId") != null) {
            productDtos = productService.listAllProductsByCategory(Long.valueOf(req.getParameter("catId")), (pageNo - 1) * PRODUCTS_PER_PAGE, PRODUCTS_PER_PAGE);
            pagination = (productService.getNoOfRecords())/PRODUCTS_PER_PAGE;
        } else {
            productDtos = productService.listAllProducts((pageNo - 1) * PRODUCTS_PER_PAGE, PRODUCTS_PER_PAGE);
            pagination = (productService.getNoOfRecords())/PRODUCTS_PER_PAGE;
        }
            req.getServletContext().setAttribute("pagination", pagination);

        req.getServletContext().setAttribute("products", productDtos);
        response.setContentType("text/html");
        RequestDispatcher rd = req.getRequestDispatcher("/views/header.jsp");
        rd.include(req, response);

        rd = req.getRequestDispatcher("/views/products.jsp");
        rd.include(req, response);

        rd = req.getRequestDispatcher("/views/footer.jsp");
        rd.include(req, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
