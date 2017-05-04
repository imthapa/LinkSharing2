package linksharing

class User {
    String firstName;
    String lastName;
    String email;
    String userName
    String password;
    boolean active;
    byte[] photo;
    boolean admin
    Date dateCreated
    Date lastUpdated

    static hasMany = [topic: Topic, subscription: Subscription, resource: Resource, readItem: ReadingItem]

    static mapping = {
        photo(sqlType: 'longBlob')
    }
    static transients = ['name']

    String name() {
        return "$firstName $lastName"
    }

    static constraints = {
        email(unique: true, blank: false, nullable: false, email: true)
        userName(unique: true)
        password(blank: false, nullable: false, size: 5..15)
        firstName(blank: false, nullable: false)
        lastName(blank: false, nullable: false)
        photo(nullable: true)
        admin(nullable: true)
        active(nullable: true)
    }
}
