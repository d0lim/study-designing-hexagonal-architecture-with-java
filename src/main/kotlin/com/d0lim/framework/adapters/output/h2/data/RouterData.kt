package com.d0lim.framework.adapters.output.h2.data

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.OneToOne
import jakarta.persistence.SecondaryTable
import jakarta.persistence.Table
import java.io.Serializable
import java.util.*

@Entity
@Table(name = "routers")
@SecondaryTable(name = "switches")
@MappedSuperclass
class RouterData(
    @Id
    @Column(name = "router_id", columnDefinition = "uuid", updatable = false)
    val routerId: UUID,

    @Embedded
    @Enumerated(EnumType.STRING)
    @Column
    var routerType: RouterTypeData,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(table = "switches", name = "router_id", referencedColumnName = "router_id")
    var networkSwitch: SwitchData,
) : Serializable
