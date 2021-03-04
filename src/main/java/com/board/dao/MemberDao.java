package com.board.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.LogManager;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.board.logic.MemberLogic;

public class MemberDao {
	private static final Logger logger = LoggerFactory.getLogger(MemberDao.class);
	@Autowired(required=false)
	private SqlSessionTemplate sqlSessionTemplate;
	int result = 0;
	List<Map<String,Object>> memList= null;
//////////////////////////////[[회원정보 시작]]//////////////////////////////
	public List<Map<String,Object>> member_login(Map<String, Object> pMap) {
		logger.info("member_login 호출성공");
		memList = new ArrayList<>();
		Map<String,Object> rLoginMap = new HashMap<>();
			sqlSessionTemplate.selectOne("proc_Login",pMap);
			logger.info("로그인 성공? : "+pMap.get("msg"));
			logger.info(pMap.toString());
			rLoginMap.put("mem_name",pMap.get("msg"));
			rLoginMap.put("mem_email",pMap.get("r_memEmail"));
			rLoginMap.put("mem_no",pMap.get("r_memNum"));
			rLoginMap.put("mem_uid",pMap.get("r_memUid"));
			memList.add(rLoginMap);
		return memList;
	}
		
	public List<Map<String, Object>> member_info(Map<String, Object> pMap) {
		logger.info("member_info 호출성공");
		return sqlSessionTemplate.selectList("member_info",pMap);
	}
	
	public List<Map<String, Object>> member_id_check(Map<String, Object> pMap) {
		logger.info("member_id_check 호출성공");
		return sqlSessionTemplate.selectList(" ",pMap);
	}
	
	public int member_join(Map<String, Object> pMap) {
		logger.info("member_join 호출성공");
		return sqlSessionTemplate.insert("member_join",pMap);
	}
	
	public int member_modi(Map<String, Object> pMap) {
		logger.info("member_modi 호출성공");
		return sqlSessionTemplate.update(" ",pMap);
	}
	 
	public int member_del(Map<String, Object> pMap) {
		logger.info("member_del 호출성공");
		return sqlSessionTemplate.delete(" ",pMap);
	}
}
