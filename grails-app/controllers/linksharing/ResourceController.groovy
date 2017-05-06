package linksharing

class ResourceController {

    def index() {}

    //todo Domain2 - Q3. Use read() for /topic/show and load() for /resource/delete & /topic/delete action.
    def delete(Integer id) {
        Resource resource = Resource.load(id)
        Resource resource1 = resource
        resource.delete()
        render "$resource1 is successfully deleted"
    }
}
