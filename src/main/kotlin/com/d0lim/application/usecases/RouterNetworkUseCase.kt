package com.d0lim.application.usecases

import com.d0lim.domain.entity.Router
import com.d0lim.domain.vo.Network
import com.d0lim.domain.vo.RouterId

interface RouterNetworkUseCase {
    fun addNetworkToRouter(routerId: RouterId, network: Network): Router
}
