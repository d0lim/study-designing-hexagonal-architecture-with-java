package com.d0lim.domain.policy

import com.d0lim.domain.entity.Event
import com.d0lim.domain.vo.Activity
import com.d0lim.domain.vo.EventId
import com.d0lim.domain.vo.Protocol
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.regex.Pattern

class RegexEventParser : EventParser {
    override fun parseEvent(event: String): Event {
        val regex = "(\"[^\"]+\")|\\S+"
        val pattern = Pattern.compile(regex, Pattern.MULTILINE)
        val matcher = pattern.matcher(event)

        val fields = arrayListOf<String>()
        while (matcher.find()) {
            fields.add(matcher.group(0))
        }

        val timestamp = LocalDateTime.parse(matcher.group(0), formatter).atOffset(ZoneOffset.UTC)
        val id = EventId(matcher.group(1))
        val protocol = Protocol.valueOf(matcher.group(2))
        val activity = Activity(matcher.group(3), matcher.group(5))

        return Event(id, protocol, activity, timestamp)
    }
}
