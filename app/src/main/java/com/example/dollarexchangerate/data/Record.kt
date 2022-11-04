package com.example.dollarexchangerate.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "Record", strict = false)
data class Record  @JvmOverloads constructor(
    @field:Attribute(name = "Date")
    @param:Attribute(name = "Date")
    var date: String? = null,
    @field:Element(name = "Nominal")
    @param:Element(name = "Nominal")
    var nominal: String? = null,
    @field:Element(name = "Value")
    @param:Element(name = "Value")
    var value: String? = null
)