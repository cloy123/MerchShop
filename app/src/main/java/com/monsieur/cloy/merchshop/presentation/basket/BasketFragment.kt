package com.monsieur.cloy.merchshop.presentation.basket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.monsieur.cloy.merchshop.databinding.FragmentBasketBinding
import com.monsieur.cloy.merchshop.presentation.viewModels.MainViewModel
import com.monsieur.cloy.merchshop.utilits.backButton
import com.monsieur.cloy.merchshop.utilits.calculatePrice
import com.monsieur.cloy.merchshop.utilits.changeToolBar
import com.monsieur.cloy.merchshop.utilits.showToast
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class BasketFragment : Fragment() {

    private var _binding: FragmentBasketBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by sharedViewModel()

    private var orderAvailability = false

    private lateinit var recyclerAdapter: BasketRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBasketBinding.inflate(inflater, container, false)
        initFunc()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        changeToolBar(menu = false, homeButton = false, "Корзина")
    }

    fun initFunc(){
        binding.toOrderButton.isEnabled = false
        viewModel.createOrderResult.observe(requireActivity(), Observer {
            if(it != null){
                if(it.isSuccessful){
                    if(!it.isSuccessful){
                        showToast("Ошибка")
                    }else if(it.isCreated){
                        showToast("Заказ создан")
                        backButton()
                    }else if(!it.isCreated){
                        showToast(it.errorMessage)
                    }
                }
            }
        })
        binding.toOrderButton.setOnClickListener {
            if(orderAvailability){
                viewModel.createOrder()
            }
        }
        initRecyclerAdapter()
    }

    fun initRecyclerAdapter(){
        recyclerAdapter = BasketRecyclerAdapter(requireContext())
        binding.basketRecycler.adapter = recyclerAdapter
        viewModel.basketItems.observe(requireActivity(), Observer {
            recyclerAdapter.setItems(it)
            var sum = 0
            orderAvailability = true
            for(b in it){
                orderAvailability = b.product.freeQuantity > 0
                sum += calculatePrice(b.product.price, b.product.discount)
            }
            binding.toOrderButton.isEnabled = orderAvailability
            binding.tvSum.text = sum.toString()
        })
        recyclerAdapter.setDeleteFromBasketListener {
            viewModel.deleteFromBasket(it)
        }
    }
}