package com.self.amazon.s3;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TestRest {
	@Autowired
	private AmazonS3Persistence amazonS3Persistence;
	
	@Value("${text.yaml}")
	private String sout;
	
	@GetMapping("teste")
	private void Teste(HttpServletRequest req, HttpServletResponse res) {
		try {
			res.getWriter().write(sout);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
