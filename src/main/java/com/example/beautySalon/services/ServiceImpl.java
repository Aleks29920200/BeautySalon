package com.example.beautySalon.services;

import com.example.beautySalon.domain.dto.binding.AddServiceDto;
import com.example.beautySalon.domain.dto.view.ServiceViewDto;
import com.example.beautySalon.domain.entity.FileEntity;
import com.example.beautySalon.domain.entity.ServiceCategory;
import com.example.beautySalon.domain.dto.error.ObjectNotFoundException;
import com.example.beautySalon.domain.dto.service.FileDto;
import com.example.beautySalon.repositories.ServiceRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@org.springframework.stereotype.Service
public class ServiceImpl implements Service{
    private ServiceRepo repo;
    private ModelMapper mapper;
    private FileService fileService;
@Autowired
    public ServiceImpl(ServiceRepo repo, ModelMapper mapper, FileService fileService) {
        this.repo = repo;
    this.mapper = mapper;
    this.fileService = fileService;
}

    @Override
    public com.example.beautySalon.domain.entity.Service findServiceByName(String name) {
        return this.repo.findServiceByName(name);
    }

    @Override
    public void saveService(AddServiceDto serviceDto) throws IOException {
        com.example.beautySalon.domain.entity.Service service=new com.example.beautySalon.domain.entity.Service();
        service.setName(getNameOfTheService(serviceDto));
        service.setPrice(getPriceOfTheService(serviceDto));
        service.setCategory(parsingCategoryToString(serviceDto));
        service.setInfo(infoAboutGivenService(serviceDto));
        serviceDto.setImg(mappingToFileDto());
        service.setImg(getCurrentAddedImage());
        this.repo.saveAndFlush(service);
    }
    private FileEntity getCurrentAddedImage() {
        return this.fileService.getCurrentAddedImage();
    }

    private static String getNameOfTheService(AddServiceDto serviceDto) {
        return serviceDto.getName();
    }

    private static Float getPriceOfTheService(AddServiceDto serviceDto) {
        return serviceDto.getPrice();
    }

    private FileDto mappingToFileDto() {
        return mapper.map(this.fileService.getCurrentAddedImage(), FileDto.class);
    }

    private static String infoAboutGivenService(AddServiceDto serviceDto) {
        return serviceDto.getInfo();
    }

    private static ServiceCategory parsingCategoryToString(AddServiceDto serviceDto) {
        return ServiceCategory.valueOf(serviceDto.getCategory());
    }

    @Override
    public ServiceViewDto findServiceById(Long id) throws ObjectNotFoundException {
        if(getServiceById(id)==null){
            throw new ObjectNotFoundException(id,"Service");
        }
        if(mappingToServiceViewDto(getServiceById(id))==null){
            throw new ObjectNotFoundException(id,"Service");
        }
        return mappingToServiceViewDto(getServiceById(id));
    }

    private com.example.beautySalon.domain.entity.Service getServiceById(Long id) {
        return this.repo.findServiceById(id);
    }

    private ServiceViewDto mappingToServiceViewDto(com.example.beautySalon.domain.entity.Service serviceById1) {
        return mapper.map(serviceById1, ServiceViewDto.class);
    }

    @Override
    public void deleteById(Long id) {
        this.repo.deleteById(id);
    }
    @Override
    public List<ServiceViewDto> allServices(){
    return this.repo.findAll().stream().map(e->mapper.map(e, ServiceViewDto.class)).
            collect(Collectors.toList());
    }
}
