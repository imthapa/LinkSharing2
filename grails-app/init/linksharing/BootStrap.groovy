package linksharing

import com.ttnd.linksharing.util.Visibility

class BootStrap {

    def init = { servletContext ->
        insert()
    }
    def destroy = {
    }

    void insert(){
        User user = new User(firstName: "ishwar", lastName: "mani",
                email: "ishwar@ttn.com", userName: "ishwar", password: "123456",
                active: true, photo: [], admin: false,
                dateCreated: new Date(), lastUpdated: new Date())
        user.save()
        Topic topic = new Topic(name: "java", createdBy: User.get(1),
                dateCreated: new Date(), lastUpdated: new Date(),
                visibility: Visibility.PRIVATE)
        topic.save()
    }
}
