package gov.iti.jets.servlet;

import com.google.gson.Gson;
import gov.iti.jets.dto.CustomerDto;
import gov.iti.jets.dto.RegistrationCustomerDTO;
import gov.iti.jets.service.CustomerService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@MultipartConfig(fileSizeThreshold=1024*1024*10, 	// 10 MB
        maxFileSize=1024*1024*50,      	// 50 MB
        maxRequestSize=1024*1024*100)
public class ProductImageServlet extends HttpServlet {


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = request.getServletContext();
        String path = context.getRealPath("/images/product/product.jpg");
//        String path = "images/product/mocha.png";
        BufferedImage bImage = ImageIO.read(new File(path));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        String tokens[] = path.split("[.]",0);
        String ext = tokens[tokens.length-1];
        ImageIO.write(bImage, ext, bos );
        byte [] imageBytes = bos.toByteArray();
//        return data;
//        byte[] imageBytes = getImageAsBytes();
//
        response.setContentType("image/"+ext);
        response.setContentLength(imageBytes.length);
        response.getOutputStream().write(imageBytes);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = req.getServletContext();
        String path = context.getRealPath("/images/product/");
        Part filePart = req.getPart("file");
        if(filePart == null)
        {
            resp.getWriter().print("0");
            return ;
        }
        String fileName = filePart.getSubmittedFileName();
        for (Part part : req.getParts()){
            part.write(path +fileName);
        }
        resp.getWriter().print("1");
    }
}
