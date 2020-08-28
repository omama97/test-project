package com.ddemo.OmStore.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.exception.DRException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ddemo.OmStore.dao.ProductDaoImpl;
import com.ddemo.OmStore.dto.FxmlDto;
import com.ddemo.OmStore.entity.Product;
import com.ddemo.OmStore.map.DbSimpleReport;
import com.ddemo.OmStore.util.CommonGuiUtil;
import com.ddemo.OmStore.util.Constants;
import com.ddemo.OmStore.util.DateUtil;
import com.ddemo.OmStore.util.DialogUtil;
import com.ddemo.OmStore.util.ImageUtil;
import com.ddemo.OmStore.util.ValidationUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class HOMEController implements Initializable {
	private String finalImagePath;

	@FXML
	private BorderPane mainBorder;

	@FXML
	private ImageView img;

	@FXML
	private Button btnUpdateImage;

	@FXML
	private TextField txtId;

	@FXML
	private TextField txtName;

	@FXML
	private TextField txtPrice;

	@FXML
	private TextField pkDate;

	@FXML
	private Button btnDelete;

	@FXML
	private Button btnUpdate;

	@FXML
	private TableView<Product> ProductTable;

	@FXML
	private TableColumn<Product, Integer> colId;

	@FXML
	private TableColumn<Product, String> colName;

	@FXML
	private TableColumn<Product, Double> colPrice;

	@FXML
	private TableColumn<Product, String> colDate;

	@FXML
	private Button btnAddNew;

	@FXML
	private Button btnFirst;

	@FXML
	private Button btnLast;

	@FXML
	private Button btnNext;

	@FXML
	private Button btnPrevious;

	@FXML
	private Button btnExit;

	@FXML
	private Button btnPrintid;

	private Connection connection;
	private static JasperReportBuilder report;// a new report
	String id = "", product_name = "", price = "", insertion_date = "", image_path = "";

	@FXML
	void btnPrintEvent(ActionEvent event) {

		try {

			new DbSimpleReport();
			System.out.println("hiiiiii !");

			// show the report

		}

		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	private List<Product> products;
	private ObservableList<Product> data;
	private Product oldProduct;
	private ProductDaoImpl productDaoImpl;

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
			if (x < 3) {
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

						if (iday > 0 && iday < 31 && (imonth > 0 && imonth < 13)
								&& String.valueOf(iyear).length() == 4) {
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
			} else {
				System.out.println(formatter.format(sdf));
				date = formatter.format(sdf);
				pkDate.setText(date);
			}

		} catch (Exception e) {
			System.out.println(formatter.format(sdf));
			date = formatter.format(sdf);
			pkDate.setText(date);
		}

	}

	@FXML
	void btnAddNewEvent(ActionEvent event) {
		try {
			FxmlDto fxmlDto = CommonGuiUtil.getFxmlDto(Constants.FXML_NEW_PRODUCT, NewProductController.class);
			VBox vBox = (VBox) CommonGuiUtil.loadFxml(fxmlDto);
			mainBorder.setRight(null);
			mainBorder.setLeft(null);
			mainBorder.setCenter(vBox);

		} catch (IOException ex) {
			// TODO Auto-generated catch block
			Logger.getLogger(HOMEController.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	@FXML
	void btnExitEvent(ActionEvent event) {
		System.exit(0);
	}

	@FXML
	void btnFirstEvent(ActionEvent event) {
		if (!products.isEmpty()) {
			ProductTable.getSelectionModel().select(products.get(0));
		} else {
			DialogUtil.showErrorDialog(Constants.NO_PRODUCTS_FOUND);
		}

	}

	@FXML
	void btnLastEvent(ActionEvent event) {
		if (!products.isEmpty()) {
			ProductTable.getSelectionModel().select(products.get(products.size() - 1));
		} else {
			DialogUtil.showErrorDialog(Constants.NO_PRODUCTS_FOUND);
		}
	}

	@FXML
	void btnNextEvent(ActionEvent event) {
		if (!products.isEmpty()) {
			Product product = ProductTable.getSelectionModel().getSelectedItem();
			if (products.indexOf(product) == products.size() - 1) {
				DialogUtil.showInfoDialog(Constants.END_OF_TABLE);

			} else {
				ProductTable.getSelectionModel().select(products.indexOf(product) + 1);
			}

		} else {
			DialogUtil.showErrorDialog(Constants.NO_PRODUCTS_FOUND);
		}

	}

	@FXML
	void btnPreviousEvent(ActionEvent event) {
		if (oldProduct != null) {
			ProductTable.getSelectionModel().select(products.indexOf(oldProduct));
		} else {
			DialogUtil.showErrorDialog("There is no Previous product selected!");
		}
	}

	@FXML
	void btnUpdateEvent(ActionEvent event) {
		Product product = ProductTable.getSelectionModel().getSelectedItem();
		if (product == null) {
			DialogUtil.showErrorDialog("You Need to select one product from table to Update");
			return;
		} else {
			// validation
			boolean isValidInputs = ValidationUtil.validateInputs(txtName, txtPrice, pkDate);
			if (!isValidInputs) {
				return;
			}
			String productName = txtName.getText();
			double price = Double.parseDouble(txtPrice.getText());
			String insertionDate = pkDate.getText();
			String imagePath = finalImagePath;

			product.setProductName(productName);
			product.setPrice(price);
			product.setInsertionDate(insertionDate);
			if (imagePath != null) {
				product.setImagePath(imagePath);
			}
			try {
				int result = DialogUtil.showConfirmationDialog("Add New Product",
						"Did you want to Update this product ?");
				if (result == 1) {

					productDaoImpl.edit(product);
					refreshTable();
					ValidationUtil.reset(img, pkDate, txtName, txtPrice, txtId);
				}
			} catch (Exception ex) {
				Logger.getLogger(HOMEController.class.getName()).log(Level.SEVERE, null, ex);
			}

		}

	}

	@FXML
	void btnUpdateImageEvent(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Select Employee Image");
		fileChooser.getExtensionFilters()
				.addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
		Stage currentStage = (Stage) img.getScene().getWindow();
		File file = fileChooser.showOpenDialog(currentStage);
		if (file != null) {
			FileInputStream inputStream = null;
			String imagePath = file.getPath();
			try {
				inputStream = new FileInputStream(imagePath);
				String destPath = ImageUtil.createFolderIfNotExists();
				String absolutePath = Paths.get(destPath).resolve(file.getName()).toFile().getAbsolutePath();
				finalImagePath = ImageUtil.saveImgeToFile(new Image(inputStream), absolutePath);
				img.setImage(new Image(new File(finalImagePath).toURI().toString()));

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	@FXML
	void btnDeleteEvent(ActionEvent event) {
		Product product = ProductTable.getSelectionModel().getSelectedItem();
		if (product == null) {
			DialogUtil.showErrorDialog("You Need to select one product from table to Delete!");
			return;
		} else {
			try {
				int result = DialogUtil.showConfirmationDialog("Add New Product",
						"Did you want to Delete this product ?");
				if (result == 1) {
					productDaoImpl.remove(product.getId());
					refreshTable();
					ValidationUtil.reset(img, pkDate, txtName, txtPrice, txtId);
				}
			} catch (Exception ex) {
				Logger.getLogger(HOMEController.class.getName()).log(Level.SEVERE, null, ex);
			}

		}
	}

	private void refreshTable() {
		ProductTable.getItems().clear();
		data.removeAll(products);
		fillProductTable();
	}

	private void initButtonsIcon() {
		btnAddNew.setGraphic(new ImageView(new Image(Constants.ADD_PRODUCT)));
		btnFirst.setGraphic(new ImageView(new Image(Constants.FIRST)));
		btnLast.setGraphic(new ImageView(new Image(Constants.LAST)));
		btnNext.setGraphic(new ImageView(new Image(Constants.NEXT)));
		btnPrevious.setGraphic(new ImageView(new Image(Constants.PREVIOUS)));
		btnExit.setGraphic(new ImageView(new Image(Constants.EXIT)));
		btnDelete.setGraphic(new ImageView(new Image(Constants.DELETE)));
		btnUpdate.setGraphic(new ImageView(new Image(Constants.UPDATE)));
	}

	private void initTable() {

		colId.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
		colName.setCellValueFactory(new PropertyValueFactory<Product, String>("productName"));
		colPrice.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
		colDate.setCellValueFactory(new PropertyValueFactory<Product, String>("insertionDate"));

	}

	private void fillAllFields(Product product) {
		txtId.setText(String.valueOf(product.getId()));
		txtName.setText(product.getProductName());
		txtPrice.setText(String.valueOf(product.getPrice()));
		pkDate.setText(String.valueOf(product.getInsertionDate()));

		img.setImage(new Image(new File(product.getImagePath()).toURI().toString()));

	}

	private void fillProductTable() {
		try {
			initTable();
			// get All Data
			products = productDaoImpl.findall();
			data = FXCollections.observableList(products);
			ProductTable.setItems(data);
			ProductTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, product) -> {
				if (product != null) {
					fillAllFields(product);
					oldProduct = oldSelection;
				}
			});

		} catch (Exception ex) {
			Logger.getLogger(HOMEController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// TODO Auto-generated method stub
		try {
			productDaoImpl = new ProductDaoImpl(Product.class);
			products = new ArrayList<>();
			initButtonsIcon();
			fillProductTable();
		} catch (Exception ex) {
			DialogUtil.showInfoDialog("You have To Open Connection with database\nServer maybe is close!");
			System.exit(0);
		}

	}
}
