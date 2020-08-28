package com.ddemo.OmStore.util;

import java.io.File;
import java.io.IOException;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;

public class ImageUtil {

    public static String saveImgeToFile(Image image, String path) {
        File outputFile = new File(path);
        if (image != null && !path.equals("")) {
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", outputFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return path;
    }

    public static String createFolderIfNotExists() {
        String fileSeparator = System.getProperty("file.separator");
        String path = "C:" + fileSeparator;
        String directoryName = path.concat("Products");

        File directory = new File(directoryName);

        if (!directory.exists()) {
            directory.mkdir();
        }
        return directoryName;

    }
}
