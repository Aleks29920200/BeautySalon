package com.example.dealership1.web;

import com.example.dealership1.services.QRCodeService;
import com.example.dealership1.util.ImageUtil;
import com.example.dealership1.util.QRCodeGenerator;
import com.google.zxing.WriterException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.Base64;


@Controller
public class QRCodeController {
        private static final String QR_CODE_IMAGE_PATH = "src/main/resources/static/images/QRCode.png";
        private QRCodeService qrCodeService;

    public QRCodeController(QRCodeService qrCodeService) {
        this.qrCodeService = qrCodeService;
    }

    @GetMapping("/user/all-transactions/qrcode")
    @PreAuthorize("hasRole('USER')")
        public String getQRCode(Model model) {
            byte[] image = new byte[0];
            try {
                // Generate and Return Qr Code in Byte Array
                image = QRCodeGenerator.getQRCodeImage(qrCodeService.qrCodeContent(), 250, 250);

                // Generate and Save Qr Code Image in static/image folder
                QRCodeGenerator.generateQRCodeImage(qrCodeService.qrCodeContent(), 250, 250, QR_CODE_IMAGE_PATH);

            } catch (WriterException | IOException e) {
                e.printStackTrace();
            }
            // Convert Byte Array into Base64 Encode String
            String qrcode = Base64.getEncoder().encodeToString(image);

            model.addAttribute("medium",qrCodeService.qrCodeContent());
            model.addAttribute("qrcode", qrcode);

            return "/user/qrcode";
        }
}

