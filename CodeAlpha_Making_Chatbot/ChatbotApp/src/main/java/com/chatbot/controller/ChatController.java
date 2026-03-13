package com.chatbot.controller;

import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chatbot.model.ChatHistory;
import com.chatbot.repo.ChatHistoryRepository;
import com.chatbot.service.ChatService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ChatController {
	
	private final ChatService service;
	private final ChatHistoryRepository chatHistoryRepository;
	
	@GetMapping("/sessions")
	public List<String> getSessions() {
	    return ChatHistoryRepository.findDistinctSessionIds();
	}

	@GetMapping("/history")
	public List<ChatHistory> getHistory(@RequestParam String sessionId) {
	    return chatHistoryRepository.findBySessionId(sessionId);
	}

	

    @PostMapping("/chat")
    public String chat(@RequestParam String message,
                       @RequestParam String sessionId) {

        return service.getResponse(message, sessionId);
    }
    
    @GetMapping("/export")
    public void exportPdf(@RequestParam String sessionId,
                          HttpServletResponse response) throws Exception {

        List<ChatHistory> chats = chatHistoryRepository.findBySessionId(sessionId);

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition",
                "attachment; filename=chat-session-" + sessionId + ".pdf");

        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        PDPageContentStream content = new PDPageContentStream(document, page);

        content.setFont(PDType1Font.HELVETICA_BOLD, 16);
        content.beginText();
        content.newLineAtOffset(50, 750);
        content.showText("Java & Spring Boot Expert Bot");
        content.endText();

        content.setFont(PDType1Font.HELVETICA, 12);

        float yPosition = 720;

        for (ChatHistory chat : chats) {

            if (yPosition < 100) {
                content.close();
                page = new PDPage();
                document.addPage(page);
                content = new PDPageContentStream(document, page);
                content.setFont(PDType1Font.HELVETICA, 12);
                yPosition = 750;
            }

            content.beginText();
            content.newLineAtOffset(50, yPosition);
            content.showText("User: " + chat.getUserMessage());
            content.endText();

            yPosition -= 20;

            content.beginText();
            content.newLineAtOffset(50, yPosition);
            content.showText("Bot: " + chat.getBotResponse());
            content.endText();

            yPosition -= 30;
        }

        content.close();
        document.save(response.getOutputStream());
        document.close();
    }

}
