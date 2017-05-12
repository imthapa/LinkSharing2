package linksharing

import com.ttnd.linksharing.co.ResourceSearchCO
import com.ttnd.linksharing.co.TopicCO
import com.ttnd.linksharing.util.Visibility
import com.ttnd.linksharing.vo.TopicVO
import org.hibernate.ObjectNotFoundException
import org.hibernate.resource.transaction.backend.jta.internal.synchronization.ExceptionMapper

//todo Q16) If topic do not exist in database then user should redirected to login index action and flash error should be set.
class TopicController {

    def topicService
//    TopicService topicService

    def index() {

    }

    //todo Q15) Add show action for topic which will take id as a parameter.
    //todo GORM2 Q1d) Update topic show action which will take ResourceSearchCO as an argument other than long id
    def show(ResourceSearchCO resourceSearchCO,Integer id) {
//        Topic topic = Topic.findById(id)
        //todo Domain2 - Q3. Use read() for /topic/show and load() for /resource/delete & /topic/delete action.
        Topic topic = Topic.read(id)
        log.info("$topic is a topic read")
        if (topic == null) {
            log.info("topic is null.")
            flash.error = "topic doesn't exist."
            redirect(controller: "login", action: "index")
        }
        //todo Q17. If topic found and its a public topic then it should render success.
        else if (topic.visibility == Visibility.PUBLIC) {
            render "success"
        }
        //todo Q18. If topic found is private then check the subscription of logged in user exist for the topic or not.
        else {
            if (topic.visibility == Visibility.PRIVATE) {
                if (Subscription.findByUserAndTopic(session.user, topic)) {
                    //todo Q19. If subscription exist then render success otherwise redirect user to login index and set flash error
                    render "success from private and subscribed."
                } else {
                    //subscription doesn't exist
                    flash.error = "you are not subscribed for this topic."
                    redirect(controller: "login", action: "index")
                }
            }
//                render "success : $topic is private"
        }
    }

    //todo Domain2 - Q3. Use read() for /topic/show and load() for /resource/delete & /topic/delete action.
    def delete(Integer id) {
      String message = topicService.delete(id)
        render "$message"
    }

    //todo Q4) Exception of object not found should be handled in resource delete
 /*   def exceptionHandler(Exception e) {
        *//*
           This method will be called if any unhandled Execption occurs in the code
        *//*
//        ['error':'Something Went Wrong ,Our Tech. Team will Analysize it shortly']
//        render view: 'notFound',  model: [id: params.id, exception: e]
        render "Something Went Wrong"
    }*/

    //todo Domain2 Q5) Add topic save action in TopicController
    //todo Domain2 Q6) Add save action in topic controller, which takes a topic and string seriousness as an argument
    def save(TopicCO topicCO, String seriousness) {
        topicService.createTopic(topicCO,session.user,seriousness)
//    }
//        User user = User.get(it);
        //todo Domain2 Q8) Session user should be createdBy of the topic
//        if (session.user.userName == topic.createdBy.userName) {
         /*   if (Topic.countByCreatedByAndName(topic.createdBy, topic.name) == 0) {
//            topic = new Topic(name: "java${it}", createdBy: user, visibility: Visibility.PRIVATE,)
                topic.save()
//                topicService.createTopic(topic,session.user)
                if (topic.hasErrors()) {
                    //todo Domain2 Q10) If a topic is not saved errors should be logged flash error should be set and error text should be rendered
                    log.info(topic.errors.allErrors)
                    flash.error = topic.errors.allErrors
                    render "${flash.error}"
                } else {
                    //todo Domain2 Q9) If a topic is saved without error flash message should be set and success should be rendered
                    flash.error = "no errors"
                    render "topic successfully saved"
                }*/
//            }
       /* } else {
            flash.error = "you are not allowed to create a topic. please login first"
            redirect(controller: "login", action: "index")
        }*/

    }

    def showTrending(){
        List topicVO = Topic.getTrendingTopics()
        log.info("$topicVO")
        render "$topicVO"
       /* render """
                    topicId : $TopicVO.id
                    topicName : $TopicVO.name
                    visibility ; $TopicVO.visibility
                    count : $TopicVO.count
                    createdBy : $TopicVO.createdBy
                    """
       */
    }
}