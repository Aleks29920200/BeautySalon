package com.example.dealership1.web;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public interface QrCodeController {
    @GetMapping("/user/all-transactions/qrcode")
    @PreAuthorize("hasRole('USER')")
    public String getQRCode(Model model);
}
