package linksharing

import com.ttnd.linksharing.vo.UserDetailsVO

class LinkSharingTagLib {
    static defaultEncodeAs = [taglib:'text']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    static namespace = "ls"

    def trendingTopic = { attr, body ->
        out << render(template: "/topic/show", model: [topics: Topic.getTrendingTopics()])
    }

    /*
    def topicSearched = { attr, body ->
        out << render(template: "/topic/show", model: [topics: Topic.findAllBy()])
    }
    */

    def topPosts = { attr, body ->
        out << render(template: "/topic/posts", model: [postsList: Resource.topPost()])
    }

    def topicsCreated = { attr, body ->
        out << render(template: "/topic/show", model: [postsList: User.allCreatedTopics(session.user)])
    }

    def loggedInUser = { attr, body ->
        User user = session.user

        UserDetailsVO userDetailsVO = new UserDetailsVO()
        userDetailsVO.userFullName = user.getFullName()
        userDetailsVO.userName = user.userName
        userDetailsVO.subscriptionCount = Subscription.countByUser(user)
        userDetailsVO.topicCount = Topic.countByCreatedBy(user)
        userDetailsVO.userId = user.id

        out << render(template: "/user/show", model: [users: userDetailsVO])
    }

}
