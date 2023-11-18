package com.d0lim.framework.adapters.output.h2.mappers

import com.d0lim.domain.entity.Router
import com.d0lim.domain.entity.Switch
import com.d0lim.domain.vo.IP
import com.d0lim.domain.vo.Network
import com.d0lim.domain.vo.RouterId
import com.d0lim.domain.vo.RouterType
import com.d0lim.domain.vo.SwitchId
import com.d0lim.domain.vo.SwitchType
import com.d0lim.framework.adapters.output.h2.data.IPData
import com.d0lim.framework.adapters.output.h2.data.NetworkData
import com.d0lim.framework.adapters.output.h2.data.RouterData
import com.d0lim.framework.adapters.output.h2.data.RouterTypeData
import com.d0lim.framework.adapters.output.h2.data.SwitchData
import com.d0lim.framework.adapters.output.h2.data.SwitchTypeData
import java.util.*

object RouterH2Mapper {
    fun toDomain(routerData: RouterData): Router {
        val routerType = RouterType.valueOf(routerData.routerType.name)
        val routerId = RouterId.withId(routerData.routerId.toString())
        val switchId = SwitchId.withId(routerData.networkSwitch.switchId.toString())
        val switchType = SwitchType.valueOf(routerData.networkSwitch.switchType.toString())
        val ip = IP(address = routerData.networkSwitch.ip.address)
        val networks: List<Network> = getNetworksFromData(routerData.networkSwitch.networks)
        val networkSwitch = Switch(switchId, switchType, networks.toMutableList(), ip)
        return Router(routerId = routerId, routerType = routerType, networkSwitch = networkSwitch)
    }

    fun toH2(router: Router): RouterData {
        val routerTypeData = RouterTypeData.valueOf(router.routerType.toString())
        val routerId = router.routerId.id
        val switchId = router.networkSwitch?.switchId?.id ?: throw RuntimeException("null network switch provided")
        val switchTypeData = SwitchTypeData.valueOf(router.networkSwitch.switchType.toString())
        val ipData = IPData(
            address = router.networkSwitch.address.address,
        )
        val networkDataList = getNetworksFromDomain(router.retrieveNetworks(), routerId)
        val switchData = SwitchData(
            switchId = switchId,
            routerId = routerId,
            switchType = switchTypeData,
            networks = networkDataList,
            ip = ipData,
        )
        return RouterData(routerId, routerTypeData, switchData)
    }

    private fun getNetworksFromData(networkData: List<NetworkData>): List<Network> {
        return networkData.map { data: NetworkData ->
            Network(
                IP(address = data.ip.address),
                data.name,
                data.cidr,
            )
        }
    }

    private fun getNetworksFromDomain(networks: List<Network>, switchId: UUID): List<NetworkData> {
        return networks.map { network: Network ->
            NetworkData(
                id = 0L,
                switchId = switchId,
                ip = IPData(address = network.address.address),
                name = network.name,
                cidr = network.cidr,
            )
        }
    }
}
