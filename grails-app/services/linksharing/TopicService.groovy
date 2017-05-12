package linksharing

import com.ttnd.linksharing.co.TopicCO
import com.ttnd.linksharing.util.Visibility
import grails.transaction.Transactional

@Transactional
class TopicService {

    def subscriptionService

    def delete(Integer id) {
        //for exception handling
        // throw new Exception()
        Topic topic = Topic.load(id)
//        Topic topic1 = topic
//        log.info"$topic is a loaded topic."
        topic.delete(failOnError:true,flush:true)
        if(topic.hasErrors())
            return "something went wrong"
        else
            return "successfully deleted"
    }

    def createTopic(TopicCO topicCO, User user,String seriousness) {
        Topic topic = new Topic(name: topicCO.name,visibility: Visibility.toVisibility(topicCO.visibility),createdBy: user)
        topic.save()
       // throw new IOException()
        subscriptionService.subscribeCreator(topic,seriousness)
    }

    def showTrending(){
        List trendingTopics = Topic.getTrendingTopics()
        log.info("$trendingTopics")
        return  trendingTopics

    }
}
