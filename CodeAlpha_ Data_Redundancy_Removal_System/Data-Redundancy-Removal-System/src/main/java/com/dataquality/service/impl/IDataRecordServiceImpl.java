package com.dataquality.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dataquality.entity.DataRecord;
import com.dataquality.repository.DataRecordRepository;
import com.dataquality.service.IDataRecordService;

@Service
public class IDataRecordServiceImpl implements IDataRecordService{

	@Autowired
	private DataRecordRepository repo;

	@Override
	public Integer saveDataRecord(DataRecord record) {
		return repo.save(record).getId();
	}

	@Override
	public List<DataRecord> getAllDataRecords() {
		List<DataRecord> list = repo.findAll();
		list.sort((record1,record2)->record1.getId()-record2.getId());
		return list;
	}

	@Override
	public DataRecord getOneDataRecord(Integer id) {
		// TODO Auto-generated method stub
		return repo.findById(id).orElse(null);
	}

	@Override
	public void deleteDataRecord(Integer id) {
		repo.deleteById(id);
		
	}

	@Override
	public boolean isEmailExists(String email) {
		
		return repo.existsByEmail(email);
	}

	@Override
	public void processCSV(MultipartFile file) {
		 try (
		            BufferedReader reader = new BufferedReader(
		            		
		                    new InputStreamReader(file.getInputStream()));

		            CSVParser csvParser = new CSVParser(reader,
		            		
		                    CSVFormat.DEFAULT.withFirstRecordAsHeader());
		        ) {

		            for (CSVRecord csvRecord : csvParser) {

		                String name = csvRecord.get("name");
		                String email = csvRecord.get("email");
		                String phone = csvRecord.get("phone");

		                DataRecord record = new DataRecord();

		                record.setName(name);
		                record.setEmail(email);
		                record.setPhone(phone);
		                record.setStatus("NEW");

		                repo.save(record);
		            }
		            
		            validateRecords();

		        } catch (Exception e) {
		            throw new RuntimeException("CSV file processing failed", e);
		        }
		
	}

	@Override
	public void validateRecords() {
		
		System.out.println("Validating records...");
		
		 List<DataRecord> records = repo.findAll();

		    Set<String> emailSet = new HashSet<>();

		    for (DataRecord r : records) {

		        String email = r.getEmail();
		        String phone = r.getPhone();

		        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {

		            r.setStatus("INVALID_EMAIL");

		        }
		        else if (!phone.matches("\\d{10}")) {

		            r.setStatus("INVALID_PHONE");

		        }
		        else if (emailSet.contains(email)) {

		            r.setStatus("DUPLICATE");

		        }
		        else {

		            r.setStatus("VALID");
		            emailSet.add(email);

		        }

		        repo.save(r);
		    }
		
	}

	@Override
	public Map<String, Object> getDashboardStats() {
		 List<DataRecord> records = repo.findAll();

		    int totalRecords = records.size();
		    int validRecords = 0;
		    int invalidEmail = 0;
		    int invalidPhone = 0;
		    int duplicates = 0;

		    for (DataRecord r : records) {

		        String status = r.getStatus();

		        if ("VALID".equals(status))
		            validRecords++;

		        else if ("INVALID_EMAIL".equals(status))
		            invalidEmail++;

		        else if ("INVALID_PHONE".equals(status))
		            invalidPhone++;

		        else if ("DUPLICATE".equals(status))
		            duplicates++;
		    }

		    int qualityScore = 0;

		    if (totalRecords > 0)
		        qualityScore = (validRecords * 100) / totalRecords;

		    Map<String,Object> map = new HashMap<>();

		    map.put("totalRecords", totalRecords);
		    map.put("validRecords", validRecords);
		    map.put("invalidEmail", invalidEmail);
		    map.put("invalidPhone", invalidPhone);
		    map.put("duplicates", duplicates);
		    map.put("qualityScore", qualityScore);

		    return map;
	}
	
	
	

}
