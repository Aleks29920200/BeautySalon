package com.example.beautySalon.web;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public interface FileDownloadController {
    @GetMapping("/show/{fileId}")
    public String show(@PathVariable("fileId") int fileId, Model model);

    @GetMapping("/download/{fileId}")
    public HttpEntity<byte[]> downLoad(
            @PathVariable("fileId") int fileId);
}
