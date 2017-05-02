package linksharing

class ResourceRating {
    Integer score;
    Resource resource;
    Date dateCreated
    Date lastUpdated

   static belongsTo = [user:User]

    static constraints = {
        score(nullable: false,range: 1..5)
        user(nullable: false)
        resource(nullable: false,unique: 'user')
    }
}
