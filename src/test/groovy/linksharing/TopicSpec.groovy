package linksharing

import com.ttnd.linksharing.util.Visibility
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Topic)
@Mock(User)
class TopicSpec extends Specification {
    static User user = new User()


    def setup() {
    }

    def cleanup() {
    }
//
//    void "test something"() {
//        expect: "fix me"
//        true == false
//    }

    @Unroll
    def "validating topic"() {
        setup:
      //  mockDomain(User)
        User user1 = new User(firstName: "ishwar", lastName: "mani",
                email: "ishwar@ttn.com", userName: "ishwar", password: "123456",
                active: true, photo: [], admin: false,
                dateCreated: new Date(), lastUpdated: new Date())
        user1.save()
        Topic topic = new Topic(name: "java", createdBy: user1,
                dateCreated: new Date(), lastUpdated: new Date(),
                visibility: Visibility.PRIVATE)
        topic.save()
        Topic topic2 = new Topic(name: name, createdBy: createdBy,
                dateCreated: dateCreated, lastUpdated: lastUpdated,
                visibility: visibility)
//        boolean res = topic2.validate()
        boolean result = topic2.save() == null ? false : true;
        user = User.findById(1)
        println(user)
        expect:
//        res == v
        result == valid

        where:
        name   | createdBy | dateCreated | lastUpdated | visibility        | valid
//        "java" | user       | new Date()  | new Date()  | Visibility.PRIVATE | false
        "json" | user      | new Date()  | new Date()  | Visibility.PUBLIC | true
        "json" | user      | new Date()  | new Date()  | Visibility.PUBLIC | false
//        "java" | user      | new Date()  | new Date()  | Visibility.PRIVATE | true
//        "java" | new User() | null        | new Date()  | Visibility.PRIVATE | false
//        "java" | new User() | new Date()  | new Date()  | null               | false
//        null   | new User() | new Date()  | new Date()  | Visibility.PRIVATE | false
//        ""     | new User() | new Date()  | new Date()  | Visibility.PRIVATE | false
//        "c++"  | new User() | new Date()  | new Date()  | "Private"          | false


    }
}
