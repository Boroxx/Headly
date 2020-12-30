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

import java.util.ArrayList;
import java.util.List;
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
		/*

			User applicant = User.builder().role("BEWERBER").city("moenchengladbach").firstname("Boris").lastname("Tenelsen").email("tenelsen.boris@web.de").housenumber("40")
							.phonenumber("0151123456").street("Hoemenstrasse").zipcode("41199").password("dennis").build();
			User company = User.builder().role("UNTERNEHMEN").city("moenchengladbach").companyname("headly").contactperson("Herr Tenelsen").email("test@test.de").housenumber("40")
				.phonenumber("0151123456").street("Hoemenstrasse").zipcode("41199").password("dennis").build();

			User uappclicant = registrationService.findUserById(applicant.getEmail());
			User ucompany = registrationService.findUserById(company.getEmail());
			if(uappclicant==null && ucompany==null){
				registrationService.registerNewAccount(applicant);
				registrationService.enableUser(applicant);
				registrationService.registerNewAccount(company);
				registrationService.enableUser(company);

			}

/*

		Profession profession1 = new Profession(1,"Bauwesen");
		Profession profession2 = new Profession(2,"Dienstleistung");
		Profession profession3 = new Profession(3,"Elektro");
		Profession profession4 = new Profession(4,"IT");
		Profession profession5 = new Profession(5,"Kunst, Kultur und Gestaltung");
		Profession profession6 = new Profession(6,"Landwirtschaft, Natur und Umwelt");
		Profession profession7 = new Profession(7,"Metall, Maschinenbau");
		Profession profession8 = new Profession(8,"Naturwissenschaften");
		Profession profession9 = new Profession(9,"Produktion, Fertigung");
		Profession profession10 = new Profession(10,"Soziales");
		Profession profession11 = new Profession(11,"Technik und Technologie");
		Profession profession12 = new Profession(12,"Verkehr und Logistik");
		Profession profession13 = new Profession(13,"Wirtschaft und Verwaltung");



		professionService.registerProfession(profession1);
		professionService.registerProfession(profession2);
		professionService.registerProfession(profession3);
		professionService.registerProfession(profession4);
		professionService.registerProfession(profession5);
		professionService.registerProfession(profession6);
		professionService.registerProfession(profession7);
		professionService.registerProfession(profession8);
		professionService.registerProfession(profession9);
		professionService.registerProfession(profession10);
		professionService.registerProfession(profession11);
		professionService.registerProfession(profession12);
		professionService.registerProfession(profession13);


		/*

		List<Jobpost> jobposts = new ArrayList<>();
		for(int i= 1; i< 5; i++){
			jobposts.add(new Jobpost(i,
					1,
					null,
					"Elektriker Baumeister(m/w)" + i,
					"Bauwesen",
					"45000 - 5000 Euro",
					"Ort" + i,
					"Vollzeit",
					"Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet."+ i));

		}

		for (Jobpost jobpost : jobposts) {
			postingService.registerPost(jobpost);
		}
	*/




	}
}
