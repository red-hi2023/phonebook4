package com.javaex.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PersonDao;
import com.javaex.vo.PersonVo;

@Controller
public class PhoneController {
	
	@Autowired
	private PersonDao personDao;
	
	//리스트
	@RequestMapping(value="/list", method = {RequestMethod.GET, RequestMethod.POST })
	public String list(Model model) {
		System.out.println("PhoneController.list()");
		
		//db에서 리스트를 가져온다
		List<PersonVo> personList = personDao.personSelect();
		
		//ds에게 데이터보낸다  포워드 request.Attribute 영역에 넣어라
		model.addAttribute("personList", personList);
		
		return "list";
	}

	//삭제
	@RequestMapping(value="/delete", method= {RequestMethod.GET,RequestMethod.POST})
	public String delete(@RequestParam(value="no") int personId) {
		System.out.println("PhoneController.delete()");
		
		//dao를 통해서 삭제한다
		int count = personDao.personDelete(personId);
		
		//리스트로 리다이렉트
		return "redirect:/list";
	}
	
	//등록폼
	@RequestMapping(value="/writeForm", method= {RequestMethod.GET,RequestMethod.POST})
	public String writeForm() {
		System.out.println("PhoneController.writeForm()");
		
		return "writeForm";
	}
	
	//등록
	@RequestMapping(value="/write", method= {RequestMethod.GET,RequestMethod.POST})
	public String write(@ModelAttribute PersonVo personVo) {
		System.out.println("PhoneController.write()");
		
		int count = personDao.personInsert(personVo);
		
		return "redirect:/list";
	}
	
	//수정폼
	@RequestMapping(value="/updateForm", method= {RequestMethod.GET,RequestMethod.POST})
	public String updateForm(@RequestParam(value="no") int personId, Model model) {
		System.out.println("PhoneController.updateForm()");
		
		//1명 데이터 가져오기
		PersonVo personVo = personDao.personSelectOne(personId);
		
		//ds 포워드 시킨다
		model.addAttribute("personVo", personVo);
		
		return "updateForm";
	}
	
	
	//수정
	@RequestMapping(value="/update", method= {RequestMethod.GET,RequestMethod.POST})
	public String update(@ModelAttribute PersonVo personVo) {
		System.out.println("PhoneController.update()");
		
		//데이터 수정
		int count = personDao.personUpdate(personVo);
		System.out.println(count);
		
		return "redirect:/list";
	}
	
	/*Map사용 예제*/
	@RequestMapping(value="/insert2", method= {RequestMethod.GET,RequestMethod.POST})
	public String insert2(@RequestParam(value="name") String name) {
		System.out.println("PhoneController.insert2()");
		
		//name 파라미터
		
		//hp, company insert2()내에서 계산된 값
		String hp="010-0000-0000";
		String company="02-0000-0000";
		
		//1) vo로 묶는다
		/*
		PersonVo personVo = new PersonVo(); //PersonVo가 없으면 만든다
		personVo.setPerson_id(personId);
		personVo.setHp(hp);
		personVo.setCompany(company);
		*/
		
		//2) vo를 안만든다  ?==> 이번 딱 1번만 사용 ==> Map을 사용한다
		Map<String, String> personMap = new HashMap<String, String>();
		personMap.put("name", name);
		personMap.put("hp", hp);
		personMap.put("company", company);
	
		int count = personDao.personInsert2(personMap);
		
		return "redirect:/list";
	}
	
	/*Map사용 예제*/
	@RequestMapping(value="/updateForm2", method= {RequestMethod.GET,RequestMethod.POST})
	public String updateForm2(@RequestParam(value="no") int no, Model model) {
		System.out.println("PhoneController.updateForm2()");
		
		Map<String, Object> personMap =personDao.peronSelectOne2(no);
		
		model.addAttribute("personMap", personMap);
		
		return "/updateForm2";
	}
	
	
	/* resultType 예제 */
	@RequestMapping(value="/list2", method = {RequestMethod.GET, RequestMethod.POST })
	public String list2() {
		System.out.println("PhoneController.list2()");
		
		List<PersonVo> personList = personDao.personSelect2();
		
		return "";
	}
	
	
}
