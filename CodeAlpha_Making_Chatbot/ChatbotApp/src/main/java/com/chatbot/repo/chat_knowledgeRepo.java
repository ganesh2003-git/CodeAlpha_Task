package com.chatbot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.chatbot.model.chat_knowledge;

public interface chat_knowledgeRepo extends JpaRepository<chat_knowledge, Long> {
	
}
