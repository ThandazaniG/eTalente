package com.eviro.assessment.grad001.ThandazaniGwampa.controller;

import com.eviro.assessment.grad001.ThandazaniGwampa.service.ImageService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;


@RestController
@RequestMapping("/v1/api/image")
@AllArgsConstructor
public class ImageController {
    private final ImageService imageService;
    @GetMapping(value ="/{name}/{surname}/{\\w\\.\\w}")
    public FileSystemResource gethttpImageLink(@PathVariable String name, @PathVariable String surname){
       return imageService.gethttpImageLink(name, surname);
    }
    @PostConstruct
    private void parseCSVfile(){
        imageService.parseCSV(new File("src/main/resources/static/1672815113084-GraduateDev_AssessmentCsv_Ref003.csv"));
    }
}
