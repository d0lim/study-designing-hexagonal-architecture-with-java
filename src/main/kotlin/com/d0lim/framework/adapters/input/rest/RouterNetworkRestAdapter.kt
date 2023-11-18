package com.d0lim.framework.adapters.input.rest

import com.d0lim.application.usecases.RouterNetworkUseCase
import com.d0lim.domain.entity.Router
import com.d0lim.framework.adapters.input.RouterNetworkAdapter
import com.d0lim.framework.adapters.output.file.mappers.RouterJsonFileMapper
import com.fasterxml.jackson.databind.ObjectMapper
import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpServer
import java.io.UnsupportedEncodingException
import java.net.URLDecoder
import kotlin.collections.set

class RouterNetworkRestAdapter(
    private val routerNetworkUseCase: RouterNetworkUseCase,
) : RouterNetworkAdapter(routerNetworkUseCase) {

    override fun processRequest(requestParams: Any): Router {
        val params: MutableMap<String, String> = HashMap()
        if (requestParams is HttpServer) {
            requestParams.createContext("/network/add") { exchange: HttpExchange ->
                if ("GET" == exchange.requestMethod) {
                    val query = exchange.requestURI.rawQuery
                    httpParams(query, params)
                    router = addNetworkToRouter(params)
                    val mapper = ObjectMapper()
                    val routerJson = mapper.writeValueAsString(RouterJsonFileMapper.toJson(router))
                    exchange.responseHeaders["Content-Type"] = "application/json"
                    exchange.sendResponseHeaders(200, routerJson.toByteArray().size.toLong())
                    val output = exchange.responseBody
                    output.write(routerJson.toByteArray())
                    output.flush()
                } else {
                    exchange.sendResponseHeaders(405, -1)
                }
                exchange.close()
            }
            requestParams.executor = null
            requestParams.start()
        }
        return router
    }

    private fun httpParams(query: String, params: MutableMap<String, String>) {
        val noNameText = "Anonymous"

        val map = mutableMapOf<String, String>()
        query.split("&").forEach {
            val parts = it.split("=")
            if (parts.size == 2) {
                map[decode(parts[0])] = decode(parts[1])
            }
        }

        params["routerId"] = map["routerId"] ?: noNameText
        params["address"] = map["address"] ?: noNameText
        params["name"] = map["name"] ?: noNameText
        params["cidr"] = map["cidr"] ?: noNameText
    }

    private fun decode(encoded: String): String {
        return try {
            URLDecoder.decode(encoded, "UTF-8")
        } catch (e: UnsupportedEncodingException) {
            throw RuntimeException("UTF-8 is a required encoding", e)
        }
    }
}
