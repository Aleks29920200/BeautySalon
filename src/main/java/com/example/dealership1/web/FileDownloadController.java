package com.example.dealership1.web;

import com.example.dealership1.domain.dto.binding.FileDownloadModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public interface FileDownloadController {
    @GetMapping("/show/{fileId}")
    public String show(@PathVariable("fileId") int fileId, Model model);

    @GetMapping("/download/{fileId}")
    public HttpEntity<byte[]> downLoad(
            @PathVariable("fileId") int fileId);
}
