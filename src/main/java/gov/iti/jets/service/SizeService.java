package gov.iti.jets.service;

import gov.iti.jets.dto.CustomerDto;
import gov.iti.jets.dto.RegistrationCustomerDTO;
import gov.iti.jets.dto.SizeDto;
import gov.iti.jets.entity.Customer;
import gov.iti.jets.entity.Size;
import gov.iti.jets.mapper.CustomerMapper;
import gov.iti.jets.mapper.SizeMapper;
import gov.iti.jets.persistence.dao.CustomerDAO;
import gov.iti.jets.persistence.dao.SizeDAO;
import gov.iti.jets.util.Utility;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.mapstruct.factory.Mappers;

import java.util.*;

public class SizeService extends BaseService<Size>{
    private volatile static SizeService sizeService;


    SizeMapper sizeMapper;

    private SizeDAO sizeDAO;

    private SizeService() {

        sizeMapper = Mappers.getMapper(SizeMapper.class);
    }
    public static SizeService getInstance() {
        if (sizeService == null) {
            synchronized (SizeService.class) {
                if (sizeService == null) {
                    sizeService = new SizeService();
                }
            }
        }
        return sizeService;
    }


    public SizeDto getSizeById(Long id) {
        sizeDAO =  new SizeDAO();
        Size size = sizeDAO.getSizeById(id);
        return sizeMapper.toDto(size);
    }

    public List<SizeDto> listAllSizes() {
        sizeDAO =  new SizeDAO();
        List<SizeDto> sizes = sizeMapper.toDTOs(sizeDAO.listAllSizes());
        return sizes;
    }

    public SizeDto  getSizeByName(String name) {
        sizeDAO =  new SizeDAO();
        SizeDto sizeDto = sizeMapper.toDto(sizeDAO.getSizeByName(name));
        return sizeDto;
    }
}
