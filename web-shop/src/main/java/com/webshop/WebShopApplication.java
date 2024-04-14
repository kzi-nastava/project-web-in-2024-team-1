package com.webshop;

import com.webshop.model.Account;
import com.webshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

/*
   @SpringBootApplication anotacija nastala je od @EnableAutoConfiguration anotacije koja
   upravlja konfiguracijom aplikacije.
 */
@SpringBootApplication
public class WebShopApplication implements CommandLineRunner {
	/* Da bismo testirali repozitorijum, direktno smo pozvali u glavnoj klasi metode,
	   inace bi pozivi bili u npr. nekom od servisa.
	 */
	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) {

		// kreiramo novi objekat klase Employee
		Account newUser = new Account();
		newUser.setName("hello");

		// čuvamo objekat u bazi
		userRepository.save(newUser);

		List<Account> users = this.userRepository.findAll();
//		List<Employee> employees = this.employeeRepository.findAllByPositionOrderByFirstName("radnik");
//		List<Employee> employees = this.employeeRepository.findByFirstNameOrLastName("Aleksandar", "Milić");
//		List<Employee> employees = this.employeeRepository.findByFirstNameIgnoreCase("jovanka");
//		List<Employee> employees = this.employeeRepository.findByDepartmentName("Menadžment");

		for (Account user : users){
			System.out.println(users);
		}
	}


	public static void main(String[] args) {
		SpringApplication.run(WebShopApplication.class, args);
	}

}
