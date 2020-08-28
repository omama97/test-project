package com.ddemo.OmStore.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ddemo.OmStore.dao.ProductDaoImpl;
import com.ddemo.OmStore.dto.FxmlDto;
import com.ddemo.OmStore.entity.Product;
import com.ddemo.OmStore.util.CommonGuiUtil;
import com.ddemo.OmStore.util.Constants;
import com.ddemo.OmStore.util.DateUtil;
import com.ddemo.OmStore.util.DialogUtil;
import com.ddemo.OmStore.util.ImageUtil;
import com.ddemo.OmStore.util.ValidationUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class NewProductController implements Initializable {

	private String finalImagePath;

	@FXML
	private Button btnBack;

	@FXML
	private ImageView imgProduct;

	@FXML
	private Button btnSelectImg;

	@FXML
	private TextField txtName;

	@FXML
	private TextField txtPrice;

	@FXML
    private TextField pkDate;

	@FXML
	private Button btnSave;
	// GLOBAL VARIABLE

	private int iday;
	private int imonth;
	private int iyear;
	private String sday;
	private String Parts[];
	private String smonth;
	private String syear;
	private String date;

	@FXML
	void txtDateEvent(ActionEvent event) {

		date = pkDate.getText();
		date = date.replaceAll("\\s", "");
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date sdf = new Date(System.currentTimeMillis());

		try {
			int x = date.indexOf("-");
			
			System.out.println(x);

			String Parts[] = date.split("-");
			sday = Parts[0];
			smonth = Parts[1];
			syear = Parts[2];

			iday = Integer.parseInt(sday);
			imonth = Integer.parseInt(smonth);
			iyear = Integer.parseInt(syear);

			if (iday > 0 && iday < 32 && imonth > 0 && imonth < 13 && String.valueOf(iyear).length() == 4) {
				Parts[0] = sday;
				Parts[1] = smonth;
				Parts[2] = syear;
				date = String.join("-", Parts);
				 
				 
				pkDate.setText(date);

			} else {
				if (String.valueOf(iyear).length() == 2) {
					syear = "20" + syear;
					iyear = Integer.parseInt(syear);

					if (iday > 0 && iday < 31 && (imonth > 0 && imonth < 13) && String.valueOf(iyear).length() == 4) {
						Parts[0] = sday;
						Parts[1] = smonth;
						Parts[2] = syear;
						date = String.join("-", Parts);
						 
						 
						pkDate.setText(date);
					} else {
						System.out.println(formatter.format(sdf));
						date = formatter.format(sdf);
						pkDate.setText(date);
					}
				} else {
					System.out.println(formatter.format(sdf));
					date = formatter.format(sdf);
					pkDate.setText(date);
				}

			}

			if ((String.valueOf(iday).length() == 1) || (String.valueOf(iday).length() == 1)) {
//			sday = "0" + sday;
//			smonth = "0" + smonth;

				Parts[0] = sday;
				Parts[1] = smonth;
				Parts[2] = syear;
				date = String.join("-", Parts);
				 
				 
				pkDate.setText(date);

			}

			pkDate.setOnKeyPressed(e -> {
				if (e.getCode() == KeyCode.UP) {
					if (iday != 31) {
						iday = iday + 1;
						sday = String.valueOf(iday);

						Parts[0] = sday;
						Parts[1] = smonth;
						Parts[2] = syear;
						date = String.join("-", Parts);
						 
						 
						pkDate.setText(date);
					} else {

						iday = 0;
						iday = iday + 1;
						sday = String.valueOf(iday);

						Parts[0] = sday;
						Parts[1] = smonth;
						Parts[2] = syear;
						date = String.join("-", Parts);
						 
						 
						pkDate.setText(date);
					}
				}

				if (e.getCode() == KeyCode.DOWN) {
					if (iday != 1) {
						iday = iday - 1;
						sday = String.valueOf(iday);

						Parts[0] = sday;
						Parts[1] = smonth;
						Parts[2] = syear;
						date = String.join("-", Parts);
						 
						 
						pkDate.setText(date);
					} else {
						iday = 32;
						iday = iday - 1;
						sday = String.valueOf(iday);

						Parts[0] = sday;
						Parts[1] = smonth;
						Parts[2] = syear;
						date = String.join("-", Parts);
						 
						 
						pkDate.setText(date);

					}

				}

				if (e.getCode() == KeyCode.RIGHT) {
					if (imonth != 12) {
						imonth = imonth + 1;
						smonth = String.valueOf(imonth);

						Parts[0] = sday;
						Parts[1] = smonth;
						Parts[2] = syear;
						date = String.join("-", Parts);
						 
						 
						pkDate.setText(date);
					} else {
						imonth = 0;
						imonth = imonth + 1;
						smonth = String.valueOf(imonth);

						Parts[0] = sday;
						Parts[1] = smonth;
						Parts[2] = syear;
						date = String.join("-", Parts);
						 
						 
						pkDate.setText(date);
					}

				}

				if (e.getCode() == KeyCode.LEFT) {
					if (imonth != 1) {
						imonth = imonth - 1;
						smonth = String.valueOf(imonth);

						Parts[0] = sday;
						Parts[1] = smonth;
						Parts[2] = syear;
						date = String.join("-", Parts);
						 
						 
						pkDate.setText(date);
					}

					else {
						imonth = 13;
						imonth = imonth - 1;
						smonth = String.valueOf(imonth);

						Parts[0] = sday;
						Parts[1] = smonth;
						Parts[2] = syear;
						date = String.join("-", Parts);
						
						pkDate.setText(date);
					}
				}

			});
			

		} catch (Exception e) {
			System.out.println(formatter.format(sdf));
			date = formatter.format(sdf);
			pkDate.setText(date);
		}

	}

	@FXML
	void btnSaveEvent(ActionEvent event) {
		//validation
        boolean isValidInputs = ValidationUtil.validateInputs(txtName, txtPrice, pkDate);
        if (!isValidInputs) {
            return;
        }

		String productName = txtName.getText();
		double price = Double.parseDouble(txtPrice.getText());
		String insertionDate = pkDate.getText();
		String imagePath = "";
		if (finalImagePath != null && !finalImagePath.isEmpty()) {
			imagePath = finalImagePath;
		}
		// Encapsulate product object
		Product product = new Product();
		product.setProductName(productName);
		product.setPrice(price);
		product.setInsertionDate(insertionDate);
		//System.out.println(product.getInsertionDate().toString());
		product.setImagePath(imagePath);

		// Move to backend
		ProductDaoImpl productDaoImpl = new ProductDaoImpl(Product.class);
		try {
			int result = DialogUtil.showConfirmationDialog("Add New Product", "Did you want  to add this product ?");
			if (result == 1) {
				productDaoImpl.create(product);
				ValidationUtil.reset(imgProduct, pkDate, txtName, txtPrice);
			}
		} catch (Exception ex) {
			Logger.getLogger(NewProductController.class.getName()).log(Level.SEVERE, null, ex);
		}

	}
	
    @FXML
    void btnBackEvent(ActionEvent event) {
        try {
            FxmlDto fxmlDto = CommonGuiUtil.getFxmlDto(Constants.FXML_HOME, HOMEController.class);
            BorderPane home = (BorderPane) CommonGuiUtil.loadFxml(fxmlDto);
            Stage currentStage = (Stage) btnBack.getScene().getWindow();
            currentStage.setScene(new Scene(home));
        } catch (IOException ex) {
            Logger.getLogger(NewProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

	@FXML
	void btnSelectImgEvent(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Select Product Image");
		fileChooser.getExtensionFilters()
				.addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
		Stage currentStage = (Stage) imgProduct.getScene().getWindow();
		File file = fileChooser.showOpenDialog(currentStage);
		if (file != null) {
			FileInputStream inputStream = null;
			String imagePath = file.getPath();
			try {
				inputStream = new FileInputStream(imagePath);
				String destPath = ImageUtil.createFolderIfNotExists();
				String absolutePath = Paths.get(destPath).resolve(file.getName()).toFile().getAbsolutePath();
				setFinalImagePath(ImageUtil.saveImgeToFile(new Image(inputStream), absolutePath));
				imgProduct.setImage(new Image(new File(finalImagePath).toURI().toString()));

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	public String getFinalImagePath() {
		return finalImagePath;
	}

	public void setFinalImagePath(String finalImagePath) {
		this.finalImagePath = finalImagePath;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
