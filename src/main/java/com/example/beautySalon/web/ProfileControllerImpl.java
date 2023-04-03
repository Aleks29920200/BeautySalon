package com.example.beautySalon.web;

import com.example.beautySalon.domain.dto.binding.EditUser;
import com.example.beautySalon.domain.dto.binding.FileDownloadModel;
import com.example.beautySalon.domain.dto.binding.FileUploadModel;
import com.example.beautySalon.domain.dto.service.FileDto;
import com.example.beautySalon.domain.dto.view.UserViewDto;
import com.example.beautySalon.domain.entity.FileEntity;
import com.example.beautySalon.services.FileService;
import com.example.beautySalon.services.UserServiceImpl;
import com.example.beautySalon.util.ImageUtil;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.security.Principal;

@Controller
public class ProfileControllerImpl implements ProfileController{
    private UserServiceImpl userService;
    private ModelMapper mapper;
    public ProfileControllerImpl(UserServiceImpl userService, ModelMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @Override
    public ModelAndView profile(Principal principal, ModelAndView modelAndView){
        UserViewDto userByUsername = mapper.map(this.userService.findUserByUsername(principal.getName()), UserViewDto.class);
        modelAndView.setViewName("/profile");
        modelAndView.addObject("user",userByUsername);
        return modelAndView;
    }
    @Override
    public String profileEdit(@Valid @ModelAttribute EditUser editUser,
                                    BindingResult result,
                                    RedirectAttributes attr,Principal principal) throws IOException {
        if (!editUser.getPassword().equals(editUser.getConfirmPassword())) {
            result.addError(
                    new FieldError(
                            "differentConfirmPassword",
                            "confirmPassword",
                            "Passwords must be the same."));
        }

        if (result.hasErrors()) {
            attr
                    .addFlashAttribute("editUser", editUser)
                    .addFlashAttribute("org.springframework.validation.BindingResult.editUser", result);

            return "redirect:/profile";
        }
        this.userService.editUser(editUser,principal.getName());
        return "redirect:/profile";
    }
    @ModelAttribute
    public EditUser editUser() {
        return new EditUser();
    }
}
