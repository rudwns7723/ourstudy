package kr.spring.ticket.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

import kr.spring.ticket.service.TicketService;
import kr.spring.ticket.vo.TicketVO;
import kr.spring.util.PagingUtil;

@Controller
public class TicketAdminController {
	private static final Logger logger = 
			LoggerFactory.getLogger(TicketAdminController.class);

	@Autowired
	private TicketService ticketService;

	//자바빈 초기화
	@ModelAttribute
	public TicketVO initCommand() {
		return new TicketVO();
	}

	//이용권 목록
	@RequestMapping("/ticket/admin_ticketList.do")
	public ModelAndView process(
					@RequestParam(value="pageNum",defaultValue="1")
					int currentPage,
					String keyfield,
					String keyword) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		
		//상품의 총 개수 또는 검색된 상품의 개수
		int count = ticketService.selectTicketCount(map);
		
		//페이지 처리
				PagingUtil page = new PagingUtil(keyfield, keyword,
							currentPage, count, 20, 10, "list.do");
		
		List<TicketVO> list = null;
		if(count > 0) {
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());
			
			list = ticketService.selectTicketList(map);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("adminTicketList");
		mav.addObject("count",count);
		mav.addObject("list",list);
		mav.addObject("page",page.getPage());


		return mav;
	}

	//======= 이용권 등록 =======//
	//등록 폼 호출
	@GetMapping("/ticket/admin_write.do")
	public String form() {
		return "ticketAdminWrite"; //타일스 설정값
	}

	//등록 폼에서 전송된 데이터 처리
	@PostMapping("/ticket/admin_write.do")
	public String submit(@Valid TicketVO ticketVO,
			BindingResult result,
			Model model,
			HttpServletRequest request,
			HttpSession session) {

		if(result.hasErrors()) {
			return form();
		}

		//이용권 등록하기
		ticketService.insertTicket(ticketVO);


		//View에 표시할 메시지
		model.addAttribute("message", "이용권 등록이 완료되었습니다.");
		model.addAttribute("url",
				request.getContextPath()+"/ticket/admin_ticketList.do");

		return "common/resultView";
	}
}


