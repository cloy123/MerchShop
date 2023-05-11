package com.monsieur.cloy.merchshop.presentation.events

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.monsieur.cloy.merchshop.R
import com.monsieur.cloy.merchshop.databinding.FragmentEventsBinding
import com.monsieur.cloy.merchshop.presentation.catalog.ProductRecyclerAdapter
import com.monsieur.cloy.merchshop.presentation.viewModels.MainViewModel
import com.monsieur.cloy.merchshop.utilits.changeToolBar
import com.monsieur.cloy.merchshop.utilits.replaceFragment
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
        initRecyclerAdapter()
    }

    override fun onStart() {
        super.onStart()
        changeToolBar(menu = false, homeButton = false, "События")
    }

    private fun initRecyclerAdapter(){
        recyclerAdapter = EventRecyclerAdapter(requireContext())
        recyclerAdapter.setOnClickListener {
            replaceFragment(EventFragment(it))
        }
        binding.eventsRecycler.adapter = recyclerAdapter
        viewModel.events.observe(requireActivity(), Observer {
            recyclerAdapter.setItems(it)
        })
    }
}