package com.ttnd.linksharing.co

import grails.validation.Validateable
import linksharing.User

/**
 * Created by ishwar on 14/5/17.
 */

class UpdatePasswordCO{
    String oldPassword
    String password
    String confirmPassword
    long id

    static constraints = {
        password(blank: false, nullable: false, size: 5..15)
        confirmPassword(nullable: true, blank: true)
        confirmPassword(blank: true, nullable: true, validator: { val, obj ->
            if (!val.equals(obj.password)) {
                return 'com.ttn.linksharing.User.confirmPassword.validator'
            }
        })
    }


//    String toString() {
//        "${oldPassword}:${password}:${id}"
//    }
    @Override
    public String toString() {
        return "UpdatePasswordCO{" +
                "oldPassword='" + oldPassword + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", id=" + id +
                '}';
    }
}
