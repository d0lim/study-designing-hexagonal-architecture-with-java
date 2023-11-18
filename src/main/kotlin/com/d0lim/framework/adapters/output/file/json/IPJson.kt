package com.d0lim.framework.adapters.output.file.json

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(value = JsonInclude.Include.NON_NULL)
data class IPJson(
    @JsonProperty("address")
    val address: String,
) {
    @JsonProperty("protocol")
    val protocol: ProtocolJson = if (address.length <= 15) {
        ProtocolJson.IPV4
    } else {
        ProtocolJson.IPV6
    }
}
