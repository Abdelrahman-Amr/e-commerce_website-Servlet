package gov.iti.jets.servlet;

import com.google.gson.Gson;
import gov.iti.jets.dto.ProductDto;
import gov.iti.jets.entity.Category;
import gov.iti.jets.entity.Product;
import gov.iti.jets.service.CategoryService;
import gov.iti.jets.service.ProductService;
import gov.iti.jets.util.Constants;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@MultipartConfig
public class EditingProductServlet extends HttpServlet {

    ProductService productService;

    CategoryService categoryService;

    @Override
    public void init() {
        productService = ProductService.getInstance();
        categoryService = CategoryService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Category> categoryList;
        long productId;
        HttpSession httpSession = request.getSession(false);

        try {

            productId = Long.parseLong(request.getParameter("id"));

//            System.out.println("product id " + productId);

            ProductDto productDto = productService.getProductById(productId);

//            System.out.println(productDto.getName());

//            System.out.println(productDto.getCatg_id());

            Category currentCategory = categoryService.get(productDto.getCatg_id());

            httpSession.setAttribute("currentProduct", productDto);
            httpSession.setAttribute("currentProductCategory", currentCategory.getName());

            categoryList = categoryService.getAllCategories();

            categoryList.remove(currentCategory);

            httpSession.setAttribute("categoryList", categoryList);

            RequestDispatcher rd = request.getRequestDispatcher("/views/header.jsp");
            rd.include(request, response);

            rd = request.getRequestDispatcher("/views/editingProduct.jsp");
            rd.include(request, response);

            rd = request.getRequestDispatcher("/views/footer.jsp");
            rd.include(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        Product product;

        PrintWriter writer = resp.getWriter();

        try {
            resp.setContentType("application/json");

//            String path = servletContext.getRealPath("/images/");
            String path= Constants.imgPath;

            Part part = request.getPart("file");

            Long productId;

            String productJson = request.getParameter("productInfo");

//            System.out.println(productJson);

            ProductDto productDTO = new Gson().fromJson(productJson, ProductDto.class);

            ProductDto oldProduct = (ProductDto) request.getSession(false).getAttribute("currentProduct");

            productDTO.setId(oldProduct.getId());

            product = productService.editProduct(productDTO);

            product.setCatg(categoryService.get(productDTO.getCatg_id()));

            if (part.getSubmittedFileName() != null) {

                productId = product.getId();

                if (productId != null) {

                    String[] strArr = part.getSubmittedFileName().split("[.]");

                    String fileName = "product/product_" + productId + "." + strArr[strArr.length - 1];

                    part.write(path + fileName);

                    product.setImageUrl(fileName);

                } else {
                    writer.write("0");
                }
            } else {
                product.setImageUrl(oldProduct.getImageUrl());
            }

            productService.update(product);

            writer.write("1");

        } catch (Exception ex){
            // roll back
            ex.printStackTrace();
            writer.write("0");
        }
    }
}
