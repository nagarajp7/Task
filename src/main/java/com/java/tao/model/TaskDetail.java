package com.java.tao.model;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Component
@Data
public class TaskDetail {
	
	@NotNull
	@NotBlank(message = "taskId is mandatory")
	Integer taskId ;
	
	@NotNull
	@NotBlank(message = "title is mandatory")
    String title ;
    
	@NotNull
	@NotBlank(message = "description is mandatory")
    String description ;
    
	@NotNull
	@NotBlank(message = "dueDate is mandatory")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    Date dueDate ;
	
	String status;
    
}
