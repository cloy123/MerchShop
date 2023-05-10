package com.monsieur.cloy.merchshop.presentation.events

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.monsieur.cloy.merchshop.R
import com.monsieur.cloy.merchshop.databinding.FragmentEventsBinding
import com.monsieur.cloy.merchshop.presentation.viewModels.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class EventsFragment : Fragment() {

    private var _binding: FragmentEventsBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerAdapter: EventRecyclerAdapter
    private val viewModel: MainViewModel by sharedViewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEventsBinding.inflate(inflater, container, false)
        initFunc()
        return binding.root
    }

    private fun initFunc() {

    }

}