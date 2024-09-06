package com.agendaki.scheduling.services.template;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.Base64;

public class ImageService {
    private static final String RESOURCE_DIRECTORY = "scheduling/src/main/resources/static/images/";

    public Boolean saveImageIntoArchive(String imageToBase64, String uuid){
        byte[] imageBytes = Base64.getDecoder().decode(imageToBase64);
        File file = Paths.get(RESOURCE_DIRECTORY, uuid+".png").toFile();
        file.getParentFile().mkdirs();
        try (OutputStream os = new FileOutputStream(file)) {
            os.write(imageBytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}