package linksharing

import com.ttnd.linksharing.co.TopicCO
import com.ttnd.linksharing.util.Seriousness
import com.ttnd.linksharing.util.Visibility
import grails.transaction.Transactional

@Transactional
class TopicService {

    def subscriptionService

    def delete(Integer id) {
        Topic topic = Topic.load(id)
        topic.delete(failOnError:true,flush:true)
        if(topic.hasErrors())
            return "something went wrong"
        else
            return "successfully deleted"
    }

    def createTopic(TopicCO topicCO, User user) {
        Topic topic = new Topic(name: topicCO.name,visibility: Visibility.toVisibility(topicCO.visibility),createdBy: user)
        topic.save()
       // throw new IOException()
        subscriptionService.subscribeCreator(topic,Seriousness.SERIOUS)
    }

    def showTrending(){
        List trendingTopics = Topic.getTrendingTopics()
        log.info("$trendingTopics")
        return  trendingTopics

    }
}
