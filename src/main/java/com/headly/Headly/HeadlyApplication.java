package com.headly.Headly;

import com.headly.Headly.models.Jobpost;
import com.headly.Headly.services.PostingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HeadlyApplication implements CommandLineRunner {

	@Autowired
	PostingService postingService;
	public static void main(String[] args) {
		SpringApplication.run(HeadlyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
			postingService.registerPost(new Jobpost(1,"Software Entwickler IoT (m/w/d)","INNEO Solutions GmbH","Wir suchen Sie, wenn Sie eine besondere Leidenschaft für Technik spüren!\n" +
							"\n" +
							"Sie möchten Unternehmen helfen, optimale Lösungen zu fi nden? Der Schlüssel liegt im freien Denken. Wenn Sie Freude an Kreativität und Innovation haben, und Ihnen ein \"das haben wir immer so gemacht\" ein Greuel ist, dann bewerben Sie sich jetzt für einen unserer zehn Standorte in Deutschland als Software Entwickler IoT (m/w/d)","inneo@gmbh.de"));
	}
}
