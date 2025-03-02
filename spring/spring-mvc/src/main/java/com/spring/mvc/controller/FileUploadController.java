package com.spring.mvc.controller;

import jakarta.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
  public String fileUpload(@RequestParam("profile") MultipartFile file, HttpSession session , Model model) throws IOException {
    System.out.println(file.getSize());
    System.out.println(file.getOriginalFilename());
    System.out.println(file.getContentType());
    System.out.println(file.getName());
    System.out.println(file.getInputStream());

    byte[] data = file.getBytes();
    String path =
        session.getServletContext().getRealPath("\\") + "\\" + "static" + "\\" + "images"
            + "\\"
        + file.getOriginalFilename();
    System.out.println(path);

    try(FileOutputStream fos = new FileOutputStream(path)){
      fos.write(data);
      model.addAttribute("msgUpload","File uploaded successfully");
      model.addAttribute("fileName",file.getOriginalFilename());
    }
    System.out.println("File uploaded successfully");

    return "fileSuccess";
  }
}