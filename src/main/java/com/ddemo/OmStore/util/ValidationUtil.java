package com.ddemo.OmStore.util;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class ValidationUtil {

    public static void reset(ImageView imageView, TextField... txt) {
        for (TextField tf : txt) {
            tf.setText("");
        }
        imageView.setImage(null);
       
    }

    public static boolean validateInputs(TextField txtName, TextField txtPrice,TextField  pkDate) {
        if (txtName.getText().isEmpty()) {
            DialogUtil.showErrorDialog("Name Field is required !");
            return false;
        }
        if (txtPrice.getText().isEmpty()) {
            DialogUtil.showErrorDialog("Price Field is required !");
            return false;
        }
        if (pkDate.getText().isEmpty()) {
            DialogUtil.showErrorDialog("Date Field is required !");
            return false;
        }
        return true;
    }
}
