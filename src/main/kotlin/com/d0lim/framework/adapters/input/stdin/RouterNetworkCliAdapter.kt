package com.d0lim.framework.adapters.input.stdin

import com.d0lim.application.usecases.RouterNetworkUseCase
import com.d0lim.domain.entity.Router
import com.d0lim.domain.vo.Network
import com.d0lim.domain.vo.RouterId
import com.d0lim.framework.adapters.input.RouterNetworkAdapter
import com.d0lim.framework.adapters.output.file.mappers.RouterJsonFileMapper
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import java.util.*

class RouterNetworkCliAdapter(
    private val routerNetworkUseCase: RouterNetworkUseCase,
) : RouterNetworkAdapter(routerNetworkUseCase) {

    fun addNetwork(routerId: RouterId, network: Network): Router {
        return routerNetworkUseCase.addNetworkToRouter(routerId, network)
    }

    override fun processRequest(requestParams: Any): Router {
        val params = stdinParams(requestParams)
        router = addNetworkToRouter(params)
        val mapper = ObjectMapper()
        try {
            val routerJson = mapper.writeValueAsString(RouterJsonFileMapper.toJson(router))
            println(routerJson)
        } catch (e: JsonProcessingException) {
            e.printStackTrace()
        }
        return router
    }

    private fun stdinParams(requestParams: Any): Map<String, String> {
        val params = HashMap<String, String>()
        if (requestParams is Scanner) {
            val scanner: Scanner = requestParams
            println("Please inform the Router ID:")
            val routerId = scanner.nextLine()
            params["routerId"] = routerId
            println("Please inform the IP address:")
            val address = scanner.nextLine()
            params["address"] = address
            println("Please inform the Network Name:")
            val name = scanner.nextLine()
            params["name"] = name
            println("Please inform the CIDR:")
            val cidr = scanner.nextLine()
            params["cidr"] = cidr
        } else {
            throw IllegalArgumentException("Request with invalid parameters")
        }
        return params
    }
}
