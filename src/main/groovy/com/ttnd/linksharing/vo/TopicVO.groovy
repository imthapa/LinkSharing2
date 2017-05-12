package com.ttnd.linksharing.vo

import com.ttnd.linksharing.util.Visibility
import linksharing.User

/**
 * Created by ishwar on 7/5/17.
 */
//todo GORM2 Q4b) TopicVO will have id,name,visibility,count,createdBy fields
class TopicVO {
    long id
    String name
    String visibility
    Integer count
    User createdBy
    Integer subsCount


    @Override
    public String toString() {
        return "TopicVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", visibility=" + visibility +
                ", count=" + count +
                ", createdBy=" + createdBy +
                '}';
    }
}
