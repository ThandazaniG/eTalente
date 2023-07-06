package com.eviro.assessment.grad001.ThandazaniGwampa.repository;

import com.eviro.assessment.grad001.ThandazaniGwampa.model.AccountProfile;
import com.eviro.assessment.grad001.ThandazaniGwampa.model.Image;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.io.File;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@DataJpaTest
class ImageRepositoryTest {
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private AccountProfileRepository accountProfileRepository;
    private AccountProfile account1, account2;

    @BeforeEach
    void beforeEach(){
        account1 = new AccountProfile("Zani", "Health");
        account2 = new AccountProfile("Lunga", "Engineering");
        accountProfileRepository.saveAll(List.of(account1, account2));

    }
    @AfterEach
    void afterEach(){
        accountProfileRepository.deleteAll();
        account1 = account2 = null;
    }
    @Test
    void findImagesExist(){
        AtomicInteger index = new AtomicInteger();
        getImages().forEach(imageBytes ->
        {
            var image  = new Image(index.getAndIncrement()==1?"img1.jpeg":"img2.jpeg", imageBytes);
             image.setCompany(index.get()==1?account1:account2);
             imageRepository.save(image);
        });
        var images = imageRepository.findAllImages();
        Assertions.assertNotNull(images);
        Assertions.assertEquals(2,images.size());
        Assertions.assertTrue(images.stream().allMatch(img->img.getCompany()!=null));

    }
    @Test
    void findImagesNotExist(){
        var images = imageRepository.findAllImages();
        Assertions.assertNotNull(images);
        Assertions.assertTrue(images.isEmpty());
    }

    List<byte[]> getImages(){
        return Arrays.stream(new String[]{"img1.jpeg","img2.jpeg"}).map(
                imagename -> {
                   byte [] tests= null;
                    try
                    {
                       File file = new File("src/test/resources/static/"+imagename);
                       tests = Files.readAllBytes(file.toPath()) ;   //file may be  null


                    }catch(Exception e){
                        e.fillInStackTrace();
                    }
                    return  tests;  //function may return null
                }
        ).toList();
    }


}