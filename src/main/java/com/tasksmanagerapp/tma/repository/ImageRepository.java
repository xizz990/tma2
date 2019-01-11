package com.tasksmanagerapp.tma.repository;

import com.tasksmanagerapp.tma.model.Image;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface ImageRepository extends CrudRepository<Image, Long> {
    Image findByName(String name);

    Page<Image> findAll(Pageable pageable);
}
