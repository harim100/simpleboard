package com.board.util;

import java.text.MessageFormat;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;


@Component
public class CellPhoneValidator implements ConstraintValidator<CellPhoneConstraint, String> {
	 
	@Override
	public boolean isValid(String cellNum, ConstraintValidatorContext context) {
		
		String reg ="^[0-9]{11,15}$";
		
		if(cellNum.length() > 0 && !Pattern.matches(reg, cellNum))
		{
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(
					MessageFormat.format("하이픈을 제외한 11~15자리 숫자만 허용됩니다.", cellNum)).addConstraintViolation();
			return false;
		} 
		else 
		{
			return true;
		}
	}

}
