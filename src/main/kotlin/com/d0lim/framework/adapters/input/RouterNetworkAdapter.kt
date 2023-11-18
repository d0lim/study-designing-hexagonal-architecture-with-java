package com.d0lim.framework.adapters.input

import com.d0lim.application.usecases.RouterNetworkUseCase
import com.d0lim.domain.entity.Router
import com.d0lim.domain.vo.IP
import com.d0lim.domain.vo.Network
import com.d0lim.domain.vo.RouterId

abstract class RouterNetworkAdapter(
    private val routerNetworkUseCase: RouterNetworkUseCase,
) {
    protected lateinit var router: Router

    protected fun addNetworkToRouter(params: Map<String, String>): Router {
        val routerId = RouterId.withId(params["routerId"] ?: throw RuntimeException("routerId not provided"))
        val network = Network(
            address = IP(address = params["address"] ?: throw RuntimeException("address not provided")),
            name = params["name"] ?: throw RuntimeException("name not provided"),
            cidr = params["cidr"]?.toInt() ?: throw RuntimeException("cidr not provided"),
        )

        return routerNetworkUseCase.addNetworkToRouter(routerId, network)
    }

    abstract fun processRequest(requestParams: Any): Router
}
