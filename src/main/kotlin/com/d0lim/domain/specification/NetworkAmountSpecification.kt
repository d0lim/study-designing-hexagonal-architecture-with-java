package com.d0lim.domain.specification

import com.d0lim.domain.entity.Router
import com.d0lim.domain.specification.shared.AbstractSpecification

class NetworkAmountSpecification : AbstractSpecification<Router>() {
    companion object {
        const val MAXIMUM_ALLOWED_NETWORKS = 6
    }

    override fun isSatisfiedBy(router: Router): Boolean {
        return router.retrieveNetworks().size <= MAXIMUM_ALLOWED_NETWORKS
    }
}
