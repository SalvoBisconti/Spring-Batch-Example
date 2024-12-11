package com.advancia.stage.model;

public record Message(
		  long id,
		  int recipientId,
		  String subject,
		  String content,
		  MessageStatus status
		) {
		}

