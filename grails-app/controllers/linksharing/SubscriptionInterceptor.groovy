package linksharing


class SubscriptionInterceptor {

    SubscriptionInterceptor(){
//        match(action: 'save')
    }

    boolean before() {
        User user = session.user
        Topic topic = Topic.get(params.id)
        log.info("$topic")
        def isSubscribed = Subscription.findByTopicAndUser(topic, user);
        if (isSubscribed) {
            render "subscription already present"
        }else {
            return true
        }
        true }

    boolean after() { true }

    void afterView() {
        // no-op
    }
}
