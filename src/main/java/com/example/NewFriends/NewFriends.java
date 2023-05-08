package com.example.NewFriends;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NewFriends /*implements ApplicationRunner*/ {

		public static void main(String[] args) {
		SpringApplication.run(NewFriends.class, args);
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
