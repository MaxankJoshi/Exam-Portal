package com.exam;

import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ExamportalApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(ExamportalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		try {
//			User user = new User();
//
//			user.setUserFirstName("Mayank");
//			user.setUserLastName("Joshi");
//			user.setUsername("Maxank");
//			user.setUserEmail("mayankjoshi233@gmail.com");
//			user.setUserPassword(this.passwordEncoder.encode("hathaway"));
//			user.setUserPhone("9528910558");
//			user.setProfile("default.png");
//			user.setUserAbout("I am a Backend Developer");
//
//			Role role = new Role();
//			role.setRoleId(1);
//			role.setRoleName("ADMIN");
//
//			UserRole userRole = new UserRole();
//			userRole.setUser(user);
//			userRole.setRole(role);
//
//			Set<UserRole> userRoles = new HashSet<>();
//			userRoles.add(userRole);
//
//			User user1 = this.userService.createUser(user, userRoles);
//		}
//
//		catch (Exception e) {
//			e.printStackTrace();
//			throw new UserFoundException();
//		}
	}
}
