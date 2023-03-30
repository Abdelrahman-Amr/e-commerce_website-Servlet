package gov.iti.jets.servlet;

import com.google.gson.Gson;
import gov.iti.jets.dto.CategoryDto;
import gov.iti.jets.dto.ProductDto;
import gov.iti.jets.entity.Product;
import gov.iti.jets.service.CategoryService;
import gov.iti.jets.service.ProductService;
import gov.iti.jets.util.Constants;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import jakarta.servlet.annotation.MultipartConfig;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@MultipartConfig
public class AddingProductServlet extends HttpServlet {

    ProductService productService;

    CategoryService categoryService;

    @Override
    public void init()
    {
        productService = ProductService.getInstance();
        categoryService = CategoryService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/views/header.jsp");
        rd.include(request, response);

        List<CategoryDto> categoryList;

        categoryList = categoryService.getAll();

        request.getSession(false).setAttribute("categoryList",categoryList);

        rd = request.getRequestDispatcher("/views/addingProduct.jsp");
        rd.include(request, response);

        rd = request.getRequestDispatcher("/views/footer.jsp");
        rd.include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();

        try {
            resp.setContentType("application/json");

//            String path = servletContext.getRealPath("/images/");
            String path = Constants.imgPath;

            Part part = req.getPart("file");
            if (part != null) {

                Long productId;

                String productJson = req.getParameter("productInfo");

                ProductDto productDTO = new Gson().fromJson(productJson, ProductDto.class);

//                System.out.println(productDTO.getName());

                Product product = productService.addNewProduct(productDTO, true);

//                System.out.println(product.getName());

                productId = product.getId();

                if (productId != null) {

                    String[] strArr = part.getSubmittedFileName().split("[.]");

                    String fileName = "product/product_" + productId + "." + strArr[strArr.length - 1];

                    part.write(path + fileName);

                    product.setImageUrl(fileName);

//                    System.out.println(req.getParameter("categoryId"));

                    product.setCatg(categoryService.get(productDTO.getCatg_id()));

                    productService.update(product);

                    writer.write("1");
                } else {
                    writer.write("0");
                }
            } else {
                writer.write("0");
            }
        } catch (Exception ex){
            ex.printStackTrace();
            writer.write("0");
        }
    }
}
