package linksharing

import com.ttnd.linksharing.util.Seriousness
import com.ttnd.linksharing.util.Visibility
import grails.util.Holders

class BootStrap {

    def init = { servletContext ->
        // insert()
        createUsers()
        createTopics()
        //createResources()
        checkResourceCount()
        subscribeTopics(User.get(1))
    }
    def destroy = {
    }

    //todo Q1. Add createUsers method to Bootstrap to create 1 admin and 1 normal user.
    def createUsers() {
        def grailsApplication = Holders.grailsApplication

        log.info "============admin creating========"
        //todo Q4. Users will be created only when there are no records in user table
        if (User.count == 0) {
            User admin = new User(firstName: "ishwar", lastName: "mani",
                    email: "ishwar@ttn.com", userName: "ishwar", password: "122334",//grailsApplication.config.defaultPassword,
                    active: true, photo: [], admin: true,
                    dateCreated: new Date(), lastUpdated: new Date())
            //todo Q2. Use failOnError and flush true for persisting users
            admin.save(flush: true, failOnError: true)

            //todo Q3. Use log info statements for data creation and log error if there are any errors
            log.info "============admin created========"
            if (admin.hasErrors())
                log.error(admin.errors.allErrors)
            //  if (User.count() == 0) {
            log.info "============normal user creating========"
            User normalUser = new User(firstName: "uday", lastName: "pratap",
                    email: "uday.pratap@tothenew.com", userName: "uday", password: "123456",
                    active: true, photo: [], admin: false,
                    dateCreated: new Date(), lastUpdated: new Date())
            normalUser.save(flush: true, failOnError: true)
            log.info "============normal user creating========"
            if (normalUser.hasErrors())
                log.error(normalUser.errors.allErrors)
        } else {
            log.info("user table already contains a user,thus new user can't be inserted...")
        }

    }
    //todo Q6)  Add createTopics method in bootstrap
    def createTopics() {
        (1..(User.count())).each {
            User user = User.get(it);
            if (Topic.countByCreatedBy(user) == 0) {
                //todo Q.7) 5 topics per user needs to be created if Topic count is 0
                (1..5).each({
                    Topic topic = new Topic(name: "java${it}", createdBy: user, visibility: Visibility.PRIVATE,).save()
                    //  doSubscription(user, topic)
                })
                log.info("5 topics created for $user")
            }
        }
    }
    //todo Q12. Add createResources method which create 2 link resource and 2 document resource in each topic
    def createResources() {
        def counter = 0
        (1..User.count).each {
            User user = User.get(it)
            //   List topicName = ['spring','hibernate','grails','gorm']
            List links = ['http://www.spring.io', 'http://www.hibernate.com', 'http://www.grailsdocs.com', 'http://www.groovyORM.com'];
            List document = ['www.spring.io/spring.pdf', 'www.hibernate.io/hibernate.pdf', 'www.grailsdocs.io/grailsdocs.pdf', 'www.groovyORM.io/groovyORM.pdf']
            List<Topic> topics = Topic.findAllByCreatedBy(user);
            log.info("topics fetched for $user are ${topics*.name}")

            String description = "default resource"
//            if (counter > links.size() - 1)
//                counter = 0
            log.info(links[counter])
            topics.each {
                if (counter > links.size() - 1)
                    counter = 0
                Topic topicName = it;
                2.times {
                    // todo Q14) Description of the resource should include topic name
                    LinkResource linkResource = new LinkResource(description: "${topicName.name} : $description", url: links[counter],
                            createdBy: user, topic: topicName)

                    // todo GORM-1    16) Creator of the resource should be same as creator of the topic
                    //user is the one who has created the topic.
                    DocumentResource documentResource = new DocumentResource(description: "${topicName.name} : $description",
                            filePath: document[counter], createdBy: user, topic: topicName)
                    linkResource.save()
                    documentResource.save()
                    counter++
                    // todo 15) Error should be logged
                    if (linkResource.hasErrors())
                        log.error linkResource.errors.allErrors
                    else if (documentResource.hasErrors())
                        log.error documentResource.errors.allErrors
                    else log.info "Resource created by ${user.userName} for topic ${topicName.name} "
                }
            }
            //log.info("link and docs created successfully...")


        }
    }

    def checkResourceCount() {
        // todo Q13) It should create resource only if there is not data in resource table
        if (Resource.count() == 0) {
            createResources()
        } else {
            log.info("resource table is not vacant...")
        }
    }

    //todo Q17) Add subscribeTopics for user to subscribe all the topics which are not created by user
    def subscribeTopics(User user){
        List<Topic> topicsNotSubscribed = Topic.findAllByCreatedByNotEqual(user)
       // List<Topic> topicsNotSubscribed = Topic.findAllByCreatedByNotInList([user])  //not working why ???
        List check = Topic.findAllByIdNotEqual(1)
        log.info("++++++++++++++++the checked ids are $check ++++++++++++++++++++++")
        log.info("$user has not subscribed to ${topicsNotSubscribed*.id}")
        topicsNotSubscribed.each {

            Subscription subscription = new Subscription(topic: it,user: user,seriousness: Seriousness.CASUAL)
            subscription.save()
            if(subscription.hasErrors())
                log.info(subscription.errors.allErrors)
            else
                log.info("new subscription is made for $user in topic ${it.name}")
        }
    }

    //todo Q22) Add createReadingItems in bootstrap to create dummy reading items
    def createReadingItems(){

    }

//    def doSubscription = {
//        user, topic ->
//            Subscription subscription = new Subscription(user: user,topic: topic,seriousness: Seriousness.VERY_SERIOUS)
//           // topic.addToSubscriptions(subscription)
//            subscription.save()
//
//            if(subscription.hasErrors()){
//                log.error("subscription failed for $subscription.errors.allErrors")
//            }
//            else {
//                log.info("$user has subscribed $topic")
//            }
//
//    }

    void insert() {
        def grailsApplication
        User user = new User(firstName: "ishwar", lastName: "mani",
                email: "ishwar@ttn.com", userName: "ishwar", password: "876876",
                active: true, photo: [], admin: false,
                dateCreated: new Date(), lastUpdated: new Date())
        user.save()
        Topic topic = new Topic(name: "java", createdBy: User.get(1),
                dateCreated: new Date(), lastUpdated: new Date(),
                visibility: Visibility.PRIVATE)
        topic.save()
    }


}
