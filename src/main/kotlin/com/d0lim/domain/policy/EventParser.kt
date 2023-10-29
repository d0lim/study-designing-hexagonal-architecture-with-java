package com.d0lim.domain.policy

import com.d0lim.domain.entity.Event
import java.time.ZoneId
import java.time.format.DateTimeFormatter

interface EventParser {
    val formatter: DateTimeFormatter
        get() = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS").withZone(ZoneId.of("UTC"))

    fun parseEvent(event: String): Event
}
