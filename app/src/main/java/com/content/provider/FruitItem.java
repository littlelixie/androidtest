package com.content.provider;


public class FruitItem {
	private String fruitName;
	protected FruitItem(String fruitName) {
		this.fruitName = fruitName;
	}
	
	public String getFruitName() {
		return fruitName;
	}
}  
