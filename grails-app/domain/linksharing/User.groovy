package linksharing

import com.ttnd.linksharing.co.SearchCO
import com.ttnd.linksharing.dto.ResourceDTO
import com.ttnd.linksharing.vo.InboxVO
import com.ttnd.linksharing.vo.SubscriptionVO
import org.hibernate.sql.JoinType
import org.hibernate.transform.Transformers

class User {
    String firstName;
    String lastName;
    String email;
    String userName
    String password;
    String confirmPassword
    boolean active = false;
    byte[] photo;
    boolean admin
    Date dateCreated
    Date lastUpdated

    static hasMany = [topic: Topic, subscription: Subscription, resource: Resource, readItem: ReadingItem]

    //todo Domain2 - Q2 User should be default sorted by the id desc so that latest created user comes first
    static mapping = {
        sort id: 'desc'
        photo(sqlType: 'longBlob')
        subscription lazy: false
    }
    static transients = ['fullName', 'confirmPassword', 'subscribedTopics']

    String getFullName() {
        return "$firstName $lastName"
    }


    static constraints = {
        email(unique: true, blank: false, nullable: false, email: true)
        userName(unique: true)
        //todo Q23. If user is set the success should be rendered - Validation message should be on email(null,blank,email,unique), username(null,blank,unique), firstName(null,blank), lastName (null,blank), password(null,blank,minsize), confirmPassword (null,blank,customvalidator)
        password(blank: false, nullable: false, size: 5..15, validator: {
            val, obj ->
                //for register and update false and true respectively.
                if (!obj.id)
                    val == obj.confirmPassword
        })
        firstName(blank: false, nullable: false)
        lastName(blank: false, nullable: false)
        photo(nullable: true)
        admin(nullable: true)
        active(nullable: true)
        confirmPassword(nullable: true, blank: true)
    }

    /*
   //todo GORM2 Q6) Add Inbox feature on user/index when user is loggedin
  - Create method getUnReadResources in user domain which takes SearchCO argument and returns unreaditems of
    user from ReadingItem domain
  - The search should also work using user/index page, q parameter of SearchCO. If searchco.q is found then
    getUnReadResources method will search the items based on ilike of resource.description.
  - The pagination parameter should also be used in getUnReadResources criteria query. Create readingItem/changeIsRead action which
   takes Long id and Boolean isRead
  - User executeUpdate to change the isRead of readingItem with given id
  - If value returned by executeUpdate is 0 then render error else render success
  */

    def getUnReadResources(SearchCO searchCO) {
        List<InboxVO> InboxVOList = []
        List result = ReadingItem.createCriteria().list {
            createAlias("resource", "r", JoinType.LEFT_OUTER_JOIN)
            projections {
//                property('r.id')
                property('r.topic')
                property('r.description')
                property('r.createdBy')
            }
//            resultTransform (Transformers.aliasToBean(ResourceDTO))
            if (searchCO && searchCO.q) {
                ilike("r.description", "%${searchCO.q}%")
            }
            eq('isRead', false)
            eq('user', this)
            maxResults 5
        }
        result.each {
            InboxVOList.add(new InboxVO(topicName: it[0].name, createdBy: it[2],description: it[1]))
        }
        println(InboxVOList)
        InboxVOList
    }


    @Override
    String toString() {
        return "$userName"
    }

    static def getSubscribedTopic(User user) {
        List<SubscriptionVO> subscriptionVOList = []
        def subscriptionList = Subscription.createCriteria().list() {
            projections {
                property('topic')
                property('seriousness')
            }
            eq('user', user)
//            maxResults 5
        }
        subscriptionList.each {
            Topic topic = it[0]
            subscriptionVOList.add(new SubscriptionVO(topicName: topic.name, createdBy: topic.createdBy,
                    subsCount: topic.subscription.size(),
                    resCount: topic.resource.size(), visibility: topic.visibility, seriousness: it[1]))
        }
        println("$subscriptionVOList")
        subscriptionVOList
    }

}

