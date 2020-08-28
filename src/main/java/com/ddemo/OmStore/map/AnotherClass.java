package com.ddemo.OmStore.map;
import java.util.HashMap;

public class AnotherClass {
	static HmTest x = new HmTest();
	static HashMap<String, String> people;

	private static void someMethod() {
		people = x.getPeopleMap();
		// work with your map here...
		System.out.print(people);

	}

	public static void main(String[] args) {
		x.Test();
		someMethod();
		for (String key : people.keySet()) {
			System.out.println("key : " + key);
			System.out.println("value : " + people.get(key));
		}
	}
}