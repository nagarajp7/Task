package com.java.tao.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import lombok.Data;
@Component
@Data
public class TaskProgress {
	
	@NotNull
	@NotBlank
	Integer Progress;

}
