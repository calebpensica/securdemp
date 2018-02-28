package com.securde.bean;

import javax.persistence.*;

@Entity(name="tag")
public class Tag 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int tagid;
	@Column(nullable=false)
	private String tag;
	
	public Tag() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return tagid;
	}

	public void setId(int id) {
		this.tagid = id;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

}
