package linksharing

import com.ttnd.linksharing.util.Visibility


class Topic{

    String name
    User createdBy
    Date dateCreated
    Date lastUpdated
    Visibility visibility

//    static mapping = {
//        id composite: ['createdBy','name']
//    }

    static constraints = {
        name(unique: true, nullable: false,blank: false)

//        visibility(nullable: false,validator: {
//            val ->
//                val instanceof Visibility
//        })
        dateCreated(nullable: false)
    }
}
