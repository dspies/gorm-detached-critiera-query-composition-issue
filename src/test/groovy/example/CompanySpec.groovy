package example

import grails.gorm.DetachedCriteria
import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class CompanySpec extends Specification implements DomainUnitTest<Company> {

    void "defaultCriteria method should return a detached criteria instance"() {
        expect:
        Company.defaultCriteria('test') instanceof DetachedCriteria<Company>
    }
}
