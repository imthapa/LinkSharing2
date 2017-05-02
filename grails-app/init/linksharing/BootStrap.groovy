package linksharing

import com.ttnd.linksharing.util.Visibility

class BootStrap {

    def init = { servletContext ->
       // insert()
        createUsers()
    }
    def destroy = {
    }

    def createUsers(){
        log.info "============admin creating========"
        User admin = new User(firstName: "ishwar", lastName: "mani",
                email: "ishwar@ttn.com", userName: "ishwar", password: "123456",
                active: true, photo: [], admin: true,
                dateCreated: new Date(), lastUpdated: new Date())
        admin.save(flush:true,failOnError:true)
        log.info "============admin created========"
        if(admin.hasErrors())
            log.error(admin.errors.allErrors)
        if(User.count() == 0){
            log.info "============normal user creating========"
            User normalUser = new User(firstName: "uday", lastName: "pratap",
                    email: "", userName: "uday", password: "123456",
                    active: true, photo: [], admin: false,
                    dateCreated: new Date(), lastUpdated: new Date())
            normalUser.save(flush:true,failOnError:true)
            log.info "============normal user creating========"
            if(normalUser.hasErrors())
                log.error(normalUser.errors.allErrors)
        }else{
            log.info("user table already contains a user,thus new user can't be inserted...")
        }


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
