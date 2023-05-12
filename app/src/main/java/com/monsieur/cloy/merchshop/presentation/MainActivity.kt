package com.monsieur.cloy.merchshop.presentation

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import com.monsieur.cloy.merchshop.R
import com.monsieur.cloy.merchshop.databinding.ActivityMainBinding
import com.monsieur.cloy.merchshop.presentation.basket.BasketFragment
import com.monsieur.cloy.merchshop.presentation.events.EventsFragment
import com.monsieur.cloy.merchshop.presentation.catalog.CatalogFragment
import com.monsieur.cloy.merchshop.presentation.profile.ProfileFragment
import com.monsieur.cloy.merchshop.presentation.viewModels.MainViewModel
import com.monsieur.cloy.merchshop.utilits.APP_ACTIVITY
import com.monsieur.cloy.merchshop.utilits.backButton
import com.monsieur.cloy.merchshop.utilits.replaceFragment
import com.monsieur.cloy.merchshop.utilits.toolbarMenu
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        APP_ACTIVITY = this
        replaceFragment(LoginFragment(), false)
        setSupportActionBar(binding.toolbar)

        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.catalog ->
                    replaceFragment(CatalogFragment(), false)
                R.id.events ->
                    replaceFragment(EventsFragment(), false)
                R.id.basket ->
                    replaceFragment(BasketFragment(), false)
                R.id.profile ->
                    replaceFragment(ProfileFragment(), false)
            }
            true
        }

        viewModel.user.observe(this, Observer {
            if(it != null){
                binding.bottomNavigation.visibility = View.VISIBLE
            }else{
                binding.bottomNavigation.visibility = View.GONE
            }
        })

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val sharedPreferences = getSharedPreferences("theme", MODE_PRIVATE)
        if (menu != null) {
            if(sharedPreferences.getBoolean("dark", false)){
                val item = menu.getItem(0)
                item.icon = getDrawable(R.drawable.ic_round_light_mode_24)
            }
            toolbarMenu = menu
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                backButton()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}