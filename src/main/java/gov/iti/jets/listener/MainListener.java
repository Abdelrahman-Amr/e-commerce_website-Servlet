package gov.iti.jets.listener;

import gov.iti.jets.dto.CategoryDto;
import gov.iti.jets.dto.SizeDto;
import gov.iti.jets.mapper.CategoryMapper;
import gov.iti.jets.persistence.dao.DBFactory;
import gov.iti.jets.service.CategoryService;
import gov.iti.jets.service.CustomerService;
import gov.iti.jets.service.SizeService;
import gov.iti.jets.util.MyLocal;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

import java.util.List;

public class MainListener implements ServletContextListener {

    public void contextInitialized (ServletContextEvent cse) {
        DBFactory.getInstance();
        MyLocal.getInstance().set(DBFactory.getInstance().createEntityManager());
        CategoryService categoryService =  CategoryService.getInstance();
        SizeService sizeService =  SizeService.getInstance();


        List<CategoryDto> categoryDtos = categoryService.getAll();
        cse.getServletContext().setAttribute("cats", categoryDtos);

        List<SizeDto> sizeDtos = sizeService.listAllSizes();
        cse.getServletContext().setAttribute("sizes", sizeDtos);


    }
    public void contextDestroyed (ServletContextEvent cse) {
        System.out.println("Application shut down");
    }
}
