package com.ttnd.linksharing.vo

/**
 * Created by ishwar on 10/5/17.
 */
class PostsVO {

    String topicName;
    long topicId
    String resourceDescription
    long resourceID


    @Override
    public String toString() {
        return "PostsVO{" +
                "topicName='" + topicName + '\'' +
                ", topicId=" + topicId +
                ", resourceDescription='" + resourceDescription + '\'' +
                ", resourceID=" + resourceID +
                '}';
    }
}
