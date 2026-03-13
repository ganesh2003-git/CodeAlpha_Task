package com.chatbot.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;
@Entity
@Data
@Table(name="chat_History")
public class ChatHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name="his_id_col")
	
	 private Long id;
	@Column(name="session_id_col")
	    private String sessionId;
	@Column(name="user_message_col")
	    private String userMessage;

		@Lob
		@Column(columnDefinition = "LONGTEXT")
	    private String botResponse;
	    @Column(name="Local_time_col")
	    private LocalDateTime timestamp;

}
