package linksharing


class ApplicationInterceptor {

    ApplicationInterceptor(){
    //    matchAll()
    }

    //todo Q11) Add Application Interceptor with logging params for all controller and actions
    boolean before() {
        log.info("the current controller and action are -> controller:$controllerName action:$actionName" )
        true }

    boolean after() { true }

    void afterView() {
        // no-op
    }
}
