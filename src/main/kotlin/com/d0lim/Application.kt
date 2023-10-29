package com.d0lim

import com.d0lim.application.ports.input.RouterViewInputPort
import com.d0lim.framework.adapters.input.stdin.RouterViewCLIAdapter
import com.d0lim.framework.adapters.output.file.RouterViewFileAdapter

fun main() {
    val type = "EDGE"

    val routerViewUseCase = RouterViewInputPort(RouterViewFileAdapter)
    val cli = RouterViewCLIAdapter(routerViewUseCase)

    println(cli.obtainRelatedRouters(type))
}
