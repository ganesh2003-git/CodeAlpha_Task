package com.dataquality.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dataquality.entity.DataQualityReport;
import com.dataquality.service.IDataQualityService;

@RestController
@RequestMapping("/quality")
public class DataQualityController {
	
	@Autowired
	private IDataQualityService service;
	

	// Data Quality Report
    @GetMapping("/report")
    public DataQualityReport getReport() {

        return service.generateReport();
    }

    // Download Clean CSV
    @GetMapping("/download-clean")
    public ResponseEntity<byte[]> downloadCleanCSV() {

        byte[] data = service.downloadCleanCSV();

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=clean_dataset.csv")
                .body(data);
    }
}
