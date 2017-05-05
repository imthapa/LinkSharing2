package com.ttnd.linksharing.util

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
    boolean active;
    byte[] photo;
    boolean admin
    Date dateCreated
    Date lastUpdated

    static constraints = {

        email(unique: true, blank: false, nullable: false, email: true)
        userName(unique: true)
        password(blank: false, nullable: false, size: 5..15)
        firstName(blank: false, nullable: false)
        lastName(blank: false, nullable: false)
        photo(nullable: true)
        admin(nullable: true)
        active(nullable: true)
    }

    @Override
    String toString() {
        return "$userName"
    }
}
