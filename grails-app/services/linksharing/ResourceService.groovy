package linksharing

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
}
