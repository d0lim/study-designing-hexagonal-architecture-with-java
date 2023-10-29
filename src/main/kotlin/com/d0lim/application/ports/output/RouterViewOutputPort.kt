package com.d0lim.application.ports.output

import com.d0lim.domain.entity.Router

interface RouterViewOutputPort {
    fun fetchRouters(): List<Router>
}
