package linksharing

import com.ttnd.linksharing.util.Seriousness

class Subscription {

//    Topic topic;
//    User user;
    //todo Domain2 Q13) Subscription domain should have a default seriousness as Serious.
    Seriousness seriousness = Seriousness.SERIOUS
    Date dateCreated
    Date lastUpdated

    static belongsTo = [topic: Topic, user: User]

    static constraints = {
        user nullable: false, unique: 'topic'
        topic nullable: false, unique: 'user'
        seriousness nullable: false
    }

    static mapping = {
        //this is not working ????
//        seriousness defaultvalue: Seriousness.SERIOUS
        //todo Domain2 Q20. Use eager fetching for topic and user in subscription
        topic lazy: false
        user lazy: false
    }
}
