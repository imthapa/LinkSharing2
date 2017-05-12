package com.ttnd.linksharing.vo

/**
 * Created by ishwar on 11/5/17.
 */
class ProfilePageVO {
    String loggedInUsername
    String loggedInUsersFullName
    int subscriptionCount
    int topicCount
    byte[] photo
    long loggedInUserId


    @Override
    public String toString() {
        return "ProfilePageVO{" +
                "loggedInUsername='" + loggedInUsername + '\'' +
                ", loggedInUsersFullName='" + loggedInUsersFullName + '\'' +
                ", subscriptionCount=" + subscriptionCount +
                ", topicCount=" + topicCount +
                '}';
    }
}