package com.ddemo.OmStore.util;


import java.io.IOException;

import com.ddemo.OmStore.dto.FxmlDto;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class CommonGuiUtil {
    public static Parent loadFxml(FxmlDto fxmlDto) throws IOException {
        String fxml = fxmlDto.getFxml();
        Object owner = fxmlDto.getOwner();
        FXMLLoader loader = new FXMLLoader(owner.getClass().getResource(fxml));
        Parent pane = loader.load();
        fxmlDto.setLoader(loader);
        return pane;
    }

    public static FxmlDto getFxmlDto(String fileName, Class clazz) throws IOException {
        FxmlDto mainFxmlDto = new FxmlDto();
        mainFxmlDto.setFxml(fileName);
        mainFxmlDto.setOwner(clazz);
        return mainFxmlDto;
    }
}
