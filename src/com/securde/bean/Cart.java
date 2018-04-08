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
	
	@OneToMany(mappedBy = "cart")
	private Set<CartItem> items;
	
	public Cart() {
		// TODO Auto-generated constructor stub
	}	

	public int getCartid() {
		return cartid;
	}

	public void setCartid(int cartid) {
		this.cartid = cartid;
	}

	public Set<CartItem> getItems() {
		return items;
	}

	public void setItems(HashSet<CartItem> items) {
		this.items = items;
	}
	
	public boolean addCartItem(CartItem item) {
		return items.add(item);
	}

}
