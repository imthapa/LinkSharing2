package com.ttnd.linksharing.vo

import linksharing.User

/**
 * Created by ishwar on 12/5/17.
 */
class InboxVO {

    long resourceID
    String topicName
    String description
    User createdBy
//    String fullName
//    String userName


    @Override
    public String toString() {
        return "InboxVO{" +
                "resourceID=" + resourceID +
                ", topicName='" + topicName + '\'' +
                ", description='" + description + '\'' +
                ", createdBy=" + createdBy +
                '}';
    }
}
