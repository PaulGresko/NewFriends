package com.example.NewFriends.repositories;



import com.example.NewFriends.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDataRepository extends JpaRepository<UserData, String> {

    @Query(value = "select * from user_data where login = :username",
            nativeQuery = true)
    Optional<UserData> findMyData(String username);


    @Query(value = "select u.* from user_data u left join user u2 on u.login = u2.login\n" +
            "           where u2.status = 'ROLE_USER'\n" +
            "            and u2.login <> :username\n" +
            "            and u.login not in(\n" +
            "                select login from (\n" +
            "                    select friend2 as login from friends where friend1 = :username\n" +
            "                ) a union (\n" +
            "                    select friend1 as login from friends where friend2 = :username\n" +
            "                )\n" +
            "            ) and u.login not in (\n" +
            "                select last_found_user from last_find\n" +
            "                        where last_find.login = :username\n" +
            "            ) ORDER BY RAND() LIMIT 1;"
            , nativeQuery = true)
    UserData findDefaultUser(String username);

    @Query(value = "select user_data.login, name, description, sex, image, birthday, city, zodiac_sign from user_data LEFT JOIN user on user_data.login = user.login where status ='ROLE_WAITING'", nativeQuery = true)
    List<UserData> findUnverifiedUsers();


    @Query(value = "select * from user_data where description like %:description% and sex = :sex and zodiac_sign = :zodiacSign " +
            "and city = :city and TIMESTAMPDIFF(YEAR, birthday, curdate()) between :age1 and :age2", nativeQuery = true)
    List<UserData> findByCategory(String description, String sex, String zodiacSign, String city, int age1, int age2);

    @Query(value = "select * from user_data where TIMESTAMPDIFF(YEAR, birthday, curdate()) between :age1 and :age2", nativeQuery = true)
    List<UserData> findByAge(Integer age1, Integer age2);


    @Query(value = "select * from user_data where login in(\n" +
            "                   select friend2 from friends where friend1 = :user and status = 'friends'\n" +
            "                    ) or login in (\n" +
            "                   select friend1 from friends where friend2 = :user and status = 'friends'\n" +
            "                    );",
            nativeQuery = true)
    List<UserData> findAllFriends(String user);


    @Query(value = "select * from user_data where login in (\n" +
            "    select friend1 from friends where friend2 = :user\n" +
            "                                  and status = 'waiting'\n" +
            "    );",
            nativeQuery = true)
    List<UserData> findAllInvites(String user);

    @Query(value = "select * from user_data where login in (\n" +
            "    select friend2 from friends where friend1 = :user\n" +
            "                                  and status = 'waiting'\n" +
            "    );",
    nativeQuery = true)
    List<UserData> findAllRequests(String user);

}
