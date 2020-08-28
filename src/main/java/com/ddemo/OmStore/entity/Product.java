package com.ddemo.OmStore.entity;


import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "products")

public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "product_name")
    private String productName;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "price")
    private Double price;
    @Column(name = "insertion_date")
    private String insertionDate;
    @Column(name = "image_path")
    private String imagePath;
    
    
    public static String ID="id";
    public static String Product_Name="product_name";
    public static String Price="price";                                                                                                                                                                                                                               
	public static String Insertion_Date="insertion_date";
	public static String Image_Path="image_path";
    
    
//	public static HashMap<String, String> HmProduct ;
//	
//	public HashMap<String, String> getProductMap() {
//        return HmProduct;
//   }
//	
//	
//	
//	public static void Test() {
//		
//		HmProduct.put("ID",ID );
//		HmProduct.put("Product_Name",Product_Name );
//		HmProduct.put("Price",Price );
//		HmProduct.put("Insertion_Date",Insertion_Date );
//		HmProduct.put("Image_Path",Image_Path );
//		System.out.println("The Value is: " + HmProduct.get("id"));
//
//
//	}
	

    

    
    	
    	
    	
    public Product() {
    	//Test();
    }

    public Product(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getInsertionDate() {
        return insertionDate;
    }

    public void setInsertionDate(String insertionDate) {
        this.insertionDate = insertionDate;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.demo.storedemo.entity.Product[ id=" + id + " ]";
    }
    
}

