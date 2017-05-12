package linksharing

import com.ttnd.linksharing.co.SearchCO
import com.ttnd.linksharing.vo.InboxVO
import com.ttnd.linksharing.vo.ProfilePageVO
import com.ttnd.linksharing.vo.SubscriptionVO
import com.ttnd.linksharing.vo.TopicVO

//todo Q3. Add User controller with index action that will render text 'user dahsboard'
class UserController {

    def subscriptionService

    def index() {
        User user = session.user
        ProfilePageVO profilePageVO = new ProfilePageVO()
        profilePageVO.loggedInUsersFullName = user.getFullName()
        profilePageVO.loggedInUsername = user.userName
        profilePageVO.subscriptionCount = Subscription.countByUser(user)
        profilePageVO.topicCount = Topic.countByCreatedBy(user)
//        profilePageVO.photo = session.user.photo
        profilePageVO.loggedInUserId = user.id

        List<SubscriptionVO> subscriptionList = User.getSubscribedTopic(user)
        log.info("${subscriptionList}")
        List<InboxVO> messages = user.getUnReadResources()
        render view: 'dashboard', model: [user: profilePageVO, subscriptionList: subscriptionList,messages:messages]
    }
/*
    def index(SearchCO searchCO){
//        render "${session.user}"
//        render(view:"dashboard");
//        log.info("${searchCO.q}")
//        List list = User.getUnReadResources(searchCO)
//        println(list)
//        render(list)
    }*/
/*

    def index(SearchCO searchCO) {
        log.info("wkwkwwkjwkjwk $searchCO.q")
        List list = User.getUnReadResources(searchCO)
        render "$list"
    }

*/

    def image(Long userId) {
        User user = User.load(userId)
        byte[] photo
        if (!user?.photo) {
            photo = assetResourceLocator.findAssetForURI('user.png').byteArray
        } else {
            photo = user.photo
        }
        OutputStream out = response.getOutputStream()
        out.write(photo)
        out.flush()
        out.close()
    }

    def unread(SearchCO searchCO) {
        log.info("${searchCO.q}")
        List list = session.user.getUnReadResources()
        println(list)
        render(list)
    }

}
