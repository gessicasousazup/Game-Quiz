package br.com.game.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.game.models.User;
import br.com.game.service.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/quiz/users")
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping
	public ResponseEntity<?> showUsers() {
		try {
			return ResponseEntity.ok().body(userService.showAllUser());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@PostMapping
	public ResponseEntity<?> saveUser(HttpSession session, @RequestBody User user) {
		try {
			userService.saveUser(user);
			session.setAttribute("user", user);
			return ResponseEntity.status(HttpStatus.CREATED).body(user);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@GetMapping("/{idGoogle}")
	public ResponseEntity<?> showUsersById(@PathVariable String idGoogle, HttpSession session) {
		try {
			User user = userService.showUserById(idGoogle);
			session.setAttribute("user", user);
			return ResponseEntity.ok(user);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		}
	}
	
}