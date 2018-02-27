package com.securde.bean;

import javax.persistence.*;
import java.util.Set;

public class Cart 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int cartid;
	
	@ManyToMany(cascade = CascadeType.MERGE, fetch=FetchType.EAGER )
	@JoinTable(name = "cart_items", joinColumns = { @JoinColumn(name = "cartid") }, inverseJoinColumns = { @JoinColumn(name = "citemid") })
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

	public void setItems(Set<CartItem> items) {
		this.items = items;
	}

}
