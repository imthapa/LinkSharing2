package com.ttnd.linksharing.co

import linksharing.Topic
import linksharing.User

/**
 * Created by ishwar on 12/5/17.
 */
class LinkCO {
    String url;
    String description;
    long id
//    Topic topic
    User createdBy


    @Override
    public String toString() {
        return "LinkCO{" +
                "url='" + url + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", createdBy=" + createdBy +
                '}';
    }
}
