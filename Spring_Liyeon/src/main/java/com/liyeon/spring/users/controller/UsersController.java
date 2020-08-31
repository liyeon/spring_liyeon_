package com.liyeon.spring.users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.liyeon.spring.users.service.UsersService;

@Controller
public class UsersController {
	@Autowired
	private UsersService service;
}