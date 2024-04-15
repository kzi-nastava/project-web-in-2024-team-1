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

		Category newCategory = new Category();
		newCategory.setCategoryName("Ime kategorije");
		categoryRepository.save(newCategory);

		Offer newOffer = new Offer();
		newOffer.setPriceOffer(20.0);
		newOffer.setRole(Role.CUSTOMER);
		offerRepository.save(newOffer);
		Product newProduct = new Product();
		newProduct.setName("Naziv proizvoda");
		newProduct.setDescription("Opis proizvoda");
		newProduct.setImagePath("slika.jpg");
		newProduct.setPrice(25.99);
		newProduct.setSalesType(SalesType.FIXED_PRICE);
		newProduct.setReleaseDate(LocalDate.of(2024, 4, 15));
		productRepository.save(newProduct);

		newProduct.setCategory(newCategory); // Postavljanje kategorije na proizvod
		newProduct.setOffers(List.of(newOffer));
		newProduct.setCustomerReview(true);
		newProduct.setSellerReview(false);
		newProduct.setSold(false);
		newProduct.setProductType(ProductType.FOR_SALE);
		productRepository.save(newProduct);

		Review newReview = new Review();
		newReview.setRating(5);
		newReview.setComment("Komentar");
		Date reviewDate = new Date(2024, 3, 15);
		newReview.setReviewDate(reviewDate);


		Account newUser = new Account();
		newUser.setName("Ime");
		newUser.setLastName("Last name");
		newUser.setUsername("username");
		newUser.setPhoneNumber("Phone number");
		newUser.setPassword("Password");
		newUser.setDateOfBirth(LocalDate.of(2001,5,20));
		newUser.setImagePath("slika.jpg");
		newUser.setDescription("bio");
		newUser.setUserRole(Role.CUSTOMER);
		accountRepository.save(newUser);
		newUser.setProductList(List.of(newProduct));
		newUser.setReviewList(List.of(newReview));
		newUser.setBlocked(false);



		newReview.setReviewer(newUser);
		reviewRepository.save(newReview);
		accountRepository.save(newUser);


		ReportUser newReportUser = new ReportUser();
		Date reportDate = new Date(2024, 3, 15);
		newReportUser.setReportDate(reportDate);
		reportUserRepository.save(newReportUser);
		newReportUser.setReportingUser(newUser);

		newReportUser.setReportedUser(newUser);
		newReportUser.setStatus(ReportStatus.SUBMITTED);
		reportUserRepository.save(newReportUser);


		System.out.println("END");

		for (Account account : accountRepository.findAll()) {

			System.out.println(account.toString());
		}

		for (Offer offer : offerRepository.findAll()) {
			System.out.println(offer.toString());
		}

		for (Category category : categoryRepository.findAll()) {

			System.out.println(category.toString());
		}

		for (Product product : productRepository.findAll()) {

			System.out.println(product.toString());
		}

		for (Review review : reviewRepository.findAll()) {

			System.out.println(review.toString());
		}

		for (ReportUser reportUser: reportUserRepository.findAll()) {

			System.out.println(reportUser.toString());

		}
	}


	public static void main(String[] args)
	{
		SpringApplication.run(WebShopApplication.class, args);
	}

}
