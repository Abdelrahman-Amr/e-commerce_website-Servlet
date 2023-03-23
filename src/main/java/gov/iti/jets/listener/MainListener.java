package gov.iti.jets.listener;

import gov.iti.jets.dto.CategoryDto;
import gov.iti.jets.mapper.CategoryMapper;
import gov.iti.jets.persistence.dao.DBFactory;
import gov.iti.jets.service.CategoryService;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

import java.util.List;

public class MainListener implements ServletContextListener {

    public void contextInitialized (ServletContextEvent cse) {
        DBFactory.getInstance();
        CategoryService categoryService = new CategoryService();
        List<CategoryDto> categoryDtos = categoryService.getAll();
        cse.getServletContext().setAttribute("cats", categoryDtos);

    }
    public void contextDestroyed (ServletContextEvent cse) {
        System.out.println("Application shut down");
    }
}
