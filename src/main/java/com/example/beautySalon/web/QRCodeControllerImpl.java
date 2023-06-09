package com.example.beautySalon.web;

import com.example.beautySalon.services.QRCodeService;
import com.example.beautySalon.util.QRCodeGenerator;
import com.google.zxing.WriterException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.Base64;


@Controller
public class QRCodeControllerImpl implements QrCodeController{
        private static final String QR_CODE_IMAGE_PATH = "src/main/resources/static/images/QRCode.png";
        private QRCodeService qrCodeService;

    public QRCodeControllerImpl(QRCodeService qrCodeService) {
        this.qrCodeService = qrCodeService;
    }

    @Override
        public String getQRCode(Model model) {
            byte[] image = new byte[0];
            try {

                image = QRCodeGenerator.getQRCodeImage(qrCodeService.qrCodeContent(), 250, 250);


                QRCodeGenerator.generateQRCodeImage(qrCodeService.qrCodeContent(), 250, 250, QR_CODE_IMAGE_PATH);

            } catch (WriterException | IOException e) {
                e.printStackTrace();
            }

            String qrcode = Base64.getEncoder().encodeToString(image);

            model.addAttribute("medium",qrCodeService.qrCodeContent());
            model.addAttribute("qrcode", qrcode);

            return "/user/qrcode";
        }
}

