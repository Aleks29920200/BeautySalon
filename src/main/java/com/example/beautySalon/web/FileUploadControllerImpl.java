package com.example.beautySalon.web;

import java.io.IOException;

import com.example.beautySalon.domain.dto.binding.FileUploadModel;
import com.example.beautySalon.services.FileService;
import org.springframework.stereotype.Controller;


@Controller
public class FileUploadControllerImpl implements FileUploadController {

  private final FileService fileService;

  public FileUploadControllerImpl(FileService fileService) {
    this.fileService = fileService;
  }

  @Override
  public String upload() {
    return "upload";
  }
  @Override
  public String uploadModel(FileUploadModel modelUploadDTO) throws IOException {
    this.fileService.upload(modelUploadDTO);
    return "redirect:/admin/add-service";
  }
}

