package com.tasksmanagerapp.tma.controller;

import com.tasksmanagerapp.tma.model.Image;
import com.tasksmanagerapp.tma.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.io.*;
import java.net.URISyntaxException;

@Controller
@RequestMapping(value="/image")
public class ImageController {

    private static final String BASE_PATH="/images";
    private static final String FILENAME = "{filename:.+}";

    @Autowired
    ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService){
        this.imageService = imageService;
    }

    @RequestMapping(value="/", method=RequestMethod.GET)
    public ModelAndView list() {
        ModelAndView model = new ModelAndView("add_image");

        return model;
    }

    @RequestMapping(value="/images", method=RequestMethod.GET)
    public String showImageList(Model model, Pageable pageable) {
        final Page<Image> page = imageService.findPage(pageable);
        model.addAttribute("page", page);

        return "add_image_h";
    }

    @RequestMapping(method = RequestMethod.GET, value = BASE_PATH + "/" + FILENAME + "/raw")
    @ResponseBody
    public ResponseEntity<?> oneRawImage(@PathVariable String filename) {

        try {
            Resource file =imageService.findOneImage(filename);
            return ResponseEntity.ok()
                    .contentLength(file.contentLength())
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(new InputStreamReader(file.getInputStream()));
        } catch (IOException e){
            return ResponseEntity.badRequest()
                    .body("Couldnt find " + filename + " " + e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = BASE_PATH)
    @ResponseBody
    public ResponseEntity<?> createFile(@RequestParam("file") MultipartFile file, HttpServletRequest servletRequest) throws URISyntaxException {

        try {
            imageService.createImage(file);
            final URI locationUri = new URI(servletRequest.getRequestURL().toString() + "/")
                    .resolve(file.getOriginalFilename() + "/raw");
            return ResponseEntity.created(locationUri)
                    .body("Successfully uploaded " + file.getOriginalFilename());
        } catch (IOException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to uplaod " + file.getOriginalFilename() + " => " + e.getMessage());
        }
    }

}
