package com.webshop;

import com.webshop.model.*;
import com.webshop.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
		Account newUser = new Account();
		newUser.setName("hello");

		accountRepository.save(newUser);

//		Category newCategory = new Category();
//		newCategory.setCategoryName("Ime kategorije");
//		categoryRepository.save(newCategory);
//
//
//		Offer newOffer = new Offer();
//		newOffer.setPriceOffer(20.0);
//		newOffer.setRole(Role.CUSTOMER);
//		offerRepository.save(newOffer);
//
//		Product newProduct = new Product();
//		newProduct.setName("Naziv proizvoda");
//		newProduct.setDescription("Opis proizvoda");
//		newProduct.setImagePath("slika.jpg");
//		newProduct.setPrice(25.99);
//		newProduct.setSalesType(SalesType.FIXED_PRICE);
//		newProduct.setReleaseDate(LocalDate.of(2024, 4, 15));
//		//newProduct.setCategory(newCategory);
//		newProduct.setOffers(List.of(newOffer));
//		newProduct.setCustomerReview(true);
//		newProduct.setSellerReview(false);
//		newProduct.setSold(false);
//		newProduct.setProductType(ProductType.FOR_SALE);
//
//
//		// čuvamo objekat u bazi
//
//		productRepository.save(newProduct);
//
//
//		List<Account> users = this.userRepository.findAll();
//		List<Product> products = this.productRepository.findAll();
//		List<Category> categories = this.categoryRepository.findAll();
//		List<Offer> offers = this.offerRepository.findAll();
//
////		List<Employee> employees = this.employeeRepository.findAllByPositionOrderByFirstName("radnik");
////		List<Employee> employees = this.employeeRepository.findByFirstNameOrLastName("Aleksandar", "Milić");
////		List<Employee> employees = this.employeeRepository.findByFirstNameIgnoreCase("jovanka");
////		List<Employee> employees = this.employeeRepository.findByDepartmentName("Menadžment");
//
//		for (Account user : accountRepository.findAll()){
//			System.out.println(user.toString());
//		}

		System.out.println("END");
	}


	public static void main(String[] args) {
		SpringApplication.run(WebShopApplication.class, args);
	}

}
