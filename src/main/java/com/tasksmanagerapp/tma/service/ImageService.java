package com.tasksmanagerapp.tma.service;

import com.tasksmanagerapp.tma.model.Image;
import com.tasksmanagerapp.tma.model.Task;
import com.tasksmanagerapp.tma.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ImageService {

//    private static String UPLOAD_ROOT = "upload-dir";
    private static String UPLOAD_ROOT = "/images/";

    private final ImageRepository repository;
    private final ResourceLoader resourceLoader;

    @Autowired
    public ImageService(ImageRepository repository, ResourceLoader resourceLoader){

        this.repository = repository;
        this.resourceLoader = resourceLoader;
    }

    public Page<Image> findPage(Pageable pageable){
        return repository.findAll(pageable);
    }

    public Resource findOneImage(String filename){
        return resourceLoader.getResource("file: /image" + UPLOAD_ROOT + "/" + filename);
    }

    public void createImage(MultipartFile file) throws IOException {
        if(!file.isEmpty()){
            Files.copy(file.getInputStream(), Paths.get(UPLOAD_ROOT, file.getOriginalFilename()));
            repository.save(new Image(file.getOriginalFilename()));
        }
    }

    public void deleteImage(String filename) throws IOException {
        final Image byName = repository.findByName(filename);
        repository.delete(byName);
        Files.deleteIfExists(Paths.get(UPLOAD_ROOT, filename));
    }

    @Bean
//    @Profile("dev")
    CommandLineRunner setUp(ImageRepository repository) throws IOException {

        return (args) -> {
            FileSystemUtils.deleteRecursively(new File(UPLOAD_ROOT));

            Files.createDirectory(Paths.get(UPLOAD_ROOT));

            FileCopyUtils.copy("Test file", new FileWriter(UPLOAD_ROOT + "/test"));
            try {
                repository.save(new Image("bin.jpeg"));
                repository.save(new Image("bin.jpeg"));
                repository.save(new Image("black_bg.jpeg"));
                repository.save(new Image("dist_transf.jpeg"));
            } catch (Exception e) {
//                e.printStackTrace();
            }
        };
    }
}
