package com.codesimple.bookstore.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codesimple.bookstore.common.APIResponse;
import com.codesimple.bookstore.dto.LoginRequestDTO;
import com.codesimple.bookstore.dto.SignUpRequestDTO;
import com.codesimple.bookstore.entity.User;
import com.codesimple.bookstore.repo.UserRepo;
import com.codesimple.bookstore.util.JwtUtils;

@Service
public class LoginService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private JwtUtils jwtUtils;

	public APIResponse signUp(SignUpRequestDTO signUpRequestDTO) {

		APIResponse apiResponse = new APIResponse();

		// convert DTO to entity

		User user = new User();
		user.setName(signUpRequestDTO.getName());
		user.setGender(signUpRequestDTO.getGender());
		user.setEmailId(signUpRequestDTO.getEmailId());
		user.setIsActive(Boolean.TRUE);
		user.setPhoneNumber(signUpRequestDTO.getPhoneNumber());
		user.setPassword(signUpRequestDTO.getPassword());

		// store entity
		user = userRepo.save(user);

		
		// generate jwt

		String token = jwtUtils.generateJwt(user);

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("accessToken", token);

		// return
		apiResponse.setData(data);
		
		return apiResponse;
	}

	public APIResponse login(LoginRequestDTO loginRequestDTO) {

		APIResponse apiResponse = new APIResponse();

		// validation

		// verify user exist with given email and password

		User user = userRepo.findOneByEmailIdIgnoreCaseAndPassword(loginRequestDTO.getEmailId(),
				loginRequestDTO.getPassword());

		// response
		if (user == null) {
			apiResponse.setData("User Login Failed..");
			return apiResponse;
		}

		// generate jwt

		String token = jwtUtils.generateJwt(user);

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("accessToken", token);

		apiResponse.setData(data);

		return apiResponse;
	}

}
