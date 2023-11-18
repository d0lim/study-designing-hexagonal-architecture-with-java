package com.d0lim.framework.adapters.output.file.mappers

import com.d0lim.domain.entity.Router
import com.d0lim.domain.entity.Switch
import com.d0lim.domain.vo.IP
import com.d0lim.domain.vo.Network
import com.d0lim.domain.vo.RouterId
import com.d0lim.domain.vo.RouterType
import com.d0lim.domain.vo.SwitchId
import com.d0lim.domain.vo.SwitchType
import com.d0lim.framework.adapters.output.file.json.IPJson
import com.d0lim.framework.adapters.output.file.json.NetworkJson
import com.d0lim.framework.adapters.output.file.json.RouterJson
import com.d0lim.framework.adapters.output.file.json.RouterTypeJson
import com.d0lim.framework.adapters.output.file.json.SwitchJson
import com.d0lim.framework.adapters.output.file.json.SwitchTypeJson

object RouterJsonFileMapper {
    fun toDomain(routerJson: RouterJson): Router {
        val routerId = RouterId.withId(routerJson.routerId.toString())
        val routerType = RouterType.valueOf(routerJson.routerType.name)
        val switchId = SwitchId.withId(routerJson.networkSwitch.switchId.toString())
        val switchType = SwitchType.valueOf(routerJson.networkSwitch.switchType.toString())
        val ip = IP(address = routerJson.networkSwitch.ip.address)
        val networks = getNetworksFromJson(routerJson.networkSwitch.networks).toMutableList()
        val networkSwitch = Switch(
            switchId = switchId,
            switchType = switchType,
            networks = networks,
            address = ip,
        )
        return Router(routerId = routerId, routerType = routerType, networkSwitch = networkSwitch)
    }

    fun toJson(router: Router): RouterJson {
        val routerId = router.routerId.id
        val routerTypeJson = RouterTypeJson.valueOf(router.routerType.toString())
        val switchIdJson = router.networkSwitch!!.switchId.id
        val switchTypeJson = SwitchTypeJson.valueOf(router.networkSwitch.switchType.toString())
        val ipJson = IPJson(address = router.networkSwitch.address.address)
        val networkDataList = getNetworksFromDomain(router.retrieveNetworks())
        val switchJson = SwitchJson(switchIdJson, ipJson, switchTypeJson, networkDataList)
        return RouterJson(routerId, routerTypeJson, switchJson)
    }

    private fun getNetworksFromJson(networkJson: List<NetworkJson>): List<Network> {
        return networkJson.map { (ip, networkName, cidr): NetworkJson ->
            Network(
                IP(address = ip.address),
                networkName,
                cidr.toInt(),
            )
        }
    }

    private fun getNetworksFromDomain(networks: List<Network>): List<NetworkJson> {
        return networks.map { network: Network ->
            NetworkJson(
                IPJson(address = network.address.address),
                network.name,
                network.cidr.toString(),
            )
        }
    }
}
