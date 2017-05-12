package linksharing

//todo Q13. Create loginCheck interceptor which will work all the controller except login
class LoginCheckInterceptor {

    LoginCheckInterceptor(){
       // matchAll().excludes(controller:'login')
        match(controller: 'login',action: 'index')
        match(controller: 'user',action: 'index')
        match(controller: 'login',action: 'logout')
        match(controller: 'resource',action: 'delete')
        match(controller: 'resource',action: 'index')
        match(controller: 'resource',action: 'show')
        match(controller: 'resource',action: 'search')
        match(controller: 'subscription',action: 'save')
        match(controller: 'subscription',action: 'update')
        match(controller: 'subscription',action: 'delete')
    }

    //todo Q12. Add session check filter in application interceptor
    boolean before() {
        log.info "inside before login interceptor"
        if(session.user == null){
            //todo Q14. If session.user is not set then redirect user to login index, this should be done in interceptor - user index action should render session user username
            redirect(controller: "login",action:"loginHandler")
        }
        else{
            true
        }

    }

    boolean after() {
        true
    }

    void afterView() {
        // no-op
    }
}
