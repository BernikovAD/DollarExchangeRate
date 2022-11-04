package com.example.dollarexchangerate.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element

@Entity(tableName = "rate")
data class Rate(

    @PrimaryKey
    @ColumnInfo(name = "date")
    var date: String,

    @ColumnInfo(name = "nominal")
    var nominal: String,

    @ColumnInfo(name = "value")
    var value: String
)