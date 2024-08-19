package ru.kata.spring.boot_security.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.RoleServiceImp;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.service.UserServiceImp;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class SpringBootSecurityDemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringBootSecurityDemoApplication.class, args);
        initApplicationDefault(context);
    }

    public static void initApplicationDefault(ConfigurableApplicationContext context) {
        UserService userService = context.getBean(UserServiceImp.class);
        RoleService roleService = context.getBean(RoleServiceImp.class);
        BCryptPasswordEncoder bCryptPasswordEncoder = context.getBean(BCryptPasswordEncoder.class);

        roleService.saveIfExists("ROLE_ADMIN");
        roleService.saveIfExists("ROLE_USER");

		User user = userService.getByEmail("admin@gmail.com");
		if (user == null) {
			Set<Role> roleList = roleService.findAll();
			User newUser = new User("admin", "admin@gmail.com", "admin");
			newUser.setRoles(roleList);
			userService.save(newUser);
		}

		User userTest1 = userService.getByEmail("user1@gmail.com");
		if (userTest1 == null) {
			Set<Role> roleList = new HashSet<>();
			roleList.add(roleService.findByName("ROLE_USER"));
			User newUser = new User("user1", "user1@gmail.com", "1");
			newUser.setRoles(roleList);
			userService.save(newUser);
		}

		User userTest2 = userService.getByEmail("user2@gmail.com");
		if (userTest2 == null) {
			Set<Role> roleList = new HashSet<>();
			roleList.add(roleService.findByName("ROLE_USER"));
			User newUser = new User("user1", "user2@gmail.com", "1");
			newUser.setRoles(roleList);
			userService.save(newUser);
		}
    }
}
