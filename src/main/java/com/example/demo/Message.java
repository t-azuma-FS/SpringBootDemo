package com.example.demo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.validation.constraints.Size;

import org.springframework.stereotype.Repository;

@Repository
public class Message implements Serializable {
	final long serialVersionUID = 1L;

	@Size(min=1, max=128)
	private String text ;

	private Timestamp datetime;

	@Size(min=1, max=16)
	private String userid;

	public Message() {

	}

	public void setUserid( String userid) {
		this.userid=userid;
	}

	public String getUserid() {
		return (this.userid);
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return(this.text);
	}

	public void setDatetime(Timestamp datetime) {
		this.datetime = datetime;
	}

	public Timestamp getDatetime() {
		return( this.datetime);
	}

}
