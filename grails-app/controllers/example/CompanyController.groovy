package example

import grails.gorm.DetachedCriteria

import grails.transaction.Transactional

@Transactional(readOnly = true)
class CompanyController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(){}

    def failingIndex(){

        String nameFilter
        String companyOwner
        nameFilter = 'google'
        companyOwner = 'john'

        DetachedCriteria<Company> defaultCriteria = Company.defaultCriteria(nameFilter)
        DetachedCriteria<Company> indexCriteria = defaultCriteria.where {
            ownerName == companyOwner
        }

        respond indexCriteria.list(params), model:[companyCount: indexCriteria.count()]
    }

    def workingIndex(){
        String nameFilter
        String companyOwner
        nameFilter = 'google'
        companyOwner = 'john'

        DetachedCriteria<Company> defaultCriteria = Company.where {
            name == nameFilter
        }
        DetachedCriteria<Company> indexCriteria = defaultCriteria.where {
            ownerName == companyOwner
        }

        respond indexCriteria.list(params), model:[companyCount: indexCriteria.count()]

    }
}
