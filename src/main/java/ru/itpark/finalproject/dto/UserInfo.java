package ru.itpark.finalproject.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    private String firstName;
    private String secondName;
    private String country;
    private String city;
    private String address;
    private String phoneNumber;
    private String secondPhoneNumber;
}
