package gov.iti.jets.servlet;

import com.google.gson.Gson;
import gov.iti.jets.dto.CustomerDto;
import gov.iti.jets.dto.RegistrationCustomerDTO;
import gov.iti.jets.service.CustomerService;
import gov.iti.jets.util.Constants;
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
        String imgName = request.getParameter("imgName") ;
        String path = context.getRealPath("/images/")+imgName;
//        String path= Constants.imgPath+imgName;
//        File f = new File("D:/x.txt");
//        f.createNewFile();
        System.out.println(path);
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
        String path= Constants.imgPath;

        Part filePart = req.getPart("file");
        String fileName = filePart.getSubmittedFileName();
        filePart.write(path +fileName);

        if(filePart == null)
        {
            resp.getWriter().print("0");
            return ;
        }
//        for (Part part : req.getParts()){
//        }
        resp.getWriter().print("1");
    }
}
