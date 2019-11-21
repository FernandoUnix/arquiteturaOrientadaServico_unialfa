package com.hotel.hotelapi;

import java.math.BigDecimal;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.hotel.hotelapi.model.Hotel;
import com.hotel.hotelapi.service.HotelService;

@SpringBootApplication
public class HotelApiApplication {

	@Autowired
	private HotelService hotelService;

	public static void main(String[] args) {
		SpringApplication.run(HotelApiApplication.class, args);
	}

	@Bean
	InitializingBean sendDatabase() {
		return () -> {

			if (hotelService.getListHotel().size() == 0) {

				Hotel hotel1 = new Hotel();
				hotel1.setNome("hotel bom sonhos");
				hotel1.setCidade("goiânia");
				hotel1.setPreco(BigDecimal.valueOf(50.0d));

				Hotel hotel2 = new Hotel();
				hotel2.setNome("hotel da maria");
				hotel2.setCidade("brasilia");
				hotel2.setPreco(BigDecimal.valueOf(70.0d));

				Hotel hotel3 = new Hotel();
				hotel3.setNome("hotel 5 estrelas");
				hotel3.setCidade("ceres");
				hotel3.setPreco(BigDecimal.valueOf(150.0d));

				Hotel hotel4 = new Hotel();
				hotel4.setNome("hotel durma bem");
				hotel4.setCidade("pilar");
				hotel4.setPreco(BigDecimal.valueOf(120.0d));

				hotelService.save(hotel1);
				hotelService.save(hotel2);
				hotelService.save(hotel3);
				hotelService.save(hotel4);
			}
		};
	}
}
