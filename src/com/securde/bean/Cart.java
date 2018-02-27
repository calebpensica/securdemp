package com.securde.bean;

import javax.persistence.*;
import java.util.Set;

public class Cart 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int cartid;
	
	@OneToMany(mappedBy = "cart")
	private Set<CartItem> items;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "transactionid")
	private Transaction transaction;
	
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

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction trans) {
		this.transaction = trans;
	}

}
