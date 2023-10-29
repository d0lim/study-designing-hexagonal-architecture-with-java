package com.d0lim.domain.service

import com.d0lim.domain.entity.Router
import com.d0lim.domain.specification.CIDRSpecification
import com.d0lim.domain.specification.NetworkAmountSpecification
import com.d0lim.domain.specification.NetworkAvailabilitySpecification
import com.d0lim.domain.specification.RouterTypeSpecification
import com.d0lim.domain.vo.Network

class NetworkOperation {
    companion object {
        fun createNewNetwork(router: Router, network: Network): Router {
            val availabilitySpec =
                NetworkAvailabilitySpecification(network.address, network.name, network.cidr)
            val cidrSpec = CIDRSpecification()
            val routerTypeSpec = RouterTypeSpecification()
            val amountSpec = NetworkAmountSpecification()

            require(!cidrSpec.isSatisfiedBy(network.cidr)) { "CIDR is below " + CIDRSpecification.MINIMUM_ALLOWED_CIDR }

            require(availabilitySpec.isSatisfiedBy(router)) { "Address already exist" }

            if (amountSpec.and(routerTypeSpec).isSatisfiedBy(router)) {
                val newNetwork = router.createNetwork(network.address, network.name, network.cidr)
                router.addNetworkToSwitch(newNetwork)
            }

            return router
        }
    }
}
