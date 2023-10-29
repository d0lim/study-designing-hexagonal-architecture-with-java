package com.d0lim.application.ports.out

import com.d0lim.domain.entity.Router

interface RouterViewOutputPort {
    fun fetchRouters(): List<Router>
}
