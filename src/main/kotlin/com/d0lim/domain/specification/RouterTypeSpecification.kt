package com.d0lim.domain.specification

import com.d0lim.domain.entity.Router
import com.d0lim.domain.specification.shared.AbstractSpecification
import com.d0lim.domain.vo.RouterType

class RouterTypeSpecification : AbstractSpecification<Router>() {
    override fun isSatisfiedBy(router: Router): Boolean {
        return router.routerType == RouterType.EDGE || router.routerType == RouterType.CORE
    }
}
