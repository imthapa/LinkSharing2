package linksharing

import com.ttnd.linksharing.co.LinkCO
import grails.transaction.Transactional

@Transactional
class ResourceService {

    def serviceMethod() {

    }

    def delete(long id){
        Resource resource = Resource.load(id)
        if(resource){
            resource.delete()
            return "$resource is successfully deleted"
        }else{
            return "resource doesn't exist"
        }

    }

    def createResource(LinkCO linkCO) {
        log.info("${linkCO}")
        Resource resource = new LinkResource(url: linkCO.url,description: linkCO.description,
                topic: Topic.get(linkCO.id),createdBy: linkCO.createdBy)
        log.info("${resource}")
        resource.save(failOnError:true,flush:true)
        if(resource.hasErrors()){
            flash.error = "sorry resource can't be saved"
        }else{
            log.info("resource successfully saved")
//            flash.message = "resource successfully saved"
        }
    }
}
