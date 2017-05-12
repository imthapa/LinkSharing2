package linksharing

class LinkSharingTagLib {
    static defaultEncodeAs = [taglib:'text']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    static namespace = "ls"

    def trendingTopic = { attr, body ->
        User
        out << render(template: "/topic/show", model: [topics: Topic.getTrendingTopics()])
    }

    def topicSearched = { attr, body ->
        User
        out << render(template: "/topic/show", model: [topics: Topic.findAllBy()])
    }
}
