package linksharing

abstract class Resource {
    String description;
    Date dateCreated
    Date lastUpdated

    static belongsTo = [createdBy: User, topic: Topic]

    static hasMany = [ratings:ResourceRating,readingItems:ReadingItem]
    static constraints = {
        description(nullable: false, blank: false)
        createdBy(nullable: false)
        topic(nullable: false)

    }
}
