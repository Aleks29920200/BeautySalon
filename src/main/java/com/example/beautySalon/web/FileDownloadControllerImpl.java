package com.example.beautySalon.web;


import com.example.beautySalon.domain.dto.binding.FileDownloadModel;
import com.example.beautySalon.services.FileService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class FileDownloadControllerImpl implements FileDownloadController{

  private final FileService fileService;

  public FileDownloadControllerImpl(FileService fileService) {
    this.fileService = fileService;
  }

  @Override
  public String show(@PathVariable("fileId") int fileId, Model model) {
    model.addAttribute("fileId", fileId);
    return "show";
  }

  @Override
  public HttpEntity<byte[]> downLoad(
      @PathVariable("fileId") int fileId)  {

    FileDownloadModel fdm = fileService.download(fileId);
    HttpHeaders header = new HttpHeaders();
    header.setContentType(new MediaType(MimeTypeUtils.parseMimeType(fdm.getContentType())));
    header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fdm.getName());
    header.setContentLength(fdm.getDocument().length);

    return new HttpEntity<>(fdm.getDocument(), header);
  }


}


