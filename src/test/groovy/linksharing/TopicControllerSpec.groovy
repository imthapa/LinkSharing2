package linksharing

import com.ttnd.linksharing.util.Seriousness
import com.ttnd.linksharing.util.Visibility
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(TopicController)
@Mock(User)
class TopicControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect: "fix me"
        true == false
    }

    //todo Domain2 Q11) Write the test case for the Topic save.
    def "test for save()"() {
        given: "saving a Topic"
        User user = Mock(User)
        TopicController controller = Spy(TopicController)
        Topic topic = new Topic(name: name, createdBy: createdBy, visibility: visibilty)

        when:
        def result = controller.save(topic,"casual")

        then:
        1 * save

        /*where:
        name      | createdBy  | visibilty         | output
        'kavaOak' | new User() | Visibility.PUBLIC | "kavaOak"
*/
    }

    //todo Domain2 Q12) Write a test case for topic deletion.
    def "test for delete()"(){

    }
}
