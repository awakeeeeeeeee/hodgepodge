package com.awakeee.hodgepodge.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	@RequestMapping(value="/test",method=RequestMethod.GET)
	@ResponseBody
	public String test(){
		return "hello world!!!";
	}
}
