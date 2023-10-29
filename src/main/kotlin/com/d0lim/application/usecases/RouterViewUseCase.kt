package com.d0lim.application.usecases

import com.d0lim.domain.entity.Router

interface RouterViewUseCase {
    fun getRouters(filter: (Router) -> Boolean): List<Router>
}
