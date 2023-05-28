package com.example.NewFriends.util;

import java.util.Date;

public class CalculateAge {

    public static int get(Date birthDate) {
        Date currentDate = new Date();
        long diffInMillis = currentDate.getTime() - birthDate.getTime();
        long ageInMillis = 1000L * 60 * 60 * 24 * 365; // количество миллисекунд в году
        int age = (int) (diffInMillis / ageInMillis);
        return age;
    }
}
