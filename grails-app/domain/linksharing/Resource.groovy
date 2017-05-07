package linksharing

import com.ttnd.linksharing.co.ResourceSearchCO
import com.ttnd.linksharing.vo.RatingInfoVO
import org.hibernate.sql.JoinType

abstract class Resource {
    String description;
    Date dateCreated
    Date lastUpdated

    static belongsTo = [createdBy: User, topic: Topic]
    static hasMany = [ratings: ResourceRating, readingItems: ReadingItem]

    // todo GORM-2  Q3.a)Create transient in resource ratingInfo and create method which will return RatingInfoVO
    static transients = ['ratingInfo']


    static constraints = {
        description(nullable: false, blank: false)
        createdBy(nullable: false)
        topic(nullable: false)

    }

    //todo GORM2 Q1c) Create named query 'search' which takes ResourceSearchCO as argument and find resources specific to topic id.
    //todo GORM2 Q2b) Updated Resource search named query and add condition to search topic with specified visibility
    static namedQueries = {
        search {
            ResourceSearchCO rsCo ->
                if (rsCo.topicId) {
                    eq('topic', Topic.get(rsCo.topicId))
                }
                if(rsCo.visibility){
                    eq('visibility',rsCo.visibility)
                }
        }
    }

    //todo GORM2 3c) Write criteria query to get the above information
    static def getRatingInfo(Long resourceId){
        List<Long> ratingInfoVO = ResourceRating.createCriteria().get {
            projections{
                count('score')
                avg('score')
                sum('score')

            }
//            eq('user',this)
            eq('resource',Resource.get(resourceId))
        }
        new RatingInfoVO(totalVotes: ratingInfoVO[0],averageScore: ratingInfoVO[1],totalScore: ratingInfoVO[2])

    }

    /*
    //todo 5. Add top post when user is not logged in
        - Resource with maximum number of votes will be top post
        -Only 5 posts should be shown in order of maximum vote count
        -Use groupProperty with id of resource otherwise lots of queries will be fired
        -Collect Resource list with resource id using getall rather then finder otherwise ordering will not be maintained
    */
    static def topPost(){
        List result = Resource.createCriteria().list {
            projections{
                property('id')
                property('topic')
                count('id')
//                property('filepath')
//                property('url')
            }
            createAlias('ratings','ratings',JoinType.INNER_JOIN)
            maxResults 5
            count('id','count')
            groupProperty('id')
            order('count','desc')
        }
        result
    }


    @Override
    public String toString() {
        return "Resource{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
