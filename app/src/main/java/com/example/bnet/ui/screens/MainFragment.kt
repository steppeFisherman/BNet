package com.example.bnet.ui.screens

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bnet.R
import com.example.bnet.databinding.FragmentMainBinding
import com.example.bnet.ui.BaseFragment
import com.example.bnet.ui.MainActivity
import com.example.bnet.ui.adapters.Listener
import com.example.bnet.ui.adapters.MainFragmentAdapter
import com.example.bnet.ui.model.DataUi
import com.example.bnet.utils.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(),
    MenuProvider {

    private val vm by viewModels<MainFragmentViewModel>()
    private lateinit var mAdapter: MainFragmentAdapter
    private lateinit var mSearchView: SearchView
    private val searchService = SearchService.Base()
    private var mList = listOf<DataUi>()

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentMainBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setupWithNavController(findNavController())
        (requireActivity() as MainActivity).setSupportActionBar(binding.toolbar)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner)

        mAdapter = MainFragmentAdapter(object : Listener {
            override fun invoke(dataUi: DataUi) {
                findNavController().navigate(
                    R.id.action_mainFragment_to_detailsFragment,
                    bundleOf(ARGS_KEY to dataUi)
                )
            }
        }, LoadImage.Base())


        binding.apply {
            rvFragmentMain.layoutManager = GridLayoutManager(view.context, 2)
            rvFragmentMain.adapter = mAdapter
        }

        vm.loading.observe(viewLifecycleOwner) { loading ->
            if (loading) binding.progressBar.visible(true)
            else binding.progressBar.visible(false)
        }

        vm.error.observe(viewLifecycleOwner) { errorType ->
            when (errorType.ordinal) {
                0 -> view.snackLong(R.string.no_connection_exception_message)
                1 -> view.snackLong(R.string.http_exception_message)
                2 -> view.snackLong(R.string.database_exception_message)
                3 -> view.snackLong(R.string.generic_exception_message)
            }
        }

        searchService.mListFilteredLiveData.observe(viewLifecycleOwner) {
            mAdapter.submitList(it)
        }

        vm.data.observe(viewLifecycleOwner) {
            mList = it
            mAdapter.submitList(it)

        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.fragmentMain_search -> {
                mSearchView = menuItem.actionView as SearchView
                mSearchView.queryHint = getString(R.string.hint)
                search(mSearchView)
                true
            }
            else -> false
        }
    }

    private fun search(searchView: SearchView) {
        searchView.setOnQueryTextListener(SearchViewListener { newText ->
            searchService.result(mList, newText)
        })
    }

    companion object {
        const val ARGS_KEY = "ARGS_KEY"
    }
}