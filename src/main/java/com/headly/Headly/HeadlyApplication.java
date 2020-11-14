package com.headly.Headly;

import com.headly.Headly.models.Jobpost;
import com.headly.Headly.models.Profession;
import com.headly.Headly.models.User;
import com.headly.Headly.services.PostingService;
import com.headly.Headly.services.ProfessionService;
import com.headly.Headly.services.RegistrationService;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Locale;

@SpringBootApplication
public class HeadlyApplication implements CommandLineRunner {

	@Autowired
	PostingService postingService;

	@Autowired
	ProfessionService professionService;

	@Autowired
	RegistrationService registrationService;
	public static void main(String[] args) {
		SpringApplication.run(HeadlyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


			User user = User.builder().role("UNTERNEHMEN").city("moenchengladbach").companyname("headly").contactperson("Herr Tenelsen").email("tenelsen.boris@web.de").housenumber("40")
							.phonenumber("0151123456").street("Hoemenstrasse").zipcode("41199").password("dennis").build();

			User u = registrationService.findUserById(user.getEmail());
			if(u==null) registrationService.registerNewAccount(user);

		Profession profession1 = new Profession(1,"Bauwesen");
		Profession profession2 = new Profession(2,"Dienstleistung");
		Profession profession3 = new Profession(3,"Elektro");
		Profession profession4 = new Profession(4,"Gesundheit");

		professionService.registerProfession(profession1);
		professionService.registerProfession(profession2);
		professionService.registerProfession(profession3);
		professionService.registerProfession(profession4);

	}
}
