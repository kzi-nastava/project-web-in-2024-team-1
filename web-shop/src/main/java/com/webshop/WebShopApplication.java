package com.webshop;

import com.webshop.model.*;
import com.webshop.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

import java.util.Date;

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

	private AccountRepository accountRepository;
	@Autowired
	private ReportUserRepository reportUserRepository;
	@Autowired
	private ReviewRepository reviewRepository;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private OfferRepository offerRepository;

	@Override
	public void run(String... args) {

		// kreiramo novi objekat klase Employee

		System.out.println("END");



		for (Offer offer : offerRepository.findAll()) {
			System.out.println(offer.toString());
		}

		for (Category category : categoryRepository.findAll()) {

			System.out.println(category.toString());
		}

		for (Product product : productRepository.findAll()) {

			System.out.println(product.toString());
		}
	}


	public static void main(String[] args)
	{
		SpringApplication.run(WebShopApplication.class, args);
	}

}
