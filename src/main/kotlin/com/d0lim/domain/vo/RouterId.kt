package com.d0lim.domain.vo

import java.util.*

data class RouterId(
    val id: UUID,
) {
    companion object {
        fun RouterId.withId(id: String) =
            RouterId(UUID.fromString(id))

        fun RouterId.withoutId() =
            RouterId(UUID.randomUUID())
    }
}
