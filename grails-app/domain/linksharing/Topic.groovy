package linksharing

import com.ttnd.linksharing.co.SearchCO
import com.ttnd.linksharing.util.Seriousness
import com.ttnd.linksharing.util.Visibility
import com.ttnd.linksharing.vo.InboxVO
import com.ttnd.linksharing.vo.TopicVO
import com.ttnd.linksharing.vo.UserDetailsVO
import org.hibernate.sql.JoinType

class Topic {

    String name
    Visibility visibility
    Date lastUpdated
    Date dateCreated

    static hasMany = [subscription: Subscription, resource: Resource]
    static belongsTo = [createdBy: User]

    static transients = ['subscribedTopics', 'trendingTopics']
    //todo Domain2 - Q1. Add default sorting: - Topic domain should be default sorted by name asc
    static mapping = {
        //id composite: ['createdBy','name']
        //createdBy column: createdBy
        sort name: 'asc'
    }

    static constraints = {
        name(unique: 'createdBy', nullable: false, blank: false)
        createdBy(nullable: false)
        visibility(nullable: false, validator: {
            val ->
                val instanceof Visibility
        })
    }
    //todo Q8. Creator of topic should automatically be subscribed to topic (Use after insert event of topic)
    def afterInsert() {
        //todo Q10. WithNewSession in after insert because it will not work without it
        //org.springframework.dao.InvalidDataAccessApiUsageException:
        Topic.withNewSession {
            //todo Q11. Seriousness should be very serious for auto subscribed topic in after insert
            Subscription subscription = new Subscription(user: createdBy, topic: this, seriousness: Seriousness.VERY_SERIOUS)
            // topic.addToSubscriptions(subscription)
            this.addToSubscription(subscription)
            // subscription.save()
            //todo Q9. Errors should be logged if topic or subscriptions is not saved
            if (subscription.hasErrors()) {
                log.error("subscription failed for $subscription.errors.allErrors")
            } else {
                log.info("$createdBy has subscribed $this")
            }
        }
    }

    // todo Q21) toString should be implemented for Topic with topic name and for User with username
    @Override
    public String toString() {
        return "$name";
    }

    //todo GORM2 Q4b) Create static method getTrendingTopics in Topic domain which will return list of TopicVO
/*  //sql query : select t.id,visibility,t.created_by_id,name,count(t.id) c from topic t join resource r on t.id = r.topic_id group by (t.id) order by c desc;
   4. Add resource show action and get trending topics also
    - Public Topic with maximum resources is considered as a trending topic
    - Create static method getTrendingTopics in Topic domain which will return list of TopicVO
    - TopicVO will have id,name,visibility,count,createdBy fields
    - Use create alias and groupproperty in criteria
    - Use count for getting count of resources of a topic
    - Use multiple order statement first one ordered by resource count and second one ordered by topic name
    - Maximum 5 records should be shown
    - Topic with maximum resource should come first
**/
    /*   static def getTrendingTopics(){
           List result = Topic.createCriteria().list {
   //            resultTransform(Transformers.aliasToBean(TopicVO))
               projections{
                   property('id')
                   property('name')
                   property('visibility')
                   count('id','count')
                   property('createdBy')
               }
               eq('visibility',Visibility.PRIVATE)
   //            createAlias('resource','res',JoinType.INNER_JOIN)
               groupProperty('id')
               count('id')
               maxResults 5
               order('count',"desc")
               order('name')
           }

           List fList = []
           result.each {
               TopicVO topicVO = new TopicVO()
               topicVO.id = it[0] //as long
               topicVO.name = it[1] //as String
               topicVO.visibility = it[2] //as String
               topicVO.count = it[3] //as int
   //            topicVO.createdBy = it[4] as User
               fList.add(topicVO)
           }
      *//*     result.collect({
             //  List brr = it.toString().tokenize("[")
               List arr = it.toString().split(",")
               println("thi is output ${arr.size()}")
   //           new TopicVO(id: arr[0].toString() as Integer,name: arr[1],visibility: Visibility.toVisibility(arr[2]),count: arr[3] as Integer,createdBy: arr[4])
           })
           result.collect({
               println("thi is output ${it.class}")
               new TopicVO(id: it[0],name: it[1],visibility: it[2],count: it[3],createdBy: it[4])
           })*//*
         //  new TopicVO(id: result[0],name: result[1],visibility: result[2],count: result[3],createdBy: result[4])
           println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh $result")
           result
       }
   */
    //todo Grails Views Q18) Create transient method getSubscribedUsers in topic domain to get all the subscribed users
    static def getSubscribedUsers(Topic topic) {
        List<UserDetailsVO> allSubsUsers = []
        List allSubscribedUsers = Subscription.createCriteria().list {
            projections {
                property('user')
            }
            eq('topic', topic)
        }

        println(allSubscribedUsers)
        allSubscribedUsers.each {
            User user = it
            allSubsUsers.add(new UserDetailsVO(userName: user.userName,
                    userFullName: user.fullName,
                    subscriptionCount: user.subscription.size(),
                    topicCount: user.topic.size(),
                    userId: user.id))

        }
        allSubsUsers
    }
/*

    static List<TopicVO> getTrendingTopics() {
        List<TopicVO> trendingTopics = []
        Resource.createCriteria().list {
            createAlias('topic', 't')
            projections {
                groupProperty("t.id")
                property("t.name")
                property("t.visibility")
                property("t.createdBy")
                count("t.id", "topicCount")
                count('t.subscription','subsCount')
            }
            order("topicCount", "desc")
            order("t.name", "desc")
            maxResults(5)
        }.each {
            trendingTopics.add(new TopicVO(id: it[0], name: it[1], visibility: it[2],
                    createdBy: it[3], count: it[4],subsCount: it[5]))
        }
        return trendingTopics
    }
*/

    static def getTrendingTopics() {
        List<TopicVO> topicVOS = []
        List list = Resource.createCriteria().list {
            projections {
                property('topic')
            }
            count('topic', 'resCount')
            groupProperty('topic')
            order('resCount')
            maxResults 5
        }
        list.each {
            Topic topic = it[0]
            topicVOS.add(new TopicVO(id: topic.id, name: topic.name, visibility: topic.visibility,
                    createdBy: topic.createdBy, count: it[1], subsCount: topic.subscription.size()))
        }
        topicVOS
    }

    static def getSearched(SearchCO searchCO) {
        List<InboxVO> searchResult = []
        List result = Topic.createCriteria().list() {
            createAlias("resource", "r", JoinType.LEFT_OUTER_JOIN)
            projections {
                property('name')
                property('r.id')
                property('r.description')
                property('r.createdBy')
            }
            if (searchCO && searchCO.q) {
                or {
                    ilike("name", "%${searchCO.q}%")
                    ilike("r.description", "%${searchCO.q}%")
                }
            }
//            maxResults 5
        }
        result.each {
            searchResult.add(new InboxVO(topicName: it[0], resourceID: it[1], createdBy: it[3], description: it[2]))
        }
        println("akkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk $searchResult}")
        searchResult
    }


}
