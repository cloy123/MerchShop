package com.monsieur.cloy.merchshop.presentation.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.monsieur.cloy.domain.models.Order
import com.monsieur.cloy.merchshop.databinding.FragmentProfileBinding
import com.monsieur.cloy.merchshop.presentation.viewModels.MainViewModel
import com.monsieur.cloy.merchshop.utilits.changeToolBar
import com.monsieur.cloy.merchshop.utilits.showToast
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ProfileFragment : Fragment() {


    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by sharedViewModel()

    private lateinit var ordersRecycler: OrderRecyclerAdapter

    private lateinit var currencyTransactionRecycler: CurrencyTransactionRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        initFunc()
        return binding.root
    }


    fun initFunc(){
        viewModel.updateOrdersData()
        viewModel.updateUserData()
        viewModel.updateCurrencyTransactionsData()

        binding.logout.setOnClickListener {
            viewModel.logout()
        }

        initCurrencyTransactionRecycler()
        initOrdersRecycler()


        viewModel.updateEventsData()

        viewModel.user.observe(requireActivity(), Observer {
            if(it != null){
                binding.userBalance.text = it.pointBalance.toString()
                binding.userEmail.text = it.email
                binding.userName.text = it.firstName + " " + it.lastName + " " + it.className
            }
        })

        viewModel.cancelOrderResult.observe(requireActivity(), Observer {
            if(it != null){
                if(!it.isSuccessful){
                    showToast("Ошибка")
                }else if(it.isCanceled){
                    showToast("Заказ отменён")
                    viewModel.updateOrdersData()
                    viewModel.updateUserData()
                    viewModel.updateCurrencyTransactionsData()
                }else if(!it.isCanceled){
                    showToast(it.errorMessage)
                }
            }
        })

        ordersRecycler.setOnCancelOrderListener {
            viewModel.cancelOrder(it.id)
        }
    }

    override fun onStart() {
        super.onStart()
        changeToolBar(menu = false, homeButton = false, "Профиль")
    }


    fun initCurrencyTransactionRecycler(){
        currencyTransactionRecycler = CurrencyTransactionRecyclerAdapter(requireContext())
        binding.currencyTransactionsRecycler.adapter = currencyTransactionRecycler
        viewModel.currencyTransactions.observe(requireActivity(), Observer {
            currencyTransactionRecycler.setItems(it)
        })
    }

    fun initOrdersRecycler(){
        ordersRecycler = OrderRecyclerAdapter(requireContext())
        binding.ordersRecycler.adapter = ordersRecycler
        viewModel.orderItems.observe(requireActivity(), Observer {
            val orders = ArrayList<Order>()
            for (i in it){
                if(!orders.contains(i.order)){
                    orders.add(i.order)
                }
            }
            ordersRecycler.setItems(orders, it)
        })
    }
}