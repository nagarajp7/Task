package com.java.tao.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import lombok.Data;
@Component
@Data
public class UserDetail {
	
	@NotNull
	@NotBlank(message = "User Id is mandatory")
	Integer userId;

}
