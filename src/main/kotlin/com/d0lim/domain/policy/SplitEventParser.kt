package com.d0lim.domain.policy

import com.d0lim.domain.entity.Event
import com.d0lim.domain.vo.Activity
import com.d0lim.domain.vo.EventId
import com.d0lim.domain.vo.Protocol
import java.time.LocalDateTime
import java.time.ZoneOffset

class SplitEventParser : EventParser {
    override fun parseEvent(event: String): Event {
        val fields = listOf(
            *event.split(" ".toRegex()).dropLastWhile { it.isEmpty() }
                .toTypedArray(),
        )

        val timestamp = LocalDateTime.parse(fields[0], formatter).atOffset(ZoneOffset.UTC)
        val id = EventId(fields[1])
        val protocol = Protocol.valueOf(fields[2])
        val activity = Activity(fields[3], fields[5])

        return Event(id, protocol, activity, timestamp)
    }
}
