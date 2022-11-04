package com.example.dollarexchangerate.data

import org.simpleframework.xml.*

@Root(name = "ValCurs", strict = false)
data class RateXml @JvmOverloads constructor(
    @field:ElementList(name = "Record", inline = true, required = false)
    @param:ElementList(name = "Record", inline = true, required = false)
    var rateList: List<Record>? = null
)
