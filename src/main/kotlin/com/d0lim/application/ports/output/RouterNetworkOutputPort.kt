package com.d0lim.application.ports.output

import com.d0lim.domain.entity.Router
import com.d0lim.domain.vo.RouterId

interface RouterNetworkOutputPort {
    fun fetchRouterById(routerId: RouterId): Router

    fun persistRouter(router: Router): Boolean
}
