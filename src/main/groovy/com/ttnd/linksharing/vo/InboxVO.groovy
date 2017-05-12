package com.ttnd.linksharing.vo

import linksharing.User

/**
 * Created by ishwar on 12/5/17.
 */
class InboxVO {

    String topicName
    String description
    User createdBy
//    String fullName
//    String userName


    @Override
    public String toString() {
        return "InboxVO{" +
                "topicName='" + topicName + '\'' +
                ", description='" + description + '\'' +
                ", createdBY=" + createdBy +
                '}';
    }
}
