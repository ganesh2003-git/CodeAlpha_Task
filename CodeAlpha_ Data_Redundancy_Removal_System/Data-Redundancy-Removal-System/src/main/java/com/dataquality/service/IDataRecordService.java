package com.dataquality.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.dataquality.entity.DataRecord;

public interface IDataRecordService {
	
	// Save record
		Integer saveDataRecord(DataRecord record);

		// Get all records
		List<DataRecord> getAllDataRecords();

		// Get one record
		DataRecord getOneDataRecord(Integer id);

		// Delete record
		void deleteDataRecord(Integer id);

		// Check duplicate email
		boolean isEmailExists(String email);
		
		void processCSV(MultipartFile file);
		
		//for checking or validate records
		void validateRecords();
		
		//Dashboard statistics
		Map<String, Object> getDashboardStats();
		
		

}
