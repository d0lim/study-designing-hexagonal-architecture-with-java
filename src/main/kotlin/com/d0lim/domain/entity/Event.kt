package com.d0lim.domain.entity

import com.d0lim.domain.policy.RegexEventParser
import com.d0lim.domain.vo.Activity
import com.d0lim.domain.vo.EventId
import com.d0lim.domain.vo.ParsePolicyType
import com.d0lim.domain.vo.Protocol
import java.time.OffsetDateTime

data class Event(
    private val id: EventId,
    private val protocol: Protocol,
    private val activity: Activity,
    private val timestamp: OffsetDateTime,
) : Comparable<Event> {
    companion object {
        fun parsedEvent(unparsedEvent: String, policyType: ParsePolicyType) = when (policyType) {
            ParsePolicyType.REGEX -> RegexEventParser().parseEvent(unparsedEvent)
            ParsePolicyType.SPLIT -> RegexEventParser().parseEvent(unparsedEvent)
        }
    }

    override fun compareTo(other: Event): Int {
        return timestamp.compareTo(other.timestamp)
    }
}
