package com.d0lim.framework.adapters.output.file.json

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

@JsonInclude(value = JsonInclude.Include.NON_NULL)
data class SwitchJson(
    @JsonProperty("switchId")
    val switchId: UUID,

    @JsonProperty("ip")
    val ip: IPJson,

    @JsonProperty("switchType")
    val switchType: SwitchTypeJson,

    @JsonProperty("networks")
    val networks: List<NetworkJson>,
)
