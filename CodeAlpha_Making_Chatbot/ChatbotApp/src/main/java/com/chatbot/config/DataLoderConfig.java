package com.chatbot.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.chatbot.service.PdfLoaderService;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class DataLoderConfig {
	
	 private final PdfLoaderService loader;

	    @Bean
	    CommandLineRunner loadData() {
	        return args -> {
	        	loader.loadPdf("java programming.pdf");
	        	loader.loadPdf("SpringBoot notebook.pdf");
	        };
	    }
}
