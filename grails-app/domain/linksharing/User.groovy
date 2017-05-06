package linksharing

class User {
    String firstName;
    String lastName;
    String email;
    String userName
    String password;
    String confirmPassword
    boolean active;
    byte[] photo;
    boolean admin
    Date dateCreated
    Date lastUpdated

    static hasMany = [topic: Topic, subscription: Subscription, resource: Resource, readItem: ReadingItem]

    //todo Domain2 - Q2 User should be default sorted by the id desc so that latest created user comes first
    static mapping = {
        sort id : 'desc'
        photo(sqlType: 'longBlob')
    }
    static transients = ['name', 'confirmPassword']

    String name() {
        return "$firstName $lastName"
    }


    static constraints = {
        email(unique: true, blank: false, nullable: false, email: true)
        userName(unique: true)
        //todo Q23. If user is set the success should be rendered - Validation message should be on email(null,blank,email,unique), username(null,blank,unique), firstName(null,blank), lastName (null,blank), password(null,blank,minsize), confirmPassword (null,blank,customvalidator)
        password(blank: false, nullable: false, size: 5..15, validator: {
            val, obj ->
                //for register and update false and true respectively.
                if (!obj.id)
                    val == obj.confirmPassword
        })
        firstName(blank: false, nullable: false)
        lastName(blank: false, nullable: false)
        photo(nullable: true)
        admin(nullable: true)
        active(nullable: true)
        confirmPassword(nullable: true, blank: true)
    }

    @Override
    String toString() {
        return "$userName"
    }
}
