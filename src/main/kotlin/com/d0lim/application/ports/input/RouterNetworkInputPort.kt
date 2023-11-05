package com.d0lim.application.ports.input

import com.d0lim.application.ports.output.RouterNetworkOutputPort
import com.d0lim.application.usecases.RouterNetworkUseCase
import com.d0lim.domain.entity.Router
import com.d0lim.domain.service.NetworkOperation
import com.d0lim.domain.vo.Network
import com.d0lim.domain.vo.RouterId

class RouterNetworkInputPort(
    private val routerNetworkOutputPort: RouterNetworkOutputPort
) : RouterNetworkUseCase {
    override fun addNetworkToRouter(routerId: RouterId, network: Network): Router {
        val router = fetchRouter(routerId)
        return createNetwork(router, network)
    }

    private fun fetchRouter(routerId: RouterId): Router =
        routerNetworkOutputPort.fetchRouterById(routerId)

    private fun createNetwork(router: Router, network: Network): Router {
        val newRouter = NetworkOperation.createNewNetwork(router, network)

        // when persist successes, returns new persistedRouter but when fails, returns original router
        return if (persistRouter(newRouter)) newRouter else router
    }

    private fun persistRouter(router: Router): Boolean =
        routerNetworkOutputPort.persistRouter(router)
}
