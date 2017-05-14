package com.ttnd.linksharing.co

import linksharing.User
import org.springframework.web.multipart.MultipartFile

/**
 * Created by ishwar on 14/5/17.
 */
class FileCO {
    String description;
    long topicId
    User createdBy
    MultipartFile myFile


    @Override
    public String toString() {
        return "FileCO{" +
                "description='" + description + '\'' +
                ", topicId=" + topicId +
                ", createdBy=" + createdBy +
                '}';
    }
}
