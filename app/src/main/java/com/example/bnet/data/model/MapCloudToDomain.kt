package com.example.bnet.data.model

import com.example.bnet.domain.model.DataDomain

interface MapCloudToDomain {

    fun transform(cloud: DataCloud): DataDomain

    class Base : MapCloudToDomain {
        override fun transform(cloud: DataCloud): DataDomain {
            return DataDomain(
                id = cloud.id,
                categoriesName = cloud.categories.name,
                categoriesImage = URL + cloud.categories.image,
                categoriesIcon = URL + cloud.categories.icon,
                description = cloud.description,
                drugName = cloud.name,
                drugImage = URL + cloud.image,
                drugAdvantage = cloud.fields[4].value
            )
        }

        companion object {
            private const val URL = "http://shans.d2.i-partner.ru"
        }
    }
}