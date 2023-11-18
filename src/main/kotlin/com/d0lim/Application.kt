package com.d0lim

import com.d0lim.application.ports.input.RouterNetworkInputPort
import com.d0lim.application.ports.output.RouterNetworkOutputPort
import com.d0lim.application.usecases.RouterNetworkUseCase
import com.d0lim.framework.adapters.input.RouterNetworkAdapter
import com.d0lim.framework.adapters.input.rest.RouterNetworkRestAdapter
import com.d0lim.framework.adapters.input.stdin.RouterNetworkCliAdapter
import com.d0lim.framework.adapters.output.file.RouterNetworkFileAdapter
import com.d0lim.framework.adapters.output.h2.RouterNetworkH2Adapter
import com.sun.net.httpserver.HttpServer
import java.io.IOException
import java.net.InetSocketAddress
import java.util.*

class Application {
    private lateinit var inputAdapter: RouterNetworkAdapter
    private lateinit var useCase: RouterNetworkUseCase
    private lateinit var outputPort: RouterNetworkOutputPort

    fun setAdapter(adapter: String?) {
        when (adapter) {
            "rest" -> {
                outputPort = RouterNetworkH2Adapter.getInstance()
                useCase = RouterNetworkInputPort(outputPort)
                inputAdapter = RouterNetworkRestAdapter(useCase)
                rest()
            }

            else -> {
                outputPort = RouterNetworkFileAdapter.getInstance()
                useCase = RouterNetworkInputPort(outputPort)
                inputAdapter = RouterNetworkCliAdapter(useCase)
                cli()
            }
        }
    }

    private fun cli() {
        val scanner = Scanner(System.`in`)
        inputAdapter.processRequest(scanner)
    }

    private fun rest() {
        try {
            println("REST endpoint listening on port 8080...")
            val httpserver = HttpServer.create(InetSocketAddress(8080), 0)
            inputAdapter.processRequest(httpserver)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}

fun main(args: Array<String>) {
    var adapter = "cli"
    if (args.isNotEmpty()) {
        adapter = args[0]
    }
    Application().setAdapter(adapter)
}
