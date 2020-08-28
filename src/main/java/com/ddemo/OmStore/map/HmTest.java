package com.ddemo.OmStore.map;
import java.util.HashMap;





public class HmTest {
	private static HashMap<String, String> HmProduct ;

	    public HashMap<String, String> getPeopleMap() {
	         return HmProduct;
	    }
	    
	
	public static void Test() {
		// TODO Auto-generated method stub
		String id="12345";
		String productName="dell";
		HmProduct = new HashMap<String, String>();
		HmProduct.put("id",id );
		HmProduct.put("productName",productName);
		System.out.println(HmProduct);
		System.out.println("The Value is: " + HmProduct.get("id"));
		System.out.println("----------------------------");

			}
		 

	}

