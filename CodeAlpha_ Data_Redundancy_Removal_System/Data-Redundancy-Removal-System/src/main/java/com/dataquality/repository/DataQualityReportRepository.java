package com.dataquality.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dataquality.entity.DataRecord;

public interface DataQualityReportRepository extends JpaRepository<DataRecord, Integer> {

}
