package com.eviro.assessment.grad001.ThandazaniGwampa.service;

import com.eviro.assessment.grad001.ThandazaniGwampa.exception.DuplicateImage;
import com.eviro.assessment.grad001.ThandazaniGwampa.exception.ImageNofFound;
import com.eviro.assessment.grad001.ThandazaniGwampa.exception.ImageNotValid;
import com.eviro.assessment.grad001.ThandazaniGwampa.model.AccountProfile;
import com.eviro.assessment.grad001.ThandazaniGwampa.model.Image;
import com.eviro.assessment.grad001.ThandazaniGwampa.repository.ImageRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URI;
import java.util.Base64;
import java.util.HashMap;
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
           int count = 0;
           while (file.hasNextLine()){
               if(count != 0){
               String line = file.nextLine();
               String[] fileContents = line.split(",");
              // publisher.publishEvent(new AccountEvent(fileContents[0],fileContents[1])); ///
                account.saveAccount(fileContents[0],fileContents[1]);
               var accountProfile = account.findAccountProfile(fileContents[0],fileContents[1]);
               var image = new Image(fileContents[2], Base64.getDecoder().decode(fileContents[3].getBytes()));
               image.setCompany(accountProfile);
               if(repository.findImageByNameAndSurname(accountProfile.getName(), accountProfile.getSurname())!=null) throw  new DuplicateImage("Image already exists");
               repository.save(image);
              }else{
                   file.nextLine();
               }
               count++;

           }
       }catch (Exception e){e.printStackTrace();}
    }

    /**
     * @param base64ImageData is the image data to be converted to an image file
     * @return an image file from bytes
     */

    @Override
    public File convertCSVDataToImage(String base64ImageData) {
        HashMap<Character, String> map = new HashMap<>();
        map.put('/', "jpeg");
        map.put('i', "png");
        map.put('R', "gif");
        map.put('U', "webp");
        if(base64ImageData == null || base64ImageData.isEmpty())throw new NullPointerException("image data cannot be null or empty");
        if(!map.containsKey(base64ImageData.charAt(0))) throw new ImageNotValid("image not valid");
        File outputFile =  new File("src/test/resources/static/image."+map.get(base64ImageData.charAt(0)));
        try(FileOutputStream fileOutputStream = new FileOutputStream(outputFile)){
             byte[] decodedBytes = Base64.getDecoder().decode(base64ImageData.getBytes());
             fileOutputStream.write(decodedBytes);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return outputFile;

    }

    /**
     * @param fileImage is the image file converted from bytes
     * @return a link to an image file
     */
    @Override
    public URI createImageLink(File fileImage) {
        if(fileImage == null) throw new NullPointerException("image cannot be null");
        URI uri = null;
        try{
            uri = fileImage.toURI();
        }catch (Exception e){
            e.printStackTrace();
        }
        return uri;
    }

    /**
     *
     * @param name is the name of the Account
     * @param surname is the surname of the Account
     * @return the FileSystemResource
     */
    public FileSystemResource gethttpImageLink(String name, String surname){
        AccountProfile acc = account.findAccountProfile(name, surname);
        var image = repository.findAllImages().stream().filter(img -> img.getCompany().equals(acc)).toList();
        if(image.isEmpty())throw new ImageNofFound("Image is not found");
        File file = convertCSVDataToImage(Base64.getEncoder().encodeToString(image.get(0).getImageData()));
        URI uri = createImageLink(file);
        return new FileSystemResource(uri.getPath());
    }

}
