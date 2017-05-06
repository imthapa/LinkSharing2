package linksharing

//todo Q13. Create loginCheck interceptor which will work all the controller except login
class LoginCheckInterceptor {

    LoginCheckInterceptor(){
       // matchAll().excludes(controller:'login')
    }

    //todo Q12. Add session check filter in application interceptor
    boolean before() {
        log.info "inside before login interceptor"
        if(session.user == null){
            //todo Q14. If session.user is not set then redirect user to login index, this should be done in interceptor - user index action should render session user username
            redirect(controller: "user",action:"index")
        }
        true
    }

    boolean after() {
        true
    }

    void afterView() {
        // no-op
    }
}
