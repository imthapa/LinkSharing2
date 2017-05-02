package linksharing

import com.ttnd.linksharing.util.Visibility


class Topic {

    String name
    static belongsTo = [createdBy: User]
    // User createdBy
    Date dateCreated
    Date lastUpdated
    Visibility visibility

    static hasMany = [subscription: Subscription, resource: Resource]
//    static mapping = {
//        //id composite: ['createdBy','name']
//        createdBy column: createdBy
//    }

    static constraints = {
        name(unique: 'createdBy', nullable: false, blank: false)
        createdBy(nullable: false)
        visibility(nullable: false, validator: {
            val ->
                val instanceof Visibility
        })
        //   dateCreated(nullable: false)
    }
}
