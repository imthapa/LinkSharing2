package linksharing

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(DocumentResource)
class DocumentResourceSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect: "fix me"
        true == false
    }

    //todo Q30) Add test case for document resource and linkresource tostring
    def "test for toString"() {
        setup:
        Resource documentResource = new DocumentResource(filePath: filepath)
        String link = documentResource.toString()

        expect:
        link == flag

        where:
        filepath                           | flag
        "http://www.google.com/groovy.pdf" | "http://www.google.com/groovy.pdf"
    }
}
