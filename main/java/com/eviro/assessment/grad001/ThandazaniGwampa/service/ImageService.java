package com.eviro.assessment.grad001.ThandazaniGwampa.service;

import com.eviro.assessment.grad001.ThandazaniGwampa.dto.AccountEvent;
import com.eviro.assessment.grad001.ThandazaniGwampa.exception.DuplicateImage;
import com.eviro.assessment.grad001.ThandazaniGwampa.model.Image;
import com.eviro.assessment.grad001.ThandazaniGwampa.repository.ImageRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.URI;
import java.util.Scanner;

@Service
@AllArgsConstructor
public class ImageService implements FileParser{
    private final ImageRepository repository;
    private final ApplicationEventPublisher publisher;
    private  Account account;


    /**
     * @param cvsFile is the file that contain images
     */
    @Override
    public void parseCSV(File cvsFile) {
        if (cvsFile==null)throw  new NullPointerException("file is null");
        saveImages(cvsFile);


    }

    /**
     * save the images
     * @param cvsFile is the file that contain images
     */
   private void saveImages(File cvsFile){
       try {
           Scanner file = new Scanner(cvsFile);
           while (file.hasNextLine()){
               String line = file.nextLine();
               String[] fileContents = line.split(",");
               publisher.publishEvent(new AccountEvent(fileContents[0],fileContents[1]));
               var accountProfile = account.findAccountProfile(fileContents[0],fileContents[1]);
               var image = new Image(fileContents[2], fileContents[3].getBytes());
               image.setCompany(accountProfile);
               if(repository.existsById(image.getId())) throw  new DuplicateImage("Image already exists");
               repository.save(image);
           }

       }catch (Exception e){e.printStackTrace();}
    }

    /**
     * @param base64ImageData is the image data to be converted to an image file
     * @return an image file from bytes
     */
    @Override
    public File convertCSVDataToImage(String base64ImageData) {
        return null;
    }

    /**
     * @param fileImage is the image file converted from bytes
     * @return a link to an image file
     */
    @Override
    public URI createImageLink(File fileImage) {
        return null;
    }
}
