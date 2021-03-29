package com.board.biz;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.board.dao.MemberDao;
import com.board.dto.MemberDto;
import com.board.frm.util.AES256Util;

@Service
public class MemberBiz { 
	private static final Logger logger = LoggerFactory.getLogger(MemberBiz.class);
	
	@Autowired
	private MemberDao memDao;
	
	@Autowired
	BCryptPasswordEncoder passEncoder;
	
	@Autowired
	AES256Util aes;

	public MemberDto login(MemberDto mDto) {
		return memDao.login(mDto);
	}
	
	public int checkId(String requestedId) {
		return memDao.checkId(requestedId);
	}
	
	public Map<String, Object> passwordMatching(MemberDto mDto) throws NoSuchAlgorithmException, UnsupportedEncodingException, GeneralSecurityException {
		MemberDto loginDto = this.login(mDto);
		Map<String, Object> loginMap = new HashMap<>();
		loginMap.put("isMatch", passEncoder.matches(mDto.getPw(), loginDto.getPw()));
		loginMap.put("customer_nm", aes.decrypt(loginDto.getCustomer_nm()));
		loginMap.put("loginDto", loginDto);
		
		return loginMap;
	}

	public int insertMember(MemberDto mDto) throws NoSuchAlgorithmException, UnsupportedEncodingException, GeneralSecurityException {
		//패스워드 암호화
		String password = mDto.getPw();
		mDto.setPw(passEncoder.encode(password));
		
		//이름 암호화
		String name = mDto.getCustomer_nm();
		mDto.setCustomer_nm(aes.encrypt(name));
		
		//전화번호 암호화
		String cellNum = mDto.getCell_no();
		mDto.setCell_no(aes.encrypt(cellNum));
		
		
		return memDao.insertMember(mDto);
	}
}
