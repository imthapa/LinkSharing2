package linksharing

import com.ttnd.linksharing.util.Seriousness
import grails.transaction.Transactional

@Transactional
class SubscriptionService {

    //subscription done for creator of topic
    def subscribeCreator(Topic topic, Seriousness seriousness) {
        def notSubscribed = Subscription.findByTopicAndUser(topic, topic.createdBy);
        if (notSubscribed == null) {
            Subscription subscription = new Subscription(topic: topic, user: topic.createdBy, seriousness: seriousness)

//            topic.addToSubscriptions(subscription)
            subscription.save()
//            throw new IOException()
            if (subscription.hasErrors()) {
                log.info("${subscription.errors.allErrors}")
                return "${subscription.errors.allErrors}"
            } else {
                log.info("new subscription is made for $topic.createdBy in topic ${topic.name}")
                return "new subscription is made for $topic.createdBy in topic ${topic.name}"
            }
        } else {
            return "subscription already present"
        }
    }

    def save(User user, Integer id) {
        Topic topic = Topic.get(id)
        Subscription subscription = new Subscription(topic: topic, user: user, seriousness: Seriousness.SERIOUS)
        subscription.save()
    }

 /*   def getAllSubscription(User user) {
        log.info("i am inside getAllSubscription")
        List<Subscription> subscriptionList = Subscription.findAllByUser(user)
//        println( subscriptionList)
        subscriptionList

        //  render view:'dashboard', model: [subscriptionList: subscriptionList]
    }

    def getThreeSubscription(User user) {
        log.info("i am inside getAllSubscription")
        List<Subscription> subscriptionList = Subscription.findAllByUser(user, [max: 3])
//        println( subscriptionList)
        subscriptionList

        //  render view:'dashboard', model: [subscriptionList: subscriptionList]
    }*/
}
