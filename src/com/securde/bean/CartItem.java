package com.securde.bean;

import javax.persistence.*;

@Entity(name="cartitem")
public class CartItem 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int citemid;	
	@OneToOne(cascade = CascadeType.ALL)	
	private Product product;	
	@Column(nullable=false)
	private int quantity;	
	@ManyToOne
    @JoinColumn(name="cartid", nullable=false)
	private Cart cart;

	public CartItem() {
		// TODO Auto-generated constructor stub
	}

	public int getCitemid() {
		return citemid;
	}

	public void setCitemid(int citemid) {
		this.citemid = citemid;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

}
