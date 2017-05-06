package com.ttnd.linksharing.util

/**
 * Created by ishwar on 25/4/17.
 */
enum Seriousness {
    SERIOUS, VERY_SERIOUS, CASUAL

    //todo Domain2 Q18) Create static method in seriuosness which take string as parameter and returns seriousness, it should be case insensitive
    static def toSeriousness(String str){

        if(str in ['SERIOUS','VERY_SERIOUS','CASUAL','serious','very_serious','casual']){
            Seriousness sEnum = str.toUpperCase()
        }
        else
            "sorry can't be coverted to Seriousness enum"
    }
}