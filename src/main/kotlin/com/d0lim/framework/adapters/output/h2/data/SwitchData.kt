package com.d0lim.framework.adapters.output.h2.data

import jakarta.persistence.AttributeOverride
import jakarta.persistence.AttributeOverrides
import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import jakarta.persistence.SecondaryTable
import jakarta.persistence.Table
import java.io.Serializable
import java.util.*

@Entity
@Table(name = "switches")
@SecondaryTable(name = "networks")
class SwitchData(
    @Id
    @Column(name = "switch_id", columnDefinition = "uuid", updatable = false)
    val switchId: UUID,

    @Column(name = "router_id")
    var routerId: UUID,

    @Enumerated(EnumType.STRING)
    @Column(name = "switch_type")
    var switchType: SwitchTypeData,

    @OneToMany
    @JoinColumn(table = "networks", name = "switch_id", referencedColumnName = "switch_id")
    var networks: List<NetworkData>,

    @Embedded
    @AttributeOverrides(
        AttributeOverride(
            name = "address",
            column = Column(name = "network_addresses"),
        ),
        AttributeOverride(
            name = "protocol",
            column = Column(name = "network_protocol"),
        ),
    )
    var ip: IPData,
) : Serializable
