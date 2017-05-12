package com.ttnd.linksharing.vo

import com.ttnd.linksharing.util.Seriousness
import com.ttnd.linksharing.util.Visibility
import linksharing.User

/**
 * Created by ishwar on 12/5/17.
 */
class SubscriptionVO {
    String topicName;
    User createdBy
    Integer subsCount;
    Integer resCount;
    Visibility visibility
    Seriousness seriousness


    @Override
    public String toString() {
        return "SubscriptionVO{" +
                "topicName='" + topicName + '\'' +
                ", CreatedBy=" + createdBy +
                ", subsCount=" + subsCount +
                ", resCount=" + resCount +
                ", visibility=" + visibility +
                ", seriousness=" + seriousness +
                '}';
    }
}
