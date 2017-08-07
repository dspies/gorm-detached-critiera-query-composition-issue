package detachedcriteria

import example.Company

class BootStrap {

    def init = { servletContext ->

        new Company(name: 'google', ownerName: 'john').save(flush: true)
        new Company(name: 'yahoo', ownerName: 'carl').save(flush: true)
        new Company(name: 'google', ownerName: 'henry').save(flush: true)
        new Company(name: 'oci', ownerName: 'tom').save(flush: true)
        new Company(name: 'google', ownerName: 'carlos').save(flush: true)
        new Company(name: 'something', ownerName: 'dave').save(flush: true)
        new Company(name: 'other', ownerName: 'ted').save(flush: true)

    }
    def destroy = {
    }
}
