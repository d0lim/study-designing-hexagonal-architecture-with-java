package com.d0lim.domain.entity

import com.d0lim.domain.vo.IP
import com.d0lim.domain.vo.Network
import com.d0lim.domain.vo.RouterId
import com.d0lim.domain.vo.RouterType

data class Router(
    private val routerId: RouterId,
    val routerType: RouterType,
    private val networkSwitch: Switch,
) {
    companion object {
        fun filterRouterByType(routerType: RouterType): (Router) -> Boolean =
            if (routerType == RouterType.CORE) ::isCore else ::isEdge

        fun isCore(router: Router): Boolean = router.routerType == RouterType.CORE

        fun isEdge(router: Router): Boolean = router.routerType == RouterType.EDGE
    }

    fun addNetworkToSwitch(network: Network) {
        this.networkSwitch.addNetwork(network)
    }

    fun createNetwork(address: IP, name: String, cidr: Int) =
        Network(address, name, cidr)

    fun retrieveNetworks(): List<Network> {
        return networkSwitch.networks
    }
}
