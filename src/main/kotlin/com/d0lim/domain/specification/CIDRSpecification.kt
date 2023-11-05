package com.d0lim.domain.specification

import com.d0lim.domain.specification.shared.AbstractSpecification

class CIDRSpecification : AbstractSpecification<Int>() {
    companion object {
        const val MINIMUM_ALLOWED_CIDR = 8
    }

    override fun isSatisfiedBy(cidr: Int): Boolean {
        return cidr >= MINIMUM_ALLOWED_CIDR
    }
}
