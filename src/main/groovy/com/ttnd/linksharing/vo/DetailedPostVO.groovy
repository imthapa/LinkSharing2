package com.ttnd.linksharing.vo

/**
 * Created by ishwar on 13/5/17.
 */
class DetailedPostVO {
    String userName
    String fullName
    String topicName
    String description
    int ratings
    Date updated
    long resourceID

    @Override
    public String toString() {
        return "DetailedPostVO{" +
                "userName='" + userName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", topicName='" + topicName + '\'' +
                ", description='" + description + '\'' +
                ", ratings=" + ratings +
                ", updated=" + updated +
                ", resourceID=" + resourceID +
                '}';
    }
}
