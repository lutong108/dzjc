package com.chinacreator.dzjc_print;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
@Controller
public class ComplainPrintController {
	@RequestMapping("/complainPrint")
	public ModelAndView ComplainPrint(String message ) {
		ModelAndView modelAndView= new ModelAndView();
		Map map=JSON.parseObject(message);
		modelAndView.addObject("message", map);
		modelAndView.setViewName("jsp/printCharge");
		return modelAndView;
	}	
}
