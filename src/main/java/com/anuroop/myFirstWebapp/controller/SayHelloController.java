package com.anuroop.myFirstWebapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SayHelloController {
	
	@RequestMapping("/say-hello")
	@ResponseBody
	public String say_hello() {
		return "Hey user, What task you are doing today";
	}
	
	@RequestMapping("/say-hello-html")
	@ResponseBody
	public String say_hello_html() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<title> My First HTML Page - Changed</title>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("My first html page with body - Changed");
		sb.append("</body>");
		sb.append("</html>");
		
		return sb.toString();
	}
	
	@RequestMapping("/say-hello-jsp")
	public String say_hello_jsp() {
		return "sayHello";
	}
}
