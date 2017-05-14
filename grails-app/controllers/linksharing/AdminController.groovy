package linksharing

class AdminController {

    def index() {
        List<User> userList = User.findAll()
        render view: "dashboard", model: ["users": userList]
    }

}
