package com.d0lim.framework.adapters.output.h2

import com.d0lim.application.ports.output.RouterNetworkOutputPort
import com.d0lim.domain.entity.Router
import com.d0lim.domain.vo.RouterId
import com.d0lim.framework.adapters.output.h2.data.RouterData
import com.d0lim.framework.adapters.output.h2.mappers.RouterH2Mapper.toDomain
import com.d0lim.framework.adapters.output.h2.mappers.RouterH2Mapper.toH2
import jakarta.persistence.EntityManager
import jakarta.persistence.Persistence
import jakarta.persistence.PersistenceContext

class RouterNetworkH2Adapter private constructor() : RouterNetworkOutputPort {
    companion object {
        private var instance: RouterNetworkH2Adapter? = null

        fun getInstance(): RouterNetworkH2Adapter {
            return instance ?: RouterNetworkH2Adapter().also {
                instance = it
            }
        }
    }

    @PersistenceContext
    private var em: EntityManager? = null

    init {
        setUpH2Database()
    }

    override fun fetchRouterById(routerId: RouterId): Router {
        val routerData = em!!.getReference(RouterData::class.java, routerId.id)
        return toDomain(routerData)
    }

    override fun persistRouter(router: Router): Boolean {
        val routerData = toH2(router)
        em!!.persist(routerData)
        return true
    }

    private fun setUpH2Database() {
        val entityManagerFactory = Persistence.createEntityManagerFactory("inventory")
        val em = entityManagerFactory.createEntityManager()
        this.em = em
    }
}
