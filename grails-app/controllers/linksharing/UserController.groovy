package linksharing

import com.ttnd.linksharing.co.SearchCO

//todo Q3. Add User controller with index action that will render text 'user dahsboard'
class UserController {

    def index(SearchCO searchCO) {
        log.info("wkwkwwkjwkjwk $searchCO.q")
        List list = User.getUnReadResources(searchCO)
        render "$list"
    }


}
