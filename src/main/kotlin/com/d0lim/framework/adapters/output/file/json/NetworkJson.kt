package com.d0lim.framework.adapters.output.file.json

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(value = JsonInclude.Include.NON_NULL)
data class NetworkJson(
    @JsonProperty("ip")
    val ip: IPJson,
    @JsonProperty("networkName")
    val networkName: String,
    @JsonProperty("networkCidr")
    val cidr: String,
)
