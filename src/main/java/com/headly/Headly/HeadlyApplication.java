package com.headly.Headly;

import com.headly.Headly.models.Jobpost;
import com.headly.Headly.models.User;
import com.headly.Headly.services.PostingService;
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
	RegistrationService registrationService;
	public static void main(String[] args) {
		SpringApplication.run(HeadlyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
			if(postingService.findById(1)==null){
				postingService.registerPost(new Jobpost(1,1,"Software Entwickler IoT (m/w/d)","INNEO Solutions GmbH","Wir suchen Sie, wenn Sie eine besondere Leidenschaft für Technik spüren!\n" +
								"\n" +
								"Sie möchten Unternehmen helfen, optimale Lösungen zu fi nden? Der Schlüssel liegt im freien Denken. Wenn Sie Freude an Kreativität und Innovation haben, und Ihnen ein \"das haben wir immer so gemacht\" ein Greuel ist, dann bewerben Sie sich jetzt für einen unserer zehn Standorte in Deutschland als Software Entwickler IoT (m/w/d)","inneo@gmbh.de"));

			}

			User user = User.builder().city("moenchengladbach").companyname("headly").contactperson("Herr Tenelsen").email("tenelsen.boris@web.de").housenumber("40")
							.phonenumber("0151123456").street("Hoemenstrasse").zipcode("41199").password("dennis").build();

			User u = registrationService.findUserById(user.getEmail());
			if(u==null) registrationService.registerNewAccount(user);
	}
}
