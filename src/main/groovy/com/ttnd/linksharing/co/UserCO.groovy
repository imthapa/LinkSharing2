package com.ttnd.linksharing.co

import grails.validation.Validateable

/**
 * Created by ishwar on 5/5/17.
 */
class UserCO implements Validateable{
    String firstName;
    String lastName;
    String email;
    String userName
    String password;
    String confirmPassword;
    byte[] photo;

    static constraints = {

        email(unique: true, blank: false, nullable: false, email: true)
        userName(unique: true)
        password(blank: false, nullable: false, size: 5..15)
        firstName(blank: false, nullable: false)
        lastName(blank: false, nullable: false)
        photo(nullable: true)
    }


    @Override
    public String toString() {
        return "UserCO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }
}
