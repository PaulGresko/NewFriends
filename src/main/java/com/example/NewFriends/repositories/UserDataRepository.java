package com.example.NewFriends.repositories;


import com.example.NewFriends.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDataRepository extends JpaRepository<UserData, String> {
    List<UserData> findByDescriptionLike(String description);
    List<UserData> findByDescriptionNotLike(String description);
    List<UserData> findByZodiacSign(String zodiacSign);
    List<UserData> findBySex(String sex);
    List<UserData> findByCity(String city);

    @Query(value = "select user_data.login, name, description, sex, image, birthday, city, zodiac_sign from user_data LEFT JOIN user on user_data.login = user.login where status ='ROLE_NEW'",nativeQuery = true)
    List<UserData> findUnverifiedUsers();


    @Query(value = "select * from user_data where description like %:description% and sex = :sex and zodiac_sign = :zodiacSign " +
            "and city = :city and TIMESTAMPDIFF(YEAR, birthday, curdate()) between :age1 and :age2", nativeQuery = true)
    List<UserData> findByCategory(String description, String sex, String zodiacSign,String city, int age1, int age2);

    @Query(value = "select * from user_data where TIMESTAMPDIFF(YEAR, birthday, curdate()) between :age1 and :age2", nativeQuery = true)
    List<UserData> findByAge(Integer age1,Integer age2);
}
