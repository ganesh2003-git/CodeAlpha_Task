package com.dataquality.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="data_records")
public class DataRecord {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="data_rec_id_col")
	private Integer id;
	@Column(name="data_rec_name_col")
	private String name;
	@Column(name="data_rec_email_col")
	private String email;
	@Column(name="data_rec_phone_col")
	private String phone;
	@Column(name="data_rec_status_col")
	private String status;

}
