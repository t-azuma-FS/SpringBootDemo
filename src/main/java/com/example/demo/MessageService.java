package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MessageService {


	@Autowired
	private MessageDao messageDao;

	@Autowired
	private Message message;

	public List<Message> findAll( String user ){

		return messageDao.findAll( user );

	}

	public void createMessage( String text, String userid) {

		String lastMessage;
		int header;

		lastMessage = messageDao.findLast( userid );
		if (lastMessage == "") {
			header = 1;
		} else {
			String[] tokens = lastMessage.split(" ");
			header = Integer.parseInt(tokens[0]);
			header = header + 1;
		}

		text = header + " " + text;
		message.setText( text );
		message.setUserid( userid );

		messageDao.create(message);

	}

}
