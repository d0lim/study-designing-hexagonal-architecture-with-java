package com.d0lim.framework.adapters.output.h2.data

import jakarta.persistence.Embeddable
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated

@Embeddable
class IPData(
    val address: String,
) {

    @Enumerated(EnumType.STRING)
    val protocol = if (address.length <= 15) {
        ProtocolData.IPV4
    } else {
        ProtocolData.IPV6
    }
}
