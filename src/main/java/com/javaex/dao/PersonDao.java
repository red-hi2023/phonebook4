package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PersonVo;

@Repository
public class PersonDao {
	
	//필드
	@Autowired
	private SqlSession sqlSession;
	
	//생성자
	//메소드 gs
	//메소드 일반
	
	//전체 리스트 가져오기
	public List<PersonVo> personSelect(){
		System.out.println("PersonDao.personSelect()");
		
		//db에서 리스트 가져온다
		List<PersonVo> personList = sqlSession.selectList("phonebook.select");
		//System.out.println(personList);
		
		return personList;
	}
	
	//1명 삭제하기
	public int personDelete(int personId) {
		System.out.println("PersonDao.personDelete()");
		
		int count = sqlSession.delete("phonebook.delete", personId);
		return count;
	}
	
	//사람 저장하기
	public int personInsert(PersonVo personVo) {
		System.out.println("PersonDao.personInsert()");
		
		int count = sqlSession.insert("phonebook.insert", personVo);
		return 0;
	}
	
	
	//수정폼-->1명데이터 가져오기
	public PersonVo personSelectOne(int personId) {
		System.out.println("PersonDao.personSelectOne()");
		
		PersonVo personVo = sqlSession.selectOne("phonebook.selectByNo", personId);
		
		return personVo;
	}
	
	//수정
	public int personUpdate(PersonVo personVo) {
		System.out.println("PersonDao.personUpdate()");
		
		int count = sqlSession.update("phonebook.update", personVo);
		
		return count;
	}
	
	
	
	
	

}
