package com.chatbot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "chat_knowledge")
public class chat_knowledge {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="long_id_col")
		
		 private Long id;

	    @Column(length = 500)
	    private String question;

	    @Lob
	    @Column(columnDefinition = "LONGTEXT")
	    private String answer;

	    


}
