package linksharing

import com.ttnd.linksharing.util.UserCO

//todo Q1. Add logincontroller with index,loginHandler and logout action
class LoginController {

    //todo Q2. LoginController index action will check if there is session.user exists or not if exist forward to user controller index action else render failure
    def index() {
        session['user'] = User.read(1)
        log.info("${session.user}")
        if (session.user != null) {
//          log.info("aldhfkhdfkjhfkjhk")
            redirect(controller: 'user', action: 'index')
        } else {
            render "Failure"
            //forward(action : 'loginHandler')
        }
            //render "hello welcome to grails"
    }

    //todo Q5. LoginController loginHandler action will take 2 argument username and password
    // url for viewing - http://localhost:8080/login/loginHandler?username=ishwar&password=qwertyuiop
    def loginHandler(String username, String password) {
        //dummy data
        //   render ("params : username = ${username} , password : ${password}")

        //todo Q6. If Loginhandler action finds user with given username and password then it will check user active or not if active set session.user to user and redirect request to login index action
        User user = User.findByUserNameAndPassword(username, password)
        if (user != null) {
            if (user.active) {
                session['user'] = user
                redirect(action: 'index')
            } else {
                flash.error = "Your account is not active"
                //todo Q7. If user is not active then set flash.error 'Your account is not active'
                render "${flash.error}"
            }

        } else {
            //todo Q8. If user is not found then flash.error is set to 'User not found' and flash.error is rendered - Urlmapping is updated for / action to controller login action index
            flash.error = "User not found"
            render "${flash.error}"
        }
    }

    //todo Q4 .Login Controller logout action will do session.invalidate and forward the request to login controller index action
    def logout() {
        session.invalidate()
        forward(action: 'index')
    }

    //todo Q21. Update bootstrap for user creation Create register action in login controller to register user
    //localhost:8080/login/register?firstName=akhil&lastName=rawat&email=akhil@ttn.com&userName=akhil&password=123456&active&admin=true
    def register(UserCO userCO){
        User user = new User(firstName: userCO.firstName,lastName: userCO.lastName,email:userCO.email,
                                            userName: userCO.userName,password: userCO.password,
                                            active: userCO.active,admin: userCO.admin)
        user.save()
        //todo Q22. Errors with proper message properties should be rendered if user is not set and flash message should be set.
        if(user.hasErrors()){
            flash.error = "sorry you can't be registered."
            //log.info(user.errors.allErrors)
//            render user.errors.allErrors
            render "${flash.error}"
        }

        else
            render "user registered successfully"
    }

}
