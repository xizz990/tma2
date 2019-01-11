package com.tasksmanagerapp.tma.controller;

import com.tasksmanagerapp.tma.model.Image;
import com.tasksmanagerapp.tma.model.Task;
import com.tasksmanagerapp.tma.service.ImageService;
import com.tasksmanagerapp.tma.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value="/image")
public class ImageController {

    private static final String FILENAME = "{filename:.+}";

//    @Autowired
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

        return "add_image";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/images/" + FILENAME + "/raw")
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

}
