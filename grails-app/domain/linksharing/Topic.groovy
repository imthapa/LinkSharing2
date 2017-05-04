package linksharing

import com.ttnd.linksharing.util.Seriousness
import com.ttnd.linksharing.util.Visibility


class Topic {

    String name
    Date lastUpdated
    Visibility visibility
    Date dateCreated

    static hasMany = [subscription: Subscription, resource: Resource]
    static belongsTo = [createdBy: User]

//    static mapping = {
//        //id composite: ['createdBy','name']
//        createdBy column: createdBy
//    }

    static constraints = {
        name(unique: 'createdBy', nullable: false, blank: false)
        createdBy(nullable: false)
        visibility(nullable: false, validator: {
            val ->
                val instanceof Visibility
        })
    }
    //todo Q8. Creator of topic should automatically be subscribed to topic (Use after insert event of topic)
    def afterInsert() {
        //todo Q10. WithNewSession in after insert because it will not work without it
        //org.springframework.dao.InvalidDataAccessApiUsageException:
        Topic.withNewSession {
            //todo Q11. Seriousness should be very serious for auto subscribed topic in after insert
            Subscription subscription = new Subscription(user: createdBy, topic: this, seriousness: Seriousness.VERY_SERIOUS)
            // topic.addToSubscriptions(subscription)
            this.addToSubscription(subscription)
           // subscription.save()
            //todo Q9. Errors should be logged if topic or subscriptions is not saved
            if (subscription.hasErrors()) {
                log.error("subscription failed for $subscription.errors.allErrors")
            } else {
                log.info("$createdBy has subscribed $this")
            }
        }
    }

    // todo Q21) toString should be implemented for Topic with topic name and for User with username
    @Override
    public String toString() {
        return "Topic{" +
                "createdBy=" + createdBy.userName +
                ", name='" + name + '\'' +
                '}';
    }
}
