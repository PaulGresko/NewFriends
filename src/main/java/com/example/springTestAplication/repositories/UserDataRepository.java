package com.example.springTestAplication.repositories;


import com.example.springTestAplication.entity.UserData;
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


    List<UserData> findByDescriptionLikeAndSexAndZodiacSignAndCity(String description, String sex, String zodiacSign,String city);


    @Query(value = "select * from user_data where TIMESTAMPDIFF(YEAR, birthday, curdate()) between :age1 and :age2", nativeQuery = true)
    List<UserData> findByAge(Integer age1,Integer age2);
}
