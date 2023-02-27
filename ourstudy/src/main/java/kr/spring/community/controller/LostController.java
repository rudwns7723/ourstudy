package kr.spring.community.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.community.service.LostService;
import kr.spring.community.vo.LostVO;
import kr.spring.info.controller.InformationController;
import kr.spring.util.PagingUtil;

@Controller
public class LostController {
	private static final Logger logger =
			LoggerFactory.getLogger(InformationController.class);
	
	@Autowired
	private LostService lostService;
	
	//자바빈(VO) 초기화
		@ModelAttribute
		public LostVO initCommand() {
			return new LostVO();
		}
	
	//==분실물 게시판 글목록
		@RequestMapping("/community/lostList.do")
		public ModelAndView process(
				 @RequestParam(value="pageNum",defaultValue="1") 
				    int currentPage,String keyfield,String keyword) {
					
				Map<String,Object> map = 
							new HashMap<String,Object>();
				map.put("keyfield", keyfield);
				map.put("keyword", keyword);
				
				//글의 총개수 또는 검색된 글의 개수
				int count = lostService.selectRowCount(map);
				logger.debug("<<count>> : " + count);
				
				//페이지 처리
				PagingUtil page = 
						new PagingUtil(keyfield,keyword,
						 currentPage,count,20,10,"lostList.do");
				List<LostVO> list = null;
				if(count > 0) {
					map.put("start", page.getStartRow());
					map.put("end", page.getEndRow());
					
					list = lostService.selectList(map);
				}
				ModelAndView mav = new ModelAndView();
				mav.setViewName("lostList");
				mav.addObject("count",count);
				mav.addObject("lostList",list);
				mav.addObject("page",page.getPage());
				
				return mav;
		}
		
//분실물
	//==분실물 글쓰기 
	//등록 폼 호출
	@GetMapping("/community/lostWrite.do")
	public String form() {
		return "lostWrite";
	}
	
	//등록 폼에서 전송된 데이터 처리
	@PostMapping("/community/lostWrite.do")
		public String submit(@Valid LostVO lostVO, BindingResult result, Model model) {
		logger.debug("<<분실물 글쓰기>> : " + lostVO);
		//유효성 체크 결과 오류가 있으면 폼을 호출
				if(result.hasErrors()) {
					return form();
				}
		//글쓰기	
		lostService.insertLost(lostVO);
		
		return "redirect:/info/lostList.do";
	}
	
	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * */
		
		
		
//습득물
	//==습득물 글쓰기
		
		
		
		
}