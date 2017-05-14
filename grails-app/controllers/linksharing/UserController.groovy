package linksharing

import com.ttnd.linksharing.co.SearchCO
import com.ttnd.linksharing.co.UpdatePasswordCO
import com.ttnd.linksharing.co.UserCO
import com.ttnd.linksharing.vo.InboxVO
import com.ttnd.linksharing.vo.PostsVO
import com.ttnd.linksharing.vo.UserDetailsVO
import com.ttnd.linksharing.vo.TopicVO

//todo Q3. Add User controller with index action that will render text 'user dahsboard'
class UserController {

    def subscriptionService

    def index() {
        User user = session.user
        UserDetailsVO userDetailsVO = new UserDetailsVO()
        userDetailsVO.userFullName = user.getFullName()
        userDetailsVO.userName = user.userName
        userDetailsVO.subscriptionCount = Subscription.countByUser(user)
        userDetailsVO.topicCount = Topic.countByCreatedBy(user)
//      profilePageVO.photo = session.user.photo
        userDetailsVO.userId = user.id
        List<TopicVO> subscriptionList = User.getSubscribedTopic(user)
        List<PostsVO> messages = user.getUnReadResources()
        log.info("------------------------------------------ $messages")
        render view: 'dashboard', model: [users: userDetailsVO, subscriptionList: subscriptionList,
                                          resourceList: messages,message: params.message]
    }
/*
    def index(SearchCO searchCO){
        render "${session.user}"
        render(view:"dashboard");
        log.info("${searchCO.q}")
        List list = User.getUnReadResources(searchCO)
        println(list)
        render(list)
    }
*/

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

    def profile(long id) {

        User user = User.get(id)

        UserDetailsVO userDetailsVO = new UserDetailsVO()
        userDetailsVO.userFullName = user.getFullName()
        userDetailsVO.userName = user.userName
        userDetailsVO.subscriptionCount = Subscription.countByUser(user)
        userDetailsVO.topicCount = Topic.countByCreatedBy(user)
        userDetailsVO.userId = user.id

        List<TopicVO> subscriptionList = User.getSubscribedTopic(user)
        List<TopicVO> topicsCreated = User.allCreatedTopics(user)
        List<PostsVO> allPosts = User.allCreatedPost(user)
//        users: userDetailsVO,
        render view: "profile", model: ['subscriptionList': subscriptionList,
                                        topicsCreated     : topicsCreated, allPosts: allPosts]
    }

    def edit() {
        List<TopicVO> topicsCreated = User.allCreatedTopics(session.user)
        render view: "edit", model: [topicsCreated: topicsCreated]
    }
/*
    def updatePassword(UpdatePasswordCO co) {
        log.info("$co")
        User user = User.get(session.user.id)
        log.info("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa $user")
//        if (co.password.equals(co.confirmPassword)){
//
//        }

        if (user.password.equals(co.oldPassword) ) {
                user.password = co.password
                user.confirmPassword = co.confirmPassword
                co.validate()
                if(co.hasError){
                    flash.error = "sorry "
                }
                else if (user.save(failOnError:true,flush: true)) {
                    flash.message = "Password changed successfully"
                    session.user = user
                } else {
                    flash.error = "Error in updating password"
                }
//            }

        } else {
            flash.error = "Old Password do not match!"
        }
        redirect(controller: "user", action: "editProfile")
    }*/


    def updatePassword(UpdatePasswordCO co) {
        log.info("$co")
        User user = User.get(session.user.id)
        log.info("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa $user")
        if (co.password.equals(co.confirmPassword)) {
            if (user.password.equals(co.oldPassword)) {
                user.password = co.password
                user.confirmPassword = co.confirmPassword
                if (user.save(failOnError: true, flush: true)) {
                    flash.message = "Password changed successfully"
                    session.user = user
                } else {
                    flash.error = "Error in updating password"
                }
//            }

            } else {
                flash.error = "Old Password do not match!"
            }
        } else {
            flash.error = " Passwords do not match!"
        }


        redirect(controller: "user", action: "editProfile", message: msg)
    }

    def updateProfile(UserCO co) {
        def msg = flash.message ?: ""
        User user = User.findByUserName(session.user.userName)
        if (user) {
            if (co.firstName)
                user.firstName = co.firstName
            if (co.lastName)
                user.lastName = co.lastName
            if (co.userName)
                user.userName = co.userName
            if (user)
                user.photo = co.photo
            if (user.save(failOnError: true, flush: true)) {
                flash.message = message(code: "profile.successfully.changed")
                msg = flash.message
                session.user = user
            } else {
                flash.error = "Error updating profile"
            }
        } else {
            flash.error = "User Not Found"
        }
//        render view: "edit", model: [message: msg]
        redirect(controller: "user", action: "editProfile", params:[message: msg])
    }

    def editProfile(def msg) {
        render view: "edit", model: [message: msg]
    }


}
