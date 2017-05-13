package linksharing

import com.ttnd.linksharing.co.LinkCO
import com.ttnd.linksharing.co.ResourceSearchCO
import com.ttnd.linksharing.vo.DetailedPostVO
import com.ttnd.linksharing.vo.RatingInfoVO

class ResourceController {

    def resourceService

    def index() {
//        "${Resource.topPost().each {render it[0]}}"
//        render "${Resource.topPost()}"
    }

    //todo Domain2 - Q3. Use read() for /topic/show and load() for /resource/delete & /topic/delete action.
    def delete(long id) {
        String result = resourceService.delete(id)
        render result
    }

    def save(LinkCO linkCo) {
        linkCo.createdBy = session.user
        resourceService.createResource(linkCo)
        redirect(controller: 'user', action: 'index')
    }

    //todo GORM2 - Q2b Add search action in a resource controller, which will search if q parameter is set and it will set visibility of resourcesearchco to public
    def search(ResourceSearchCO resourceSearchCO) {
        resourceSearchCO.validate()
        log.info("${resourceSearchCO.errors.allErrors}")
        render "unsuccessful"
        if (resourceSearchCO.hasErrors()) {
        } else {
            log.info("successfully done")
            render "successfully done"
        }
    }

    //TODO GORM2 Q3d) Call getRatingInfo method from resource show action
    def show(Long id) {
        RatingInfoVO ratingInfoVO = Resource.getRatingInfo(id)
        render """
                    totalVotes:${ratingInfoVO.totalVotes}
                    averageScore:${ratingInfoVO.averageScore}
                    totalScore:$ratingInfoVO.totalScore
                    """
    }

    def updateReadItem(Long id, Boolean isRead) {
        String str = ReadingItem.changeIsRead(id, isRead)
        render "${str}"
    }

    def topPost() {
        List list = Resource.topPost()
//        render("${list}")
        render(template: "/topic/posts", model: ['postsList': list]);
//        render "${Resource.topPost()}"
    }

    def viewPost(long id) {
        DetailedPostVO detailedPostVO = Resource.getResourceDetails(id)
        render view: "viewPost", model: [detailedPost: detailedPostVO]
    }
}
