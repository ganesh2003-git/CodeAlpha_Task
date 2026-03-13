package com.dataquality.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dataquality.entity.DataRecord;

public interface DataRecordRepository extends JpaRepository<DataRecord, Integer> {
	
	
	 boolean existsByEmail(String email);
	 
	 @Query(value="SELECT COUNT(*) FROM data_record", nativeQuery=true)
	    int totalRecords();

	    @Query(value="SELECT COUNT(*) FROM data_record WHERE email NOT REGEXP '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$'", nativeQuery=true)
	    int invalidEmail();

	    @Query(value="SELECT COUNT(*) FROM data_record WHERE phone NOT REGEXP '^[0-9]{10}$'", nativeQuery=true)
	    int invalidPhone();
}
