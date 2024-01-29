package com.app.jmspoc;

import com.app.jmspoc.model.Account;
import com.app.jmspoc.model.enums.Role;
import com.app.jmspoc.repository.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
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
		List<Account> accounts = new ArrayList<>();

		for (int i = 1; i <= 5; i++) {
			Account account = new Account();
			account.setFirstName("Mr.");
			account.setLastName("Admin"+i);
			account.setUsername("admin"+i);
			account.setEmail("admin"+i+"@gmail.com");
			account.setPassword(passwordEncoder.encode("admin"));
			account.setRoles(Set.of(Role.ADMIN));
			accounts.add(account);
		}
		accountRepository.saveAll(accounts);
	}
}
