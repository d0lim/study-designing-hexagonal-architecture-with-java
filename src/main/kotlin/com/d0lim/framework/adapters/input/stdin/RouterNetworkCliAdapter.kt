package com.d0lim.framework.adapters.input.stdin

import com.d0lim.application.usecases.RouterNetworkUseCase
import com.d0lim.domain.entity.Router
import com.d0lim.domain.vo.Network
import com.d0lim.domain.vo.RouterId

class RouterNetworkCliAdapter(
    private val routerNetworkUseCase: RouterNetworkUseCase,
) {
    fun addNetwork(routerId: RouterId, network: Network): Router {
        return routerNetworkUseCase.addNetworkToRouter(routerId, network)
    }
}
