package com.hotel.hotelapi.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HotelResource {

	@GetMapping("/helloworld")
	public String helloWorld() {
		return "hello world";
	}
}
