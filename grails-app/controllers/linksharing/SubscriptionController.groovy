package linksharing

import com.gs.collections.impl.lazy.parallel.set.SelectUnsortedSetBatch
import com.ttnd.linksharing.util.Seriousness

class SubscriptionController {

    def index() {}

    //todo Domain2 Q14) Implement subscription save, update, delete

    //todo Domain2 1Q6) Create save action which takes id as parameter for topic id, user for subscription should be read from the session, if subscription save render success else errors
    def save(Integer id) {
        User user = User.get(session.user.id)
        Topic topic = Topic.get(id)
        log.info("$topic")
        def notSubscribed = Subscription.findByTopicAndUser(topic, user);
        if (notSubscribed == null) {
            Subscription subscription = new Subscription(topic: topic, user: user, seriousness: Seriousness.SERIOUS)
            subscription.save()

            if (subscription.hasErrors()) {
                log.info("${subscription.errors.allErrors}")
                render "${subscription.errors.allErrors}"
            } else {
                log.info("new subscription is made for $user in topic ${topic.name}")
                render "new subscription is made for $user in topic ${topic.name}"
            }
        } else {
            render "subscription already present"
        }
    }
    //todo Domain2 Q15. Create subscription delete action which takes id as parameter, if it exist then delete and send success else render not found
    def delete(Integer id) {
        Subscription subscription = Subscription.get(id)
//        log.info("$subscription")
        if (subscription != null) {
            subscription.delete(flush: true, failOnError: true)
            render "subscription successfully deleted"
        } else {
            render "subscription not found"
        }
    }

    //todo Domain2 Q17) Create update action which takes an id and serious as a parameter if subscription and seriousness found, then save else render not found, if saved then render success else errors
    def update(Integer id, String seriousness) {
        Subscription subscription = Subscription.get(id)
        def seriousness1 = Seriousness.toSeriousness(seriousness)
        log.info("$seriousness1")
        if (subscription != null && seriousness1 instanceof Seriousness) {
            subscription.seriousness = seriousness1
            subscription.save(flush:true,failOnError:true)
            if (subscription.hasErrors()) {
                log.info("${subscription.errors.allErrors}")
                render "${subscription.errors.allErrors}"
            } else {
                log.info("$subscription is updated")
                render "$subscription is updated"
            }
        } else {
            render "subscription can't be updated"
        }
    }
}
