package com.example.dealership1.web;

import java.io.IOException;

import com.example.dealership1.domain.dto.binding.FileUploadModel;
import com.example.dealership1.services.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class FileUploadController {

  private final FileService fileService;

  public FileUploadController(FileService fileService) {
    this.fileService = fileService;
  }

  @GetMapping("/upload")
  public String upload() {
    return "upload";
  }
  @PostMapping("/upload")
  public String uploadModel(FileUploadModel modelUploadDTO) throws IOException {
    return "redirect:/show" + fileService.upload(modelUploadDTO);
  }

}

