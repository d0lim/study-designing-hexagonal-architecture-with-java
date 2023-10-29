package com.d0lim.domain.vo

import java.util.*

data class SwitchId(
    val id: UUID,
) {
    companion object {
        fun SwitchId.withId(id: String) =
            SwitchId(UUID.fromString(id))

        fun SwitchId.withoutId() =
            SwitchId(UUID.randomUUID())
    }
}
