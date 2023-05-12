package com.monsieur.cloy.merchshop.presentation.events

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import com.monsieur.cloy.domain.models.Event
import com.monsieur.cloy.domain.models.EventRole
import com.monsieur.cloy.merchshop.databinding.FragmentEventBinding
import com.monsieur.cloy.merchshop.presentation.viewModels.MainViewModel
import com.monsieur.cloy.merchshop.utilits.backButton
import com.monsieur.cloy.merchshop.utilits.changeToolBar
import com.monsieur.cloy.merchshop.utilits.replaceFragment
import com.monsieur.cloy.merchshop.utilits.showToast
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class EventFragment(val event: Event) : Fragment() {

    private var _binding: FragmentEventBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by sharedViewModel()


    private lateinit var rolesRecyclerAdapter: EventRolesRecyclerAdapter

    private lateinit var responsiblesRecyclerAdapter: EventResponsiblesRecyclerAdapter

    private lateinit var participantsRecyclerAdapter: EventParticipantRecyclerAdapter

    var roles = ArrayList<EventRole>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEventBinding.inflate(inflater, container, false)
        initFunc()
        return binding.root
    }

    private fun initFunc() {
        if(event.isCompleted){
            binding.btFinish.visibility = View.GONE
            binding.linearSignup.visibility = View.GONE
        }else{
            binding.btFinish.visibility = View.VISIBLE
            binding.linearSignup.visibility = View.VISIBLE
        }

        initRecyclerParticipants()
        initRecyclerResponsibles()
        initRecyclerRoles()

        viewModel.eventRoles.observe(requireActivity(), Observer {
            roles = it.filter { it.eventId == event.id } as ArrayList
            rolesRecyclerAdapter.setItems(roles)
            val rolesStr = ArrayList<String>()
            if(viewModel.userData != null){
                roles = roles.filter { it.userTypeId == viewModel.userData!!.userTypeId} as ArrayList
            }else{
                binding.linearSignup.visibility = View.GONE
            }
            for(role in roles){
                rolesStr.add(role.name)
            }
            val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
                requireContext(),
                android.R.layout.simple_spinner_item, rolesStr
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerRoles.adapter = adapter
        })
        viewModel.eventResponsibles.observe(requireActivity(), Observer {
            val responsibles = it.filter { it.eventId == event.id } as ArrayList
            if(viewModel.userData != null){
                if(responsibles.find { it.userId == viewModel.userData!!.id } != null){
                    binding.btFinish.visibility = View.VISIBLE
                }else{
                    binding.btFinish.visibility = View.GONE
                }
            }else{
                binding.btFinish.visibility = View.GONE
            }
            responsiblesRecyclerAdapter.setItems(responsibles)
        })
        viewModel.eventParticipants.observe(requireActivity(), Observer {
            val participants = it.filter { it.eventId == event.id } as ArrayList
            if(viewModel.userData != null){
                if(participants.find { it.userId == viewModel.userData!!.id } != null){
                    binding.btSignup.visibility = View.GONE
                }else{
                    binding.btFinish.visibility = View.VISIBLE
                }
            }else{
                binding.btSignup.visibility = View.GONE
            }
            participantsRecyclerAdapter.setItems(participants)
        })

        viewModel.signupResult.observe(requireActivity(), Observer {
            if(it != null){
                if(!it.isSuccessful){
                    showToast("Ошибка")
                }else if(it.isSigned){
                    showToast("Вы записаны на мероприятие")
                    backButton()
                }else if(!it.isSigned){
                    showToast(it.errorMessage)
                }
            }
        })

        binding.btFinish.setOnClickListener {
            replaceFragment(FinishEventFragment(event))
        }

        binding.btSignup.setOnClickListener {
            viewModel.signupEvent(event, roles[binding.spinnerRoles.selectedItemId.toInt()])
        }
    }

    override fun onStart() {
        super.onStart()
        changeToolBar(menu = false, homeButton = true, event.name)
    }

    fun initRecyclerRoles(){
        rolesRecyclerAdapter = EventRolesRecyclerAdapter(requireContext())
        binding.eventRolesRecycler.adapter = rolesRecyclerAdapter
    }

    fun initRecyclerResponsibles(){
        responsiblesRecyclerAdapter = EventResponsiblesRecyclerAdapter(requireContext())
        binding.eventResponsiblesRecycler.adapter = responsiblesRecyclerAdapter
    }

    fun initRecyclerParticipants(){
        participantsRecyclerAdapter = EventParticipantRecyclerAdapter(requireContext())
        binding.eventParticipantsRecycler.adapter = participantsRecyclerAdapter
    }

}