package example

import grails.testing.gorm.DomainUnitTest
import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.*

class CompanyControllerSpec extends Specification implements ControllerUnitTest<CompanyController>, DomainUnitTest<Company> {

    void """index should return 1 company matching the composed query
            parameters but instead ignores the second set of detached criteria
            and returns both companies matching the intial detached criteria"""() {
        given: '3 companies with properties not matching the query parameters'
        new Company(name: 'yahoo', ownerName: 'carl').save(flush: true)
        new Company(name: expectedCompanyName, ownerName: 'henry').save(flush: true)
        new Company(name: 'oci', ownerName: 'tom').save(flush: true)

        and: '1 company matching the query parameters'
        new Company(name: expectedCompanyName, ownerName: expectedOwnerName).save(flush: true)

        expect: '4 companies'
        Company.list().size() == 4

        when:"The index action is executed"
        controller.failingIndex()

        then:"The model is correct"
        model.companyList
        model.companyCount == 1
        model.companyList[0].name == expectedCompanyName
        model.companyList[0].ownerName == expectedOwnerName

        where:
        expectedOwnerName = 'john'
        expectedCompanyName = 'google'
    }

    void """index should return 1 company matching the composed query
            parameters and does when the detached criteria is created in the same method"""() {
        given: '3 companies with properties not matching the query parameters'
        new Company(name: 'yahoo', ownerName: 'carl').save(flush: true)
        new Company(name: expectedCompanyName, ownerName: 'henry').save(flush: true)
        new Company(name: 'oci', ownerName: 'tom').save(flush: true)

        and: '1 company matching the query parameters'
        new Company(name: expectedCompanyName, ownerName: expectedOwnerName).save(flush: true)

        expect: '4 companies'
        Company.list().size() == 4

        when:"The index action is executed"
        controller.workingIndex()

        then:"The model is correct"
        model.companyList
        model.companyCount == 1
        model.companyList[0].name == expectedCompanyName
        model.companyList[0].ownerName == expectedOwnerName

        where:
        expectedOwnerName = 'john'
        expectedCompanyName = 'google'
    }
}
