package linksharing

class ReadingItem {
    Boolean isRead
    static belongsTo = [user: User, resource: Resource]
    Date dateCreated
    Date lastUpdated

    static constraints = {
        user(nullable: false)
        resource(nullable: false, unique: 'user')
        isRead(nullable: false)
    }
}
