package com.java.tao.model;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class Statistics {
	
	Integer totalTask;
	Integer completedTask;
	Double percentage;

}
