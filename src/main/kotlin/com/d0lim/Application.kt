package com.d0lim

import com.d0lim.application.ports.input.RouterNetworkInputPort
import com.d0lim.domain.vo.IP
import com.d0lim.domain.vo.Network
import com.d0lim.domain.vo.RouterId
import com.d0lim.framework.adapters.input.stdin.RouterNetworkCliAdapter
import com.d0lim.framework.adapters.output.file.RouterNetworkFileAdapter

fun main() {
    val routerNetworkUseCase = RouterNetworkInputPort(RouterNetworkFileAdapter)
    val cli = RouterNetworkCliAdapter(routerNetworkUseCase)

    val routerId = RouterId.withId("ca23800e-9b5a-11eb-a8b3-0242ac130003")
    val network = Network(IP("20.0.0.0"), "Marketing", 8)
    val router = cli.addNetwork(routerId, network)
    println(router)
}
