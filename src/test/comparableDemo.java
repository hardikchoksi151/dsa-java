package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class comparableDemo {

	public static void main(String[] args) {
	
		List<laptop> laps = new ArrayList<>();
		laps.add(new laptop("acer", 4, 40000));
		laps.add(new laptop("asus", 16, 64000));
		laps.add(new laptop("apple", 8, 70000));
		
		Collections.sort(laps);
		
		for (laptop i: laps) {
			System.out.println(i);
		}
	}

}

class laptop implements Comparable<laptop>{
	private String brand;
	private int ram;
	private int price;
	
	public laptop(String brand, int ram, int price) {
		this.brand = brand;
		this.ram = ram;
		this.price = price;
	}
	
	public String getBrand() {
		return brand;
	}
	public int getRam() {
		return ram;
	}
	public int getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return "laptop [brand=" + brand + ", ram=" + ram + ", price=" + price + "]";
	}

	@Override
	public int compareTo(laptop o) {
		
		return (this.getRam() > o.getRam() ? 1 : -1);
			
	}
	
	
	
	
}