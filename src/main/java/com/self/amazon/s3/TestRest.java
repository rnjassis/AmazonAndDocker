package com.self.amazon.s3;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.self.amazon.persistence.mongo.entity.PersonDoc;
import com.self.amazon.persistence.mongo.repository.PersonDocRepository;
import com.self.amazon.persistence.sql.entity.Person;
import com.self.amazon.persistence.sql.repository.PersonRepository;

@RestController
@RequestMapping("/")
public class TestRest {
	
	@Autowired
	private AmazonS3Persistence amazonS3Persistence;
	
	@Autowired
	private PersonRepository personRep;
	
	@Autowired
	private PersonDocRepository pdr;
	
	@GetMapping("teste")
	private void Teste(HttpServletRequest req, HttpServletResponse res) {
		pdr.save(new PersonDoc("person doc one"));
		pdr.save(new PersonDoc("person doc two"));
		pdr.save(new PersonDoc("person doc three"));
		
		for(Person p : personRep.findAll()) {
			System.out.println(p.getName());
		}
	}
	
	
	
}
