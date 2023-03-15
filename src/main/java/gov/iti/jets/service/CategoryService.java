package gov.iti.jets.service;

import gov.iti.jets.dto.CategoryDto;
import gov.iti.jets.entity.Category;
import gov.iti.jets.mapper.CategoryMapper;
import gov.iti.jets.persistence.dao.CategoryDAO;
import org.mapstruct.factory.Mappers;

import java.util.List;

public class CategoryService extends BaseService<Category>{
    CategoryMapper categoryMapper;

    private CategoryDAO categoryDAO;

    public CategoryService() {
        categoryDAO = new CategoryDAO();
        dao = categoryDAO;
        categoryMapper = Mappers.getMapper(CategoryMapper.class);
    }

    public List<CategoryDto> getAll()
    {
        return categoryMapper.toDTOs(categoryDAO.getAll());
    }
}
