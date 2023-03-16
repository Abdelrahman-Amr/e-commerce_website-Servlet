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


    @Override
    public void init() {
        productService = new ProductService();
        productMapper = Mappers.getMapper(ProductMapper.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        List<ProductDto> productDtos = productService.listAllProducts();
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
