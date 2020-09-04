package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class MessageDao {


	@Autowired
	private JdbcTemplate template;


	private static final String SELECT_ALL = "SELECT userid, text, datetime FROM message WHERE message.userid=? ORDER BY datetime";

	public List<Message> findAll(String userid) {
		RowMapper<Message> mapper = new BeanPropertyRowMapper<Message>(Message.class);
		return this.template.query( SELECT_ALL, mapper, userid );
	}

	private static final String SELECT_LAST = "SELECT userid, text, datetime FROM message ORDER BY datetime DESC LIMIT 1";
	public String findLast(String userid) {

		List<Message>  messageList;
		String message;

		RowMapper<Message> mapper = new BeanPropertyRowMapper<Message>(Message.class);

		messageList = this.template.query( SELECT_LAST, mapper );
		if( messageList.size() == 0 ) {
			message = "";
		}else {
			message = messageList.get(0).getText();
		}
		return message;
	}

	private static final String MESSAGEINSERT = "INSERT INTO message( userid, text) values(?,?) ";

	public void create( Message message ) {
		this.template.update(MESSAGEINSERT, message.getUserid(), message.getText());
	}

}
