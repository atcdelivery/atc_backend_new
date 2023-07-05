package com.atcdilivery.spring.jwt.mongodb.controllers;

import java.util.*;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.atcdilivery.spring.jwt.mongodb.payload.directResponse.trackorder.TrackOrderResponse;
import com.atcdilivery.spring.jwt.mongodb.payload.request.LoginEmailRequest;
import com.atcdilivery.spring.jwt.mongodb.payload.response.Response;
import com.atcdilivery.spring.jwt.mongodb.repository.RoleRepository;
import com.atcdilivery.spring.jwt.mongodb.repository.UserRepository;
import com.atcdilivery.spring.jwt.mongodb.service.ApiService;
import com.atcdilivery.spring.jwt.mongodb.utilities.SequenceGenrator;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.atcdilivery.spring.jwt.mongodb.models.ERole;
import com.atcdilivery.spring.jwt.mongodb.models.Role;
import com.atcdilivery.spring.jwt.mongodb.models.User;
import com.atcdilivery.spring.jwt.mongodb.payload.request.LoginRequest;
import com.atcdilivery.spring.jwt.mongodb.payload.request.SignupRequest;
import com.atcdilivery.spring.jwt.mongodb.payload.response.JwtResponse;
import com.atcdilivery.spring.jwt.mongodb.payload.response.MessageResponse;
import com.atcdilivery.spring.jwt.mongodb.security.jwt.JwtUtils;
import com.atcdilivery.spring.jwt.mongodb.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
    UserRepository userRepository;

	@Autowired
    RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	SequenceGenrator sequenceGenrator;

	@Autowired
	ApiService apiService;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(),
												 userDetails.getUsername(),
												 userDetails.getEmail(),
												 userDetails.getFirstName(),
												 userDetails.getLastName(),
												 roles,userDetails.getUserId()));
	}

	@PostMapping("/signin/email")
	public ResponseEntity<?> authenticateUserEmail(@Valid @RequestBody LoginEmailRequest loginRequest) {

//		Authentication authentication = authenticationManager.authenticate(
//				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		Optional<User> user = userRepository.findByEmailAndPassword(loginRequest.getEmail(),loginRequest.getPassword());

		String jwt = jwtUtils.genrateJWTToken(user.get());

//		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = new ArrayList<>();

		user.get().getRoles().forEach(x ->{
			roles.add(x.getName().toString());
		});

		return ResponseEntity.ok(new JwtResponse(user.get().getFirstName(),user.get().getLastName(), jwt,user.get().getId(), user.get().getUsername(),user.get().getEmail(),roles,user.get().getUserId()));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(),signUpRequest.getEmail(),
							 signUpRequest.getPassword(),
				 			 new Date(),new Date(),false,false,signUpRequest.getFirstName(),signUpRequest.getLastName());

		Set<String> strRoles = signUpRequest.getRoles();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "mod":
					Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		user.setUserId("USR00"+sequenceGenrator.generateUserSequence(User.SEQUENCE_NAME));
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

/*	@GetMapping("/track-my-order")
	public ResponseEntity<?> trackMyShipments(@RequestParam String waybillNo) throws JsonProcessingException {
		return apiService.trackMyOrder(waybillNo);
	}*/

	@GetMapping("/get-track-order")
	public ResponseEntity<?>  trackOrder(@RequestParam String waybillNo){
		TrackOrderResponse resp =apiService.trackOrderResponse(waybillNo);
		if(resp == null) {
			return new ResponseEntity<>("wayBillNo not found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}


	@PostMapping("/signup_user")
	public ResponseEntity<?> registerOnlyUser(@Valid @RequestBody SignupRequest signUpRequest) {
		Set<String> set = new HashSet<String> ();
		set.add("user");
		signUpRequest.setRoles(set);
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(),signUpRequest.getEmail(),
				signUpRequest.getPassword(),
				new Date(),new Date(),false,false,signUpRequest.getFirstName(),signUpRequest.getLastName());

		Set<String> strRoles = signUpRequest.getRoles();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
					case "admin":
						Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(adminRole);

						break;
					case "mod":
						Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(modRole);

						break;
					default:
						Role userRole = roleRepository.findByName(ERole.ROLE_USER)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		user.setUserId("USR00"+sequenceGenrator.generateUserSequence(User.SEQUENCE_NAME));
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}
