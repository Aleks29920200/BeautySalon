package com.example.dealership1.domain.dto.binding;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadModel {

  private MultipartFile img;

  public FileUploadModel(MultipartFile img) {
    this.img=img;
  }


  public MultipartFile getImg() {
    return img;
  }

  public FileUploadModel setImg(MultipartFile img) {
    this.img = img;
    return this;
  }
}
