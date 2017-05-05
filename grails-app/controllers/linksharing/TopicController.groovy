package linksharing

import com.ttnd.linksharing.util.Visibility

//todo Q16) If topic do not exist in database then user should redirected to login index action and flash error should be set.
class TopicController {

    def index() {

    }

    //todo Q15) Add show action for topic which will take id as a parameter.
    def show(Integer id) {
        Topic topic = Topic.findById(id)
        if (topic == null) {
            log.info("topic is null.")
            flash.error = "topic doesn't exist."
            redirect(controller: "login", action: "index")
        }
        //todo Q17. If topic found and its a public topic then it should render success.
        else if(topic.visibility == Visibility.PUBLIC){
            render "success"
        }
        //todo Q18. If topic found is private then check the subscription of logged in user exist for the topic or not.
        else{
            if(topic.visibility == Visibility.PRIVATE ){
                if(Subscription.findByUserAndTopic(session.user,topic)){
                    //todo Q19. If subscription exist then render success otherwise redirect user to login index and set flash error
                    render "success from private and subscribed."
                }else{
                    //subscription doesn't exist
                    flash.error = "you are not subscribed for this topic."
                    redirect(controller: "login", action: "index")
                }
            }
//                render "success : $topic is private"
        }
    }
}
