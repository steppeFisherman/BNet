package com.example.bnet.data.model

import com.google.gson.annotations.SerializedName

class DataCloud(
    @SerializedName("categories")
    var categories: Categories = Categories(),
    @SerializedName("description")
    var description: String = "",
    @SerializedName("documentation")
    var documentation: String = "",
    @SerializedName("fields")
    var fields: List<Field> = listOf(),
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("image")
    var image: String = "",
    @SerializedName("name")
    var name: String = ""
) {
    data class Categories(
        @SerializedName("icon")
        var icon: String = "",
        @SerializedName("id")
        var id: Int = 0,
        @SerializedName("image")
        var image: String = "",
        @SerializedName("name")
        var name: String = ""
    )

    data class Field(
        @SerializedName("flags")
        var flags: Flags = Flags(),
        @SerializedName("group")
        var group: Int = 0,
        @SerializedName("image")
        var image: String = "",
        @SerializedName("name")
        var name: String = "",
        @SerializedName("show")
        var show: Int = 0,
        @SerializedName("type")
        var type: String = "",
        @SerializedName("value")
        var value: String = ""
    ) {
        data class Flags(
            @SerializedName("html")
            var html: Int = 0,
            @SerializedName("no_image")
            var noImage: Int = 0,
            @SerializedName("no_name")
            var noName: Int = 0,
            @SerializedName("no_value")
            var noValue: Int = 0,
            @SerializedName("no_wrap")
            var noWrap: Int = 0,
            @SerializedName("no_wrap_name")
            var noWrapName: Int = 0
        )
    }
}
