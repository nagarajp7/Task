package com.java.tao.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name="ASSIGNMENT")
@Entity
@Data
public class Assignment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Integer assignId;
	
	Integer taskId;
	
	String userId;
	
}
