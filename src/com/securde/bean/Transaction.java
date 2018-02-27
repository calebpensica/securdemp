package com.securde.bean;

import javax.persistence.*;

@Entity(name="transaction")
public class Transaction 
{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int transactionid;
	
	@Column(nullable=false)
	private float sum;
	
	@Column(nullable=false)
	private String deliveryAdd;
	
	@Column(nullable=false)
	private String timeOrder;
	
	@Column
	private String timeReceived;
	
	@Column(nullable=false)
	private Client buyer;
	
	@OneToOne(mappedBy = "transaction", cascade = CascadeType.ALL, 
            fetch = FetchType.LAZY, optional = false)
	private Cart cart;
	
	public Transaction() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return transactionid;
	}

	public void setId(int id) {
		this.transactionid = id;
	}

	public float getSum() {
		return sum;
	}

	public void setSum(float sum) {
		this.sum = sum;
	}

	public String getDeliveryAdd() {
		return deliveryAdd;
	}

	public void setDeliveryAdd(String deliveryAdd) {
		this.deliveryAdd = deliveryAdd;
	}

	public String getTimeOrder() {
		return timeOrder;
	}

	public void setTimeOrder(String timeOrder) {
		this.timeOrder = timeOrder;
	}

	public String getTimeReceived() {
		return timeReceived;
	}

	public void setTimeReceived(String timeReceived) {
		this.timeReceived = timeReceived;
	}

	public Client getBuyer() {
		return buyer;
	}

	public void setBuyer(Client buyer) {
		this.buyer = buyer;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

}
