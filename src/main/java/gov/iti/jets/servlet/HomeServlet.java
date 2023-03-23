package gov.iti.jets.servlet;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HomeServlet extends HttpServlet {

//    CategoryService categoryService;


    @Override
    public void init()
    {
//        categoryService = new CategoryService();
    }
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
//        List<CategoryDto> categoryDtos = categoryService.getAll();
//        req.getServletContext().setAttribute("cats", categoryDtos);

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
