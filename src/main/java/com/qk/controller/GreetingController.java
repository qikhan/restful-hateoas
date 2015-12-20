package com.qk.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qk.entity.Greeting;

@Controller
public class GreetingController {

	private static final String TEMPLATE_GREETING = "Hello, %s!";
	private static final String TEMPLATE_MESSAGE = "A message will be sent to '%s'.";

	@RequestMapping("/greeting")
	@ResponseBody
	public HttpEntity<Greeting> greeting(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name) {

		Greeting greeting = new Greeting(String.format(TEMPLATE_GREETING, name));

		greeting.add(linkTo(methodOn(GreetingController.class).greeting(name))
				.withSelfRel());

		greeting.add(linkTo(methodOn(GreetingController.class).message(name))
				.withRel("message"));

		return new ResponseEntity<Greeting>(greeting, HttpStatus.OK);
	}

	@RequestMapping("/message")
	@ResponseBody
	public HttpEntity<Greeting> message(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name) {

		Greeting greeting = new Greeting(String.format(TEMPLATE_MESSAGE, name));

		greeting.add(linkTo(methodOn(GreetingController.class).message(name))
				.withSelfRel());

		return new ResponseEntity<Greeting>(greeting, HttpStatus.OK);
	}
}