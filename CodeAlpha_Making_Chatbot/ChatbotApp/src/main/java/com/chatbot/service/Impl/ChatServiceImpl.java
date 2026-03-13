package com.chatbot.service.Impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.chatbot.model.ChatHistory;
import com.chatbot.model.chat_knowledge;
import com.chatbot.repo.ChatHistoryRepository;
import com.chatbot.repo.chat_knowledgeRepo;
import com.chatbot.service.ChatService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService{

	 private final chat_knowledgeRepo knowledgeRepo;
	    private final ChatHistoryRepository historyRepo;
	    @PersistenceContext
	    private EntityManager entityManager;

	    
	    @Override
	    public String getResponse(String userMessage, String sessionId) {

	    	chat_knowledge result = null;

	        try {
	            // Step 1: try FULLTEXT search
	            result = searchBest(cleanQuery(userMessage));

	        } catch (Exception e) {

	            // Step 2: fallback to keyword scoring
	            result = findBestByKeyword(userMessage);
	        }

	        String answer;

	        if (result != null) {
	            answer = formatAnswer(result.getAnswer());
	        } else {
	            answer = "⚠️ Sorry, I couldn't find a matching answer.";
	        }

	        ChatHistory history = new ChatHistory();
	        history.setSessionId(sessionId);
	        history.setUserMessage(userMessage);
	        history.setBotResponse(answer);
	        history.setTimestamp(LocalDateTime.now());

	        historyRepo.save(history);

	        return answer;
			
	    }
	    
	    private chat_knowledge findBestByKeyword(String userMessage){

	        List<chat_knowledge> list = knowledgeRepo.findAll();

	        chat_knowledge best = null;
	        int bestScore = 0;

	        for(chat_knowledge k : list){

	            int score = calculateScore(userMessage, k.getQuestion());

	            if(score > bestScore){
	                bestScore = score;
	                best = k;
	            }
	        }

	        return best;
	    }
	    
	    private int calculateScore(String user, String question){

	        user = user.toLowerCase();
	        question = question.toLowerCase();

	        String[] userWords = user.split(" ");
	        String[] qWords = question.split(" ");

	        int score = 0;

	        for(String u : userWords){

	            if(u.length() < 3) continue;

	            for(String q : qWords){

	                if(u.equals(q)){
	                    score += 3;
	                }

	                else if(q.contains(u) || u.contains(q)){
	                    score += 1;
	                }
	            }
	        }

	        return score;
	    }
	    
	    
	    private chat_knowledge searchBest(String query) {

	    	String sql = """
	    	        SELECT *
	    	        FROM chat_knowledge
	    	        WHERE MATCH(question)
	    	        AGAINST (:q IN BOOLEAN MODE)
	    	        LIMIT 1
	    	        """;

	    	    return (chat_knowledge) entityManager
	    	            .createNativeQuery(sql, chat_knowledge.class)
	    	            .setParameter("q", query)
	    	            .getSingleResult();
	    }

	   
	    
	    
	    private String formatAnswer(String text){

	        if(text == null) return "I couldn't find a good answer.";

	        // remove extra spaces
	        text = text.replaceAll("\\s+", " ").trim();

	        // limit answer length
	        if(text.length() > 250){
	            text = text.substring(0,250);
	        }

	        // split into sentences
	        String[] sentences = text.split("\\.");

	        StringBuilder result = new StringBuilder();

	        result.append("📘 Answer:\n\n");

	        for(int i=0; i<sentences.length && i<2; i++){
	            result.append("• ").append(sentences[i].trim()).append(".\n");
	        }

	        result.append("\n💡 Ask more if you want details.");

	        return result.toString();
	    }
	    
	    private String cleanQuery(String query){

	        return query
	                .toLowerCase()
	                .replaceAll("[^a-zA-Z ]","")
	                .replaceAll("\\b(what|is|the|a|an|define|explain|why|tell|about)\\b","")
	                .trim();
	    }
	    
}
