package com.example.springTestAplication;

import com.example.springTestAplication.entity.UserData;
import com.example.springTestAplication.repositories.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SpringTestAplicationApplication /*implements ApplicationRunner*/ {

		public static void main(String[] args) {
		SpringApplication.run(SpringTestAplicationApplication.class, args);
	}
//
//	@Autowired
//	UserDataRepository userDataRepository;
//
//	public static void main(String[] args) {
//		SpringApplication.run(SpringTestAplicationApplication.class, args);
//	}
//
//	@Override
//	public void run(ApplicationArguments args) throws Exception {
//		List<UserData> userDataList = userDataRepository.findByAge(32,32);
//		System.out.println(userDataList);
//		System.out.println(userDataRepository.findByDescriptionLikeAndSexAndZodiacSignAndCity(
//				"%Petr%","М", "Телец","Москва"));
//
//	}
}
