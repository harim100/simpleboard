package com.board.frm.util;

import java.text.MessageFormat;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.board.dao.MemberDao;

@Component
public class IdDuplicationValidator implements ConstraintValidator<IdDuplicationConstraint, String> {
	
	@Autowired
	private MemberDao memberDao;
	
	@Override
	public boolean isValid(String id, ConstraintValidatorContext context) {
		int isSameId = memberDao.checkId(id);
		if(isSameId > 0)
		{
			return false;
		} 
		else 
		{
			return true;
		}
	}

}
