package com.ddemo.OmStore.dto;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
//pojo class
public class FxmlDto {

    private String fxml;
    private Object owner;
    private FXMLLoader loader;
    private Stage stage;

    public String getFxml() {
        return fxml;
    }

    public void setFxml(String fxml) {
        this.fxml = fxml;
    }

    public Object getOwner() {
        return owner;
    }

    public void setOwner(Object owner) {
        this.owner = owner;
    }

    public FXMLLoader getLoader() {
        return loader;
    }

    public void setLoader(FXMLLoader loader) {
        this.loader = loader;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;

    }


}
