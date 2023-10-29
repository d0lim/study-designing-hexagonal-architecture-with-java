package com.d0lim.framework.adapters.output.file

import com.d0lim.application.ports.output.RouterViewOutputPort
import com.d0lim.domain.entity.Router
import com.d0lim.domain.vo.RouterId
import com.d0lim.domain.vo.RouterType
import java.io.BufferedReader
import java.io.InputStreamReader

object RouterViewFileAdapter : RouterViewOutputPort {
    override fun fetchRouters(): List<Router> {
        return readFileAsString()
    }

    private fun readFileAsString(): List<Router> {
        val routers: MutableList<Router> = ArrayList()
        try {
            BufferedReader(
                InputStreamReader(
                    RouterViewFileAdapter::class.java.getClassLoader().getResourceAsStream("routers.txt"),
                ),
            ).lines().use { stream ->
                stream.forEach { line: String ->
                    val routerEntry =
                        line.split(";".toRegex()).dropLastWhile { it.isEmpty() }
                            .toTypedArray()
                    val id = routerEntry[0]
                    val type = routerEntry[1]
                    val router = Router(RouterId.withId(id), RouterType.valueOf(type))
                    routers.add(router)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return routers
    }
}
