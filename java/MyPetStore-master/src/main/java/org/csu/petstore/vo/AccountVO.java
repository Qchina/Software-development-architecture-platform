package org.csu.petstore.vo;

import lombok.Data;


@Data
public class AccountVO {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String status;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String phone;
    private String langPref;
    private String favoriteGory;
    private Integer myListOpt;
    private Integer bannerOpt;
}