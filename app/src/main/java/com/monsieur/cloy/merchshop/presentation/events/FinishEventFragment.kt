package com.monsieur.cloy.merchshop.presentation.events

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.monsieur.cloy.domain.models.Event
import com.monsieur.cloy.merchshop.databinding.FragmentFinishEventBinding
import com.monsieur.cloy.merchshop.presentation.viewModels.MainViewModel
import com.monsieur.cloy.merchshop.utilits.backButton
import com.monsieur.cloy.merchshop.utilits.changeToolBar
import com.monsieur.cloy.merchshop.utilits.showToast
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class FinishEventFragment(val event: Event) : Fragment() {

    private var _binding: FragmentFinishEventBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by sharedViewModel()

    private lateinit var finishParticipantRecyclerAdapter: FinishParticipantRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFinishEventBinding.inflate(inflater, container, false)
        initFunc()
        return binding.root
    }

    fun initFunc(){

        viewModel.noteVisitResult.observe(requireActivity(), Observer {
            if(it != null){
                if(!it.isSuccessful){
                    showToast("Ошибка")
                }else if(it.isNoted){
                    showToast("Участвующие отмечены")
                    backButton()
                }else if(!it.isNoted){
                    showToast(it.errorMessage)
                }
            }
        })

        binding.btFinish.setOnClickListener {
            viewModel.finishEvent(event, finishParticipantRecyclerAdapter.participants!!)
        }
        initRecyclerParticipants()
        viewModel.eventParticipants.observe(requireActivity(), Observer {
            val participant = it.filter { it.eventId == event.id } as ArrayList
            finishParticipantRecyclerAdapter.setItems(participant)
        })
    }

    override fun onStart() {
        super.onStart()
        changeToolBar(menu = false, homeButton = true, "")
    }

    fun initRecyclerParticipants(){
        finishParticipantRecyclerAdapter = FinishParticipantRecyclerAdapter(requireContext())
        binding.participantsRecycler.adapter = finishParticipantRecyclerAdapter
    }

}