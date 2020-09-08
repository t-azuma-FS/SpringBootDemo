package com.example.demo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class MessageForm {


	@NotNull
	@Size(min=1, max=128)
	private String text;

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
