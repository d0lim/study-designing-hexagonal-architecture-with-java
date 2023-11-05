package com.d0lim.framework.adapters.output.file

import com.d0lim.application.ports.output.RouterNetworkOutputPort
import com.d0lim.domain.entity.Router
import com.d0lim.domain.entity.Switch
import com.d0lim.domain.vo.IP
import com.d0lim.domain.vo.Network
import com.d0lim.domain.vo.RouterId
import com.d0lim.domain.vo.RouterType
import com.d0lim.domain.vo.SwitchId
import com.d0lim.domain.vo.SwitchType

object RouterNetworkFileAdapter : RouterNetworkOutputPort {
    init {
        createSampleRouters()
    }

    private val routers = mutableListOf<Router>()

    override fun fetchRouterById(routerId: RouterId): Router {
        return routers.find { it.routerId == routerId }
            ?: throw RuntimeException("Failed to find router with routerId: $routerId")
    }

    override fun persistRouter(router: Router): Boolean {
        return this.routers.add(router)
    }

    private fun createSampleRouters() {
        val routerId = RouterId.withId("ca23800e-9b5a-11eb-a8b3-0242ac130003")
        val network = Network(IP("10.0.0.0"), "HR", 8)
        val networkSwitch =
            Switch(SwitchId.withoutId(), SwitchType.LAYER3, mutableListOf(network), IP("9.0.0.9"))
        val router = Router(routerId, RouterType.EDGE, networkSwitch)
        routers.add(router)
    }
}
