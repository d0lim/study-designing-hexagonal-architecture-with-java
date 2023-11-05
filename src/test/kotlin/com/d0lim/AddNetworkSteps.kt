package com.d0lim

import com.d0lim.domain.entity.Router
import com.d0lim.domain.specification.CIDRSpecification
import com.d0lim.domain.specification.NetworkAvailabilitySpecification
import com.d0lim.domain.vo.IP
import com.d0lim.domain.vo.Network
import com.d0lim.domain.vo.RouterId
import io.kotest.core.spec.style.BehaviorSpec

class AddNetworkSteps : BehaviorSpec({
    given("I provide a router ID and the network details") {
        val routerId = RouterId.withId("ca23800e-9b5a-11eb-a8b3-0242ac130003")
        val network = Network(IP("20.0.0.0"), "Marketing", 8)

        `when`("I found the router") {
            val router: Router = routerNetworkFileAdapter.fetchRouterById(routerId)

            and("The network address is valida dn doesn't already exist") {
                val availabilitySpec = NetworkAvailabilitySpecification(
                    network.address,
                    network.name,
                    network.cidr,
                )

                if (!availabilitySpec.isSatisfiedBy(router)) {
                    throw IllegalArgumentException("Address already exist")
                }

                given("The CIDRis valid") {
                    val cidrSpec = CIDRSpecification()
                    if (!cidrSpec.isSatisfiedBy(network.cidr)) {
                        throw IllegalArgumentException("CIDR is below ${CIDRSpecification.MINIMUM_ALLOWED_CIDR}")
                    }

                    then("Add the network to the router") {
                        router.addNetworkToSwitch(network)
                    }
                }
            }
        }
    }
})
