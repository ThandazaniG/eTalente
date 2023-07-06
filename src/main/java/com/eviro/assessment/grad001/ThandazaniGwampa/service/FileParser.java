package com.eviro.assessment.grad001.ThandazaniGwampa.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.net.URI;
@Service
public interface FileParser {
    void parseCSV(File cvsFile);
    File convertCSVDataToImage(String base64ImageData);
    URI createImageLink(File fileImage);
}
