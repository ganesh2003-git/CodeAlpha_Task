package com.dataquality.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data

@Table(name="data_records")
public class DataQualityReport {
	@Column(name="tol_rec_total_record_col")
    private long totalRecords;
	@Column(name="tol_rec_valid_record_col")
    private long validRecords;
	@Column(name="tol_rec_valid_email_col")
    private long invalidEmail;
	@Column(name="tol_rec_valide_phone_col")
    private long invalidPhone;
	@Column(name="tol_rec_duplicates_col")
    private long duplicates;
    @Column(name="tol_rec_quality_score_col")
    private double qualityScore;

}
