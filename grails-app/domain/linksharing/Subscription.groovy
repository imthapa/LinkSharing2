package linksharing

import com.ttnd.linksharing.util.Seriousness

class Subscription {

    Topic topic;
    User user;
    Seriousness seriousness
    Date dateCreated
    Date lastUpdated

    static constraints = {
        user nullable: false, unique: 'topic'
        topic nullable: false, unique: 'user'
        seriousness nullable: false
    }
}
