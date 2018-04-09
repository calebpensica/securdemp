package com.securde.bean;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity(name="cart")
public class Cart 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int cartid;
	
	
	public Cart() {
		// TODO Auto-generated constructor stub
	}	

	public int getCartid() {
		return cartid;
	}

	public void setCartid(int cartid) {
		this.cartid = cartid;
	}


}
