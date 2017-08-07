package example

import grails.gorm.DetachedCriteria

class Company {

    String name
    String ownerName

    static constraints = {
    }

    static DetachedCriteria<Company> defaultCriteria(String companyName) {
        return Company.where {
            name == companyName
        }
    }
}
