package com.example.dealership1.web;

import com.example.dealership1.domain.dto.binding.FileUploadModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
public interface FileUploadController {
    @GetMapping("/upload")
    public String upload();
    @PostMapping("/upload")
    public String uploadModel(FileUploadModel modelUploadDTO) throws IOException;

}
