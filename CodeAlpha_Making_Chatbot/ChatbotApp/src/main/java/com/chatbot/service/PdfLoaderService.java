package com.chatbot.service;

import java.io.InputStream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

import com.chatbot.model.chat_knowledge;
import com.chatbot.repo.chat_knowledgeRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PdfLoaderService {
	
	private final chat_knowledgeRepo repo;
	
	 public void loadPdf(String fileName) throws Exception {

		 InputStream inputStream = getClass()
	                .getClassLoader()
	                .getResourceAsStream("pdf/" + fileName);

	        if (inputStream == null) {
	            throw new RuntimeException("PDF file not found: " + fileName);
	        }

	        PDDocument document = PDDocument.load(inputStream);
	        PDFTextStripper stripper = new PDFTextStripper();
	        String text = stripper.getText(document);
	        document.close();

	        String[] lines = text.split("\\r?\\n");

	        for (int i = 0; i < lines.length; i++) {

	            String line = lines[i].trim();

	            if (line.endsWith("?")) {

	                String question = line;
	                StringBuilder answer = new StringBuilder();

	                int j = i + 1;
	                while (j < lines.length && !lines[j].endsWith("?")) {
	                    answer.append(lines[j]).append(" ");
	                    j++;
	                }

	                chat_knowledge knowledge = new chat_knowledge();
	                knowledge.setQuestion(question.toLowerCase());
	                knowledge.setAnswer(answer.toString());

	                repo.save(knowledge);
	            }
	        }
	    }
}
