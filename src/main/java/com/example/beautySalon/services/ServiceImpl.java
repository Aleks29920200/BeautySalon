package com.example.beautySalon.services;

import com.example.beautySalon.domain.dto.binding.AddServiceDto;
import com.example.beautySalon.domain.dto.view.ServiceViewDto;
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
        service.setName(serviceDto.getName());
        service.setPrice(serviceDto.getPrice());
        service.setCategory(ServiceCategory.valueOf(serviceDto.getCategory()));
        service.setInfo(serviceDto.getInfo());
        serviceDto.setImg(mapper.map(this.fileService.getCurrentAddedImage(),FileDto.class));
        service.setImg(this.fileService.getCurrentAddedImage());
        this.repo.saveAndFlush(service);
    }
    @Override
    public ServiceViewDto findServiceById(Long id) throws ObjectNotFoundException {
        com.example.beautySalon.domain.entity.Service serviceById1 = this.repo.findServiceById(id);
        if(serviceById1==null){
            throw new ObjectNotFoundException(id,"Service");
        }
        ServiceViewDto serviceById = mapper.map(serviceById1, ServiceViewDto.class);
        if(serviceById==null){
            throw new ObjectNotFoundException(id,"Service");
        }
        return serviceById;
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
