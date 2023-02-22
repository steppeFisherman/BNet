package com.example.bnet.utils

import android.widget.Filter
import android.widget.Filterable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bnet.ui.model.DataUi

interface SearchService {

    fun result(list: List<DataUi>, newText: String)

    class Base : Filterable, SearchService {

        private var mList = mutableListOf<DataUi>()
        private var mListFiltered = mutableListOf<DataUi>()

        private var mListLivaData = MutableLiveData<List<DataUi>>()
        val mListFilteredLiveData: LiveData<List<DataUi>>
            get() = mListLivaData

        @Suppress("UNCHECKED_CAST")
        override fun getFilter(): Filter {
            return object : Filter() {
                override fun performFiltering(charSequence: CharSequence?): FilterResults {
                    val key = charSequence.toString().lowercase().trim()
                    mListFiltered = if (key.isEmpty()) mList
                    else {

                        val newList = mutableListOf<DataUi>()
                        for (data in mList) {
                            val categoriesName = data.categoriesName.lowercase().trim()
                            val description = data.description.lowercase().trim()
                            val drugName = data.drugName.lowercase().trim()
                            if (categoriesName.contains(key)
                                || description.contains(key)
                                || drugName.contains(key)
                            ) newList.add(data)
                        }
                        newList
                    }
                    val filterResults = FilterResults()
                    filterResults.values = mListFiltered
                    filterResults.count = mListFiltered.size
                    return filterResults
                }

                override fun publishResults(
                    charSequence: CharSequence?,
                    filterResults: FilterResults?
                ) {
                    mListFiltered = filterResults?.values as MutableList<DataUi>
                    mListLivaData.value = mListFiltered
                }
            }
        }

        override fun result(list: List<DataUi>, newText: String) {
            mList = list.toMutableList()
            filter.filter(newText)
        }
    }
}