package com.d0lim.domain.service

import com.d0lim.domain.entity.Router

class RouterSearch {
    companion object {
        fun retrieveRouters(routers: List<Router>, predicate: (Router) -> Boolean): List<Router> {
            return routers.filter(predicate)
        }
    }
}
