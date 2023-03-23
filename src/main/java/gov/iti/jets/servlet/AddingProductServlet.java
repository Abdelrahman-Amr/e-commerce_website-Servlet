package gov.iti.jets.servlet;

import com.google.gson.Gson;
import gov.iti.jets.dto.AdminProductDto;
import gov.iti.jets.dto.ProductDto;
import gov.iti.jets.dto.RegistrationCustomerDTO;
import gov.iti.jets.entity.Category;
import gov.iti.jets.entity.Product;
import gov.iti.jets.service.CategoryService;
import gov.iti.jets.service.ProductService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import jakarta.servlet.annotation.MultipartConfig;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

@MultipartConfig
public class AddingProductServlet extends HttpServlet {

    ProductService productService = ProductService.getInstance();

    CategoryService categoryService = CategoryService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/views/header.jsp");
        rd.include(request, response);

        rd = request.getRequestDispatcher("/views/addingProduct.html");
        rd.include(request, response);

        rd = request.getRequestDispatcher("/views/footer.jsp");
        rd.include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();

        try {
    resp.setContentType("application/json");

    ServletContext servletContext = req.getServletContext();
    String path = servletContext.getRealPath("/images/");

    Part part = req.getPart("file");
    if (part != null) {

        Long productId = null;

        String productJson = new String(req.getParameter("productInfo"));
//            System.out.println(productJson);
        ProductDto productDTO = new Gson().fromJson(productJson, ProductDto.class);

        Product product = productService.addNewProduct(productDTO, true);

        productId = product.getId();

        if (productId != null) {

            String[] strArr = part.getSubmittedFileName().split("[.]");

            String fileName = "product/product_" + productId + "." + strArr[strArr.length - 1];

            part.write(path + fileName);

            product.setImageUrl(fileName);

//                Category category = new Category();
//
//                category.setName("category1");
//
//                categoryService.save(category);
//
//                product.setCatg(categoryService.getCategoryByName(productDTO.getCategory()));

            productService.update(product);

            writer.write("1");
        } else {
            writer.write("0");
        }
    } else {
        writer.write("0");
    }
}catch (Exception ex){
    writer.write("0");
}
    }
}
