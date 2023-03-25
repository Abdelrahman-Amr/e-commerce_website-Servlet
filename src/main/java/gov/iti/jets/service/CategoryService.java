package gov.iti.jets.service;

import gov.iti.jets.dto.CategoryDto;
import gov.iti.jets.entity.Category;
import gov.iti.jets.mapper.CategoryMapper;
import gov.iti.jets.persistence.dao.CategoryDAO;
import org.checkerframework.checker.units.qual.C;
import org.mapstruct.factory.Mappers;

import java.util.List;

public class CategoryService extends BaseService<Category>{
    private volatile static CategoryService categoryService;

    CategoryMapper categoryMapper;

    private CategoryDAO categoryDAO;

    private CategoryService() {

        categoryMapper = Mappers.getMapper(CategoryMapper.class);
    }
    public static CategoryService getInstance() {
        if (categoryService == null) {
            synchronized (CategoryService.class) {
                if (categoryService == null) {
                    categoryService = new CategoryService();
                }
            }
        }
        return categoryService;
    }

    public List<CategoryDto> getAll()
    {
        categoryDAO =  new CategoryDAO();
        return categoryMapper.toDTOs(categoryDAO.getAll());
    }
}
