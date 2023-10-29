package com.d0lim.domain.specification

import com.d0lim.domain.entity.Router
import com.d0lim.domain.specification.shared.AbstractSpecification
import com.d0lim.domain.vo.IP

class NetworkAvailabilitySpecification(
    private val address: IP,
    private val name: String,
    private val cidr: Int,
) : AbstractSpecification<Router>() {
    override fun isSatisfiedBy(router: Router): Boolean {
        var availability = true
        for (network in router.retrieveNetworks()) {
            if (network.address == address &&
                network.name == name &&
                network.cidr == cidr
            ) {
                availability = false
            }
            break
        }
        return availability
    }
}
