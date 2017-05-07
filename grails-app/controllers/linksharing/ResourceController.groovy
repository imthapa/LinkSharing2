package linksharing

import com.ttnd.linksharing.co.ResourceSearchCO
import com.ttnd.linksharing.vo.RatingInfoVO

class ResourceController {

    def index() {}

    //todo Domain2 - Q3. Use read() for /topic/show and load() for /resource/delete & /topic/delete action.
    def delete(Integer id) {
        Resource resource = Resource.load(id)
        Resource resource1 = resource
        resource.delete()
        render "$resource1 is successfully deleted"
    }

    //todo GORM2 - Q2b Add search action in a resource controller, which will search if q parameter is set and it will set visibility of resourcesearchco to public
    def search(ResourceSearchCO resourceSearchCO){
        resourceSearchCO.validate()
            log.info("${resourceSearchCO.errors.allErrors}")
        render "unsuccessful"
        if (resourceSearchCO.hasErrors()){
        }
        else{
            log.info("successfully done")
            render "successfully done"
        }

    }

    //TODO GORM2 Q3d) Call getRatingInfo method from resource show action
    def show(Long id){
        RatingInfoVO ratingInfoVO = Resource.getRatingInfo(id)
        render"""
                    totalVotes:${ratingInfoVO.totalVotes}
                    averageScore:${ratingInfoVO.averageScore}
                    totalScore:$ratingInfoVO.totalScore
                    """

    }

    def updateReadItem(Long id,Boolean isRead){
        String str = ReadingItem.changeIsRead(id,isRead)
        render "${str}"
    }
}
