package com.ttnd.linksharing.co

import com.ttnd.linksharing.util.Visibility
import grails.validation.Validateable

/**
 * Created by ishwar on 7/5/17.
 */

//todo GORM2 Q1b) Create ResourceSearchCo which extend searchCO and add topicId long field into it to get resource specific to topic
//todo GORM2 Q2a) Update ResourceSearchCO and add visibility field in it
class ResourceSearchCO  extends SearchCO implements Validateable {

    long topicId
    Visibility visibility

    static constraints = {
        topic_id(nullable:true)
        visibility(validator: { val, obj ->
            Visibility.toVisibility(val) == Visibility.PUBLIC
        })
    }
}
