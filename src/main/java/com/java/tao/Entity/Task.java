package com.java.tao.Entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Table(name="TASK")
@Entity
@Data
public class Task {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Integer taskId;
	
	String title ;
	
	String description ;
	
	Date dueDate ;
	
	Date startDate;
	
	Date completedDate;
	
	String status;
	
	Integer progress;
	
	Integer userId;

}
