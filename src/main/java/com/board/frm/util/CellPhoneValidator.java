package com.board.frm.util;

import java.text.MessageFormat;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.board.dto.MemberDto;


@Component
public class CellPhoneValidator implements ConstraintValidator<CellPhoneConstraint, String> {
	@Override
	public boolean isValid(String cell_no, ConstraintValidatorContext context) {
		
		String reg ="^[0-9]{11,15}$";
		
		if(!Pattern.matches(reg, cell_no))
		{
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(
					MessageFormat.format("하이픈을 제외한 11~15자리 숫자만 허용됩니다.", cell_no)).addConstraintViolation();
			return false;
		} 
		else 
		{
			return true;
		}
	}

}
