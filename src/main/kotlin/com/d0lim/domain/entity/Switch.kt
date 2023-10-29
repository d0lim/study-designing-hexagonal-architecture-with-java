package com.d0lim.domain.entity

import com.d0lim.domain.vo.IP
import com.d0lim.domain.vo.Network
import com.d0lim.domain.vo.SwitchId
import com.d0lim.domain.vo.SwitchType

data class Switch(
    private val switchId: SwitchId,
    private val switchType: SwitchType,
    var networks: MutableList<Network>,
    private val address: IP,
) {
    fun addNetwork(network: Network) {
        networks.add(network)
    }
}
