package linksharing

class ReadingItem {
    Boolean isRead
    Date dateCreated
    Date lastUpdated

    static belongsTo = [user: User, resource: Resource]

    static constraints = {
        user(nullable: false)
        resource(nullable: false, unique: 'user')
        isRead(nullable: false)
    }
}
