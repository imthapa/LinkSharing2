package linksharing

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?" {
            constraints {
                // apply constraints here
            }
        }

        //todo Q8) If user is not found then flash.error is set to 'User not found' and flash.error is rendered - Urlmapping is updated for / action to controller login action index
        "/"(controller:"login",action:"index")
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
