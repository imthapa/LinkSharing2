package linksharing

import com.ttnd.linksharing.vo.DetailedPostVO
import com.ttnd.linksharing.vo.PostsVO
import com.ttnd.linksharing.co.ResourceSearchCO
import com.ttnd.linksharing.vo.RatingInfoVO
import com.ttnd.linksharing.vo.TopicVO

abstract class Resource {
    String description;
    Date dateCreated
    Date lastUpdated

    static belongsTo = [createdBy: User, topic: Topic]
    static hasMany = [ratings: ResourceRating, readingItems: ReadingItem]

    // todo GORM2 Q3a) Create transient in resource ratingInfo and create method which will return RatingInfoVO
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
                'topic' {
                    if (rsCo.topicId) {
                        eq('id', topicId)
//                    eq('topic', topicId)   ???
                    }
                    if (rsCo.visibility) {
                        eq('visibility', rsCo.visibility)
                    }
                }

        }
    }

    //todo GORM2 Q3c) Write criteria query to get the above information
    static def getRatingInfo(Long resourceId) {
        List<Long> ratingInfoVO = ResourceRating.createCriteria().get {
            projections {
                count('score')
                avg('score')
                sum('score')

            }
//            eq('user',this)
            eq('resource', Resource.get(resourceId))
        }
        new RatingInfoVO(totalVotes: ratingInfoVO[0], averageScore: ratingInfoVO[1], totalScore: ratingInfoVO[2])
    }

    /*
    //todo GORM2 Q5) Add top post when user is not logged in
        -Resource with maximum number of votes will be top post
        -Only 5 posts should be shown in order of maximum vote count
        -Use groupProperty with id of resource otherwise lots of queries will be fired
        -Collect Resource list with resource id using getall rather then finder otherwise ordering will not be maintained
    */

    static def topPost() {
        def result = ResourceRating.createCriteria().list {
            projections {
                'resource' {
                    groupProperty('id')
                }
                count('id', 'count')
                order('count', 'desc')
            }
            maxResults 5
        }
        ArrayList fResult = []
        result.each {
            PostsVO p = new PostsVO()
            Resource resource = Resource.get(it[0])
            p.resourceID = it[0] as long//resource.id
            p.resourceDescription = resource.description;
            p.topicId = resource.topicId
            p.topicName = resource.topic.name
            fResult.add(p)
        }
        println(fResult)
        fResult

        /*    List result = Resource.createCriteria().list {
               projections{
                   property('id')
                   property('topic')
   //                count('id')
   //                property('filepath')
   //                property('url')
               }
           //    createAlias('ratings','ratings',JoinType.INNER_JOIN)
               count('id','count')
               groupProperty('id')
               order('count','desc')
               maxResults 5
           }
           result
           */
    }

    static def getAllResources(Topic topic) {
        List<PostsVO> allResources = []
        List resources = Resource.createCriteria().list {
            projections {
                property('id')
                property('description')
                property('topic')
            }
            eq('topic', topic)
        }
        resources.each {
            /* PostsVO p = new PostsVO()
             p.resourceID = it[1] as long//resource.id
             p.resourceDescription = it[1];
             p.topicId = it[2].id
             p.topicName = it[2].name*/
            Topic topic1 = it[2]
            allResources.add(new PostsVO(resourceID: it[0],
                    resourceDescription: it[1],
                    topicId: topic1.id,
                    topicName: topic1.name))
        }
        allResources
    }

    @Override
    public String toString() {
        return "Resource{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }

    static def getResourceDetails(long id) {
        RatingInfoVO ratingInfoVO = getRatingInfo(id)
        Resource resource = Resource.get(id)
        DetailedPostVO detailedPostVO = new DetailedPostVO(resourceID: id, description: resource.description,
                ratings: ratingInfoVO.averageScore, updated: resource.lastUpdated,
                userName: resource.createdBy.userName, fullName: resource.createdBy.fullName,
                topicName: resource.topic.name)
        detailedPostVO
    }
}
