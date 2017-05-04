package linksharing

import com.ttnd.linksharing.util.Visibility
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(LinkResource)
class LinkResourceSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect: "fix me"
        true == false
    }

    //todo Q29) Add toString for linkresource with url
    def "test for toString"() {
        setup:
        Resource linkResource = new LinkResource(url: url)
        String link = linkResource.toString()

        expect:
        link == flag

        where:
        url                     | flag
        "http://www.google.com" | "http://www.google.com"
    }
}
