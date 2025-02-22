package it.aulab.progetto_finale_gianmarco_nacci.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.aulab.progetto_finale_gianmarco_nacci.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

}
