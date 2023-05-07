package com.monsieur.cloy.merchshop.presentation.catalog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.monsieur.cloy.merchshop.R
import com.monsieur.cloy.merchshop.databinding.FragmentCatalogBinding
import com.monsieur.cloy.merchshop.presentation.viewModels.MainViewModel
import com.monsieur.cloy.merchshop.utilits.changeToolBar
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CatalogFragment : Fragment() {

    private var _binding: FragmentCatalogBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCatalogBinding.inflate(inflater, container, false)
        initFunc()
        return binding.root
    }

    private fun initFunc() {

    }

    override fun onStart() {
        super.onStart()
        changeToolBar(menu = false, homeButton = false, "Каталог")
    }

}