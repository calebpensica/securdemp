package com.securde.bean;

import java.util.Set;
import javax.persistence.*;

@Entity(name="product") 
public class Product 
{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int productid;	
	@Column(nullable=false)
	private String name;	
	@Column(nullable=false)
	private float price;	
	@Column(nullable=false)
	private boolean status;	
	@Column
	private String imagepath;
	@ManyToMany(cascade = CascadeType.MERGE, fetch=FetchType.EAGER )
	@JoinTable(name = "product_tags", joinColumns = { @JoinColumn(name = "productid") }, inverseJoinColumns = { @JoinColumn(name = "tagid") })
	private Set<Tag> tags;
	
	
	public Product() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return productid;
	}

	public void setId(int id) {
		this.productid = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public String getImagePath()
	{
		return imagepath;
	}
	
	public void setImagePath(String filepath)
	{
		this.imagepath = filepath;
	}

}
