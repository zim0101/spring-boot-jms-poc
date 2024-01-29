package com.app.jmspoc.repository;

import com.app.jmspoc.model.Account;
import com.app.jmspoc.model.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByUsername(String username);

    Account findByEmail(String email);

    List<Account> findByRolesContains(Role role);
}