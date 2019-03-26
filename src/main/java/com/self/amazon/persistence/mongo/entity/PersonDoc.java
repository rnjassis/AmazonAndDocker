package com.self.amazon.persistence.mongo.entity;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "texts")
public class PersonDoc {
	
	@Id
	private String id;
	
	private String text;
	
	public PersonDoc () {
		
	}

	public PersonDoc(String text) {
		super();
		this.text = text;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}
