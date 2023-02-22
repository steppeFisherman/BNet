package com.example.bnet.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataUi(
    val id: Int = 0,
    val categoriesName: String = "",
    val categoriesImage: String = "",
    val categoriesIcon: String = "",
    val description: String = "",
    val drugName: String = "",
    val drugImage: String = "",
    val drugAdvantage: String = "",
) : Parcelable
