package com.board.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.domain.UserVO;
import com.board.service.UserService;

@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	private final UserService userService;
	
	@Inject
	public MemberController(UserService userService) {
		this.userService = userService;
	}
	
	// 회원가입 페이지
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerGET() throws Exception {
		logger.info("get register");
		return "/member/register";
	}
	
	// 회원가입 처리
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPOST(UserVO userVO, RedirectAttributes redirectAttributes) throws Exception {
		logger.info("post register");
		
		//비밀번호 암호화 처리 나중에 적용
		//String hashedPW = bcrypt.hashpw(userVO.getPw(), BCrypt.gensalt());
		//userVO.setPw(hashedPW);
		userService.register(userVO);
		redirectAttributes.addFlashAttribute("msg", "REGISTERED");
		
		return "redirect:/member/login";
	}
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request, Model model)  {
		System.out.println("login()");
		//model.addAttribute("request", request);

		return "/member/login";
	}
}