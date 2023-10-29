package com.d0lim.framework.adapters.input.stdin

import com.d0lim.application.usecases.RouterViewUseCase
import com.d0lim.domain.entity.Router
import com.d0lim.domain.vo.RouterType

class RouterViewCLIAdapter(
    private val routerViewUseCase: RouterViewUseCase,
) {
    fun obtainRelatedRouters(type: String): List<Router> {
        return routerViewUseCase.getRouters(
            Router.filterRouterByType(RouterType.valueOf(type)),
        )
    }
}
