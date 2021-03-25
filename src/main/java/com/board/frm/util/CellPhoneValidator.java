package com.board.frm.util;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;


@Component
public class CellPhoneValidator implements ConstraintValidator<CellPhoneConstraint, String> {
	@Override
	public boolean isValid(String cell_no, ConstraintValidatorContext context) {
		
		String reg ="^[0-9]{11,15}$";
		
		if(cell_no.length() > 0 && !Pattern.matches(reg, cell_no))
		{
			return false;
		} 
		else 
		{
			return true;
		}
	}

}
