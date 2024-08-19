package ru.kata.spring.boot_security.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kata.spring.boot_security.demo.model.User;


public interface UserRepository extends JpaRepository<User, Integer> {
    /*@Query("Select u from User u join fetch u.roles where u.name=:name")*/
    User findByName(String name);
    User findByEmail(String email);
}
