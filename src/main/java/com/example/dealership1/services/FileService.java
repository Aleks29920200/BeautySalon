package com.example.dealership1.services;


import java.io.IOException;
import java.util.List;

import com.example.dealership1.domain.dto.binding.FileDownloadModel;
import com.example.dealership1.domain.dto.binding.FileUploadModel;
import com.example.dealership1.domain.dto.service.FileDto;
import com.example.dealership1.domain.entity.FileEntity;
import com.example.dealership1.domain.entity.ServiceCategory;
import com.example.dealership1.repositories.FileRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

  private FileRepository fileRepository;

  public FileService(FileRepository fileRepository) {

    this.fileRepository = fileRepository;
  }
  public int upload(FileUploadModel model) throws IOException {
    MultipartFile img = model.getImg();

    FileEntity newFile = new FileEntity();

    newFile.setFileData(img.getBytes());
    newFile.setContentType(img.getContentType());
    newFile.setFileName(img.getOriginalFilename());

    return fileRepository.saveAndFlush(newFile).getId();
  }
  public FileEntity getCurrentAddedImage(){
    return this.fileRepository.findAll().get(this.fileRepository.findAll().size()-1);
  }
  public FileDownloadModel download(int fileId) {
    FileEntity file = fileRepository.findById(fileId).orElseThrow(() -> new IllegalArgumentException("File" + fileId + " not found!"));

    return new FileDownloadModel()
        .setContentType(file.getContentType())
        .setName(file.getFileName()).
        setDocument(file.getFileData());
  }

}
