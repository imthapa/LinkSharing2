package linksharing


class ResourceInterceptor {

    ResourceInterceptor() {
        match(controller: 'resource', action: 'delete')
    }

    boolean before() {
        log.info("inside before of resource ")
        User loggedInUser = session.user;
        Resource resource = Resource.get(params.id)
        if (resource) {
            if (loggedInUser.admin || resource.createdBy.userName.equals(loggedInUser.userName)) {
                return true
//            render flash.error("sorry!! your are not allowed to delete the ${resource.description} resource")
//            return true
            } else {
                render("sorry!! your are not allowed to delete the ${resource.description} resource")
//            render("sorry!! your are not allowed to delete the ${resource.description} resource")
            }
        } else {
            render "resoure doesn't exist"
        }

    }

    boolean after() { true }

    void afterView() {
        // no-op
    }
}
