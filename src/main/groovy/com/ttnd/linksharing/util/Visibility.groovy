package com.ttnd.linksharing.util

/**
 * Created by ishwar on 25/4/17.
 */
enum Visibility {
    PRIVATE,PUBLIC

    //todo Domain2 Q7) Create a method in visibility enum to convert string into enum and write test case for the same
    static def toVisibility(String str){
        // Coersion by type.
        if(str in ['PRIVATE','PUBLIC','private','public']){
            Visibility vEnum = str.toUpperCase()
        }
        else
            "sorry can be coverted to visibility enum"
    }
}