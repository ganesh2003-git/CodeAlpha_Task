package com.chatbot.service;

import org.springframework.stereotype.Service;

import com.chatbot.repo.chat_knowledgeRepo;

@Service
public interface ChatService {
	
	
	String getResponse(String userMessage,String sessionId);
	

	

}
