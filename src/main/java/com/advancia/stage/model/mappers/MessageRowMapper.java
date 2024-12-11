package com.advancia.stage.model.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.advancia.stage.model.Message;
import com.advancia.stage.model.MessageStatus;

public class MessageRowMapper implements RowMapper<Message> {

	  @Override
	  public Message mapRow(ResultSet resultSet, int rowNumber) throws SQLException {

	    Message message = new Message(
	      resultSet.getLong("message_id"),
	      resultSet.getInt("recipient_id"),
	      resultSet.getString("subject"),
	      resultSet.getString("content"),
	      MessageStatus.valueOf(resultSet.getString("status"))
	    );
	    return message;
	  }
	}