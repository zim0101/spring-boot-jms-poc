package com.app.jmspoc;

import com.app.jmspoc.model.Account;
import com.app.jmspoc.model.enums.Role;
import com.app.jmspoc.repository.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootApplication
public class JmsPOCApplication implements CommandLineRunner{

	private final AccountRepository accountRepository;
	private final PasswordEncoder passwordEncoder;

    public JmsPOCApplication(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public static void main(String[] args) {
		SpringApplication.run(JmsPOCApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Create Admin User for the demo
		Account account = new Account();
		account.setFirstName("Mr.");
		account.setLastName("Admin");
		account.setUsername("admin1");
		account.setEmail("admin1@gmail.com");
		account.setPassword(passwordEncoder.encode("admin1"));
		account.setRoles(Set.of(Role.ADMIN));
		accountRepository.save(account);
	}
}
