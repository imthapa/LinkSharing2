package linksharing

import com.ttnd.linksharing.util.Visibility
import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by ishwar on 6/5/17.
 */
class VisibilitySpec extends Specification {

    //todo Domain2 Q7) Create a method in visibility enum to convert string into enum and write test case for the same
    @Unroll
    def "validating toVisibilty"() {
        setup:
        def visibility = Visibility.toVisibility(str);

        expect:
        visibility == result

        where:
        str       | result
        "private" | Visibility.PRIVATE
        "public"  | Visibility.PUBLIC
        "PRIVATE" | Visibility.PRIVATE
        "PUBLIC"  | Visibility.PUBLIC
        ""        | "sorry can be coverted to visibility enum"
    }

}
