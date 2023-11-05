package com.d0lim.domain.vo

import java.util.*

data class SwitchId(
    val id: UUID,
) {
    companion object {
        fun withId(id: String) =
            SwitchId(UUID.fromString(id))

        fun withoutId() =
            SwitchId(UUID.randomUUID())
    }
}
