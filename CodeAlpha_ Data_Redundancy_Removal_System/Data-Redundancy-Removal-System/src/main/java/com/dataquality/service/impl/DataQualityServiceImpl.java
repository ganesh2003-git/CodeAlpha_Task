package com.dataquality.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dataquality.entity.DataQualityReport;
import com.dataquality.entity.DataRecord;
import com.dataquality.repository.DataQualityReportRepository;
import com.dataquality.service.IDataQualityService;

@Service
public class DataQualityServiceImpl implements IDataQualityService {

	@Autowired
	private DataQualityReportRepository repo;
	
	@Override
	public DataQualityReport generateReport() {
		List<DataRecord> records = repo.findAll();

	    int totalRecords = records.size();
	    int validRecords = 0;
	    int invalidEmail = 0;
	    int invalidPhone = 0;
	    int duplicates = 0;

	    Set<String> emailSet = new HashSet<>();

	    for(DataRecord r : records){

	        String email = r.getEmail();
	        String phone = r.getPhone();

	        boolean emailValid = email != null &&
	                email.matches("^[A-Za-z0-9+_.-]+@(.+)$");

	        boolean phoneValid = phone != null &&
	                phone.matches("\\d{10}");

	        if(!emailValid)
	            invalidEmail++;

	        if(!phoneValid)
	            invalidPhone++;

	        if(emailSet.contains(email))
	            duplicates++;
	        else
	            emailSet.add(email);

	        if(emailValid && phoneValid)
	            validRecords++;
	    }

	    int qualityScore = 0;

	    if(totalRecords > 0)
	        qualityScore = (validRecords * 100) / totalRecords;

	    DataQualityReport report = new DataQualityReport();

	    report.setTotalRecords(totalRecords);
	    report.setValidRecords(validRecords);
	    report.setInvalidEmail(invalidEmail);
	    report.setInvalidPhone(invalidPhone);
	    report.setDuplicates(duplicates);
	    report.setQualityScore(qualityScore);

	    return report;
	}

	@Override
	public byte[] downloadCleanCSV() {
		  List<DataRecord> records = repo.findAll();

	        ByteArrayOutputStream out = new ByteArrayOutputStream();

	        try (
	                CSVPrinter printer = new CSVPrinter(
	                        new PrintWriter(out),
	                        CSVFormat.DEFAULT.withHeader("name", "email", "phone"))) {

	            for (DataRecord r : records) {

	                if ("VALID".equals(r.getStatus())) {

	                    printer.printRecord(
	                            r.getName(),
	                            r.getEmail(),
	                            r.getPhone());
	                }
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return out.toByteArray();
	    }
	}

	

