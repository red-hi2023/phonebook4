package com.javaex.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PersonDao;
import com.javaex.vo.PersonVo;

@Controller
public class PhoneController {
	//필드//생성자//메소드gs
	
	//메소드일반
	
	//주소록 리스트
	@RequestMapping(value="/list", method={RequestMethod.GET,RequestMethod.POST})
	public String list(Model model) {
		System.out.println("PhoneController.list()");
		
		//personDao.personSelect() 리스트가져온다
		PersonDao personDao = new PersonDao();
		List<PersonVo> personList = personDao.personSelect("");
		
		//model 주소를 받고 메소드를 이용해서 담는다  
		//--> DS request.setAttribute("personList", personList)
		model.addAttribute("personList", personList);
		
		//ds에게 포워딩을 위한 jsp정보 전달
		return "/WEB-INF/list.jsp";
	}
	
	//주소록 등록폼
	@RequestMapping(value="/writeForm", method= {RequestMethod.GET,RequestMethod.POST})
	public String writeForm() {
		System.out.println("PhoneController.writeForm()");
		
		//ds에게 포워딩을 위한 jsp정보 전달
		return "/WEB-INF/writeForm.jsp";
	}
	
	
	//주소록 등록
	@RequestMapping(value="write", method= {RequestMethod.GET, RequestMethod.POST})
	public String write(@ModelAttribute PersonVo personVo ) {
		System.out.println("PhoneController.write()");
		
		//vo로 묶는다  ds
		/*
		PersonVo personVo = new PersonVo();
		personVo.setName(name);
		personVo.setHp(hp);
		personVo.setCompany(company);
		*/
		
		//dao를 통해서 저장한다
		PersonDao personDao = new PersonDao();
		int count = personDao.personInsert(personVo);
		System.out.println(count);
		
		//ds에게 리다이렉트을 위한 url정보 전달
		return "redirect:/list";
	}
	
	/*
	@RequestMapping(value="write", method= {RequestMethod.GET, RequestMethod.POST})
	public String write(@RequestParam(value="name") String name, 
						@RequestParam(value="hp") String hp, 
						@RequestParam(value="company") String company ) {
		System.out.println("PhoneController.write()");
		
		//vo로 묶는다
		PersonVo personVo = new PersonVo();
		personVo.setName(name);
		personVo.setHp(hp);
		personVo.setCompany(company);
		System.out.println(personVo);
		
		//dao를 통해서 저장한다
		PersonDao personDao = new PersonDao();
		int count = personDao.personInsert(personVo);
		System.out.println(count);
				
		//ds에게 리다이렉트을 위한 url정보 전달
		return "redirect:/list";
	}
	*/
	
	//person삭제(@PathVariable 사용)
	@RequestMapping(value="/delete/{no}", method= {RequestMethod.GET, RequestMethod.POST})
	public String delete(@PathVariable(value="no") int personId) {
		System.out.println("PhoneController.delete()");
		
		System.out.println(personId);
		PersonDao personDao = new PersonDao(); 
		int count = personDao.personDelete(personId);
		
		//ds에게 리다이렉트을 위한 url정보 전달
		return "redirect:/list";
	}
	
	
	
	/*
	//파라미터 사용
	@RequestMapping(value="/delete", method= {RequestMethod.GET, RequestMethod.POST})
	public String delete(@RequestParam(value="no") int personId) {
		System.out.println("PhoneController.delete()");
		
		PersonDao personDao = new PersonDao(); 
		int count = personDao.personDelete(personId);
		//System.out.println(count);
		
		return "redirect:/list";
	}
	*/
	
	
	//수정폼
	@RequestMapping(value="/updateForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String updateForm(@RequestParam(value="no") int personId, Model model) {
		System.out.println("PhoneController.updateForm()");
		
		//Dao를 통해 데이터를 가져온다
		PersonDao personDao = new PersonDao(); 
		PersonVo personVo = personDao.personSelectOne(personId);
		
		//model 주소를 받고 메소드를 이용해서 담는다  
		model.addAttribute("personVo", personVo);
		
		//ds에게 포워딩을 위한 jsp정보 전달
		return "/WEB-INF/updateForm.jsp";
	}
	
	
	//수정
	@RequestMapping(value="/update", method= {RequestMethod.GET, RequestMethod.POST})
	public String update(@ModelAttribute PersonVo personVo) {
		System.out.println("PhoneController.update()");
		
		//Dao를 통해 데이터를 가져온다
		PersonDao personDao = new PersonDao(); 
		int count = personDao.personUpdate(personVo);
		
		//ds에게 리다이렉트을 위한 url정보 전달
		return "redirect:/list";
	}
	
}
