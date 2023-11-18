package com.d0lim.framework.adapters.output.file

import com.d0lim.application.ports.output.RouterNetworkOutputPort
import com.d0lim.domain.entity.Router
import com.d0lim.domain.vo.RouterId
import com.d0lim.framework.adapters.output.file.json.RouterJson
import com.d0lim.framework.adapters.output.file.mappers.RouterJsonFileMapper.toDomain
import com.d0lim.framework.adapters.output.file.mappers.RouterJsonFileMapper.toJson
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.nio.file.Paths

class RouterNetworkFileAdapter private constructor() : RouterNetworkOutputPort {

    companion object {
        private var instance: RouterNetworkFileAdapter? = null

        fun getInstance(): RouterNetworkFileAdapter {
            return instance ?: RouterNetworkFileAdapter().also {
                instance = it
            }
        }
    }

    init {
        readJsonFile()
    }

    private var routers: List<RouterJson>? = null
    private var resource: InputStream = javaClass.getClassLoader().getResourceAsStream("inventory.json")
        ?: throw RuntimeException("failed to read inventory.json")
    private var objectMapper: ObjectMapper = ObjectMapper()

    override fun fetchRouterById(routerId: RouterId): Router {
        return routers?.firstOrNull { it.routerId == routerId.id }?.let { toDomain(it) }
            ?: throw RuntimeException("router with id $routerId not found")
    }

    override fun persistRouter(router: Router): Boolean {
        val routerJson = toJson(router)
        try {
            val localDir = Paths.get("").toAbsolutePath().toString()
            val file = File("$localDir/inventory.json")
            file.delete()
            objectMapper!!.writeValue(file, routerJson)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return true
    }

    private fun readJsonFile() {
        try {
            routers = objectMapper.readValue(
                resource,
                object : TypeReference<List<RouterJson>>() {},
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
