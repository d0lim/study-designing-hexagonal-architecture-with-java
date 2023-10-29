package com.d0lim.domain.service

import com.d0lim.domain.entity.Event
import com.d0lim.domain.vo.ParsePolicyType

class EventSearch {
    fun retrieveEvents(unparsedEvents: List<String>, policyType: ParsePolicyType): List<Event> {
        return unparsedEvents.map { Event.parsedEvent(it, policyType) }
    }
}
