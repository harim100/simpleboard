package com.board.util;

import java.text.MessageFormat;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import com.board.dao.MemberDao;

import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class IdDuplicationValidator implements ConstraintValidator<IdDuplicationConstraint, String> {
	 
	private MemberDao memberDao;
	
	@Override
	public void initialize(IdDuplicationConstraint idDuplicationConstraint) {
	  
	}
	
	@Override
	public boolean isValid(String id, ConstraintValidatorContext context) {
		int isSameId = memberDao.member_id_check(id);
		if(isSameId > 0)
		{
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(
					MessageFormat.format("이미 존재하는 아이디 입니다.", id)).addConstraintViolation();
			return false;
		} 
		else 
		{
			return true;
		}
	}

}
