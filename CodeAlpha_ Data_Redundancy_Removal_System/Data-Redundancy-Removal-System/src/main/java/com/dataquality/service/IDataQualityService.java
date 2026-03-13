package com.dataquality.service;

import com.dataquality.entity.DataQualityReport;

public interface IDataQualityService {
	

	//generate data dataset
	DataQualityReport generateReport();
	
	//byte[] download clean dataset
	byte[] downloadCleanCSV();
}
