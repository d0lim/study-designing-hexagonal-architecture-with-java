package com.d0lim.domain.vo

data class IP(
    private val address: String,
) {

    private val protocol: Protocol

    init {
        if (address.length <= 15) {
            this.protocol = Protocol.IPV4
        } else {
            this.protocol = Protocol.IPV6
        }
    }
}
