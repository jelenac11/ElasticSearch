package com.ftn.udd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ftn.udd.model.User;
import com.ftn.udd.repositories.IUserRepository;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	boolean alreadySetup = false;

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (alreadySetup) {
			return;
		}

		User user1 = new User("hr1@gmail.com", passwordEncoder.encode("sifra123"), "Jelena", "Cupac");
		User user2 = new User("hr2@gmail.com", passwordEncoder.encode("sifra123"), "Jelena2", "Cupac2");

		userRepository.save(user1);
		userRepository.save(user2);

		alreadySetup = true;
	}

}
