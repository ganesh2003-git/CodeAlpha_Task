package com.chatbot.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.chatbot.model.ChatHistory;
@Repository
public interface ChatHistoryRepository extends JpaRepository<ChatHistory, Long> {
	


	
	 List<ChatHistory> findBySessionId(String sessionId);

	 static @Query("SELECT DISTINCT c.sessionId FROM ChatHistory c")
		
	    List<String> findDistinctSessionIds() {
			// TODO Auto-generated method stub
			return null;
		}

}
