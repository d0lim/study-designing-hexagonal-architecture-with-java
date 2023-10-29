package com.d0lim.domain.vo

data class Network(
    val address: IP,
    val name: String,
    val cidr: Int,
) {
    init {
        if (cidr < 1 || cidr > 32) {
            throw IllegalArgumentException("Invalid CIDR value")
        }
    }
}
