package com.example.bnet.ui.screens

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.bnet.R
import com.example.bnet.databinding.FragmentDetailsBinding
import com.example.bnet.ui.BaseFragment
import com.example.bnet.ui.MainActivity
import com.example.bnet.ui.model.DataUi
import com.example.bnet.utils.LoadImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentDetailsBinding>(), MenuProvider {

    private val imageLoad = LoadImage.Base()

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentDetailsBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarDetails.setupWithNavController(findNavController())
        (requireActivity() as MainActivity).setSupportActionBar(binding.toolbarDetails)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner)

        val dataUi = arguments?.getParcelable(MainFragment.ARGS_KEY) ?: DataUi()

        binding.apply {
            toolbarDetails.title = dataUi.drugName
            imageLoad.load(requireContext(), drugIconDetails, dataUi.categoriesIcon)
            imageLoad.load(requireContext(), drugImage, dataUi.drugImage)
            drugName.text = dataUi.drugName
            drugDescription.text = dataUi.drugAdvantage
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.details_toolbar_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            android.R.id.home -> {
                requireActivity().onBackPressed()
                true
            }
            else -> false
        }
    }
}
