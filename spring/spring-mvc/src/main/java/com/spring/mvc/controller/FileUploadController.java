package com.spring.mvc.controller;

import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {
  @RequestMapping(path = "/goToFileForm" ,method = RequestMethod.POST)
  public String fileForm(){
    System.out.println("Inside contact method of controller");
    return "fileForm";
  }

  @RequestMapping(path = "/uploadImage" ,method = RequestMethod.POST)
  public String fileUpload(@RequestParam("profile") MultipartFile file) throws IOException {
    System.out.println(file.getSize());
    System.out.println(file.getOriginalFilename());
    System.out.println(file.getContentType());
    System.out.println(file.getName());
    System.out.println(file.getInputStream());
    return "fileSuccess";
  }
}