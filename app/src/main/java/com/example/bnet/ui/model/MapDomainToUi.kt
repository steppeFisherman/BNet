package com.example.bnet.ui.model

import com.example.bnet.domain.model.DataDomain

interface MapDomainToUi {

    fun transform(domain: DataDomain): DataUi

    class Base : MapDomainToUi {
        override fun transform(domain: DataDomain): DataUi {
            return DataUi(
                id = domain.id,
                categoriesName = domain.categoriesName,
                categoriesImage = domain.categoriesImage,
                categoriesIcon = domain.categoriesIcon,
                description = domain.description,
                drugName = domain.drugName,
                drugImage = domain.drugImage,
                drugAdvantage = domain.drugAdvantage
            )
        }
    }
}