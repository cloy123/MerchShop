package com.monsieur.cloy.merchshop.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.monsieur.cloy.merchshop.databinding.FragmentLoginBinding
import com.monsieur.cloy.merchshop.presentation.catalog.CatalogFragment
import com.monsieur.cloy.merchshop.presentation.viewModels.MainViewModel
import com.monsieur.cloy.merchshop.utilits.changeToolBar
import com.monsieur.cloy.merchshop.utilits.replaceFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class LoginFragment : Fragment() {

    private var _binding : FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        initFunk()
        return binding.root
    }

    private fun login(){
        val login = binding.login.text?.trim().toString()
        if(login.isEmpty()){
            binding.loginTextInput.error = "Введите логин"
            return
        }else{
            binding.loginTextInput.error = ""
        }
        val password = binding.password.text?.trim().toString()
        if(password.isEmpty()){
            binding.passwordTextInput.error = "Введите пароль"
            return
        }else{
            binding.passwordTextInput.error = ""
        }
        viewModel.login(login, password)
    }

    override fun onStart() {
        super.onStart()
        changeToolBar(menu = false, homeButton = false, "Войти")
        viewModel.user.observe(viewLifecycleOwner, Observer {
            if(it != null){
                replaceFragment(CatalogFragment(), false)
            }
        })
        viewModel.loginResult.observe(viewLifecycleOwner, Observer{
            if(it != null){
                if(it.user != null){
                    replaceFragment(CatalogFragment(), false)
                }else{
                    if(!it.isSuccessful){
                        if(binding.login.text?.isNotEmpty() == true){
                            binding.tvInfo.text = "ошибка"
                            binding.tvInfo.visibility = View.VISIBLE
                        }else{
                            binding.tvInfo.text = ""
                            binding.tvInfo.visibility = View.GONE
                        }
                    }else{
                        if(binding.login.text?.isNotEmpty() == true){
                            binding.tvInfo.text = "такой пользователь не найден или введён неверный пароль"
                            binding.tvInfo.visibility = View.VISIBLE
                        }else{
                            binding.tvInfo.text = ""
                            binding.tvInfo.visibility = View.GONE
                        }
                    }
                }
            }

        })
    }

    private fun initFunk(){
        binding.buttonLogin.setOnClickListener {
            login()
        }
    }
}