package gov.iti.jets.servlet;

import gov.iti.jets.dto.CategoryDto;
import gov.iti.jets.entity.Category;
import gov.iti.jets.mapper.CategoryMapper;
import gov.iti.jets.mapper.CustomerMapper;
import gov.iti.jets.service.CategoryService;
import gov.iti.jets.service.CustomerService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mapstruct.factory.Mappers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class HomeServlet extends HttpServlet {

    CategoryService categoryService;
    CategoryMapper categoryMapper;


    @Override
    public void init()
    {
        categoryService = new CategoryService();
        categoryMapper = Mappers.getMapper(CategoryMapper.class);
    }
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        List<CategoryDto> categoryDtos = categoryService.getAll();
        req.getServletContext().setAttribute("cats", categoryDtos);

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
        doGet(req, response);
    }

}
