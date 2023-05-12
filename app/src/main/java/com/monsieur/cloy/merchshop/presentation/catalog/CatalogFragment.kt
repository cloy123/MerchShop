package com.monsieur.cloy.merchshop.presentation.catalog

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.monsieur.cloy.merchshop.R
import com.monsieur.cloy.merchshop.databinding.FragmentCatalogBinding
import com.monsieur.cloy.merchshop.presentation.viewModels.MainViewModel
import com.monsieur.cloy.merchshop.utilits.changeToolBar
import com.monsieur.cloy.merchshop.utilits.replaceFragment
import com.monsieur.cloy.merchshop.utilits.showToast
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CatalogFragment : Fragment() {

    private var _binding: FragmentCatalogBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by sharedViewModel()

    private lateinit var recyclerAdapter: ProductRecyclerAdapter

    private lateinit var filters: View
    private lateinit var fromPrice: EditText
    private lateinit var untilPrice: EditText
    private lateinit var closeFilters: Button
    private lateinit var clearTypes: TextView
    private lateinit var clearColors: TextView
    private lateinit var clearPrice: TextView
    private lateinit var typesRecycler: RecyclerView
    private lateinit var colorsRecycler: RecyclerView
    private lateinit var applyFilters: Button
//    private lateinit var typesAdapter: FilterRecyclerAdapter
//    private lateinit var firmsAdapter: FilterRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCatalogBinding.inflate(inflater, container, false)
        initFunc()
        return binding.root
    }

    private fun initFunc() {
        filters = binding.navView.getHeaderView(0)
        fromPrice = filters.findViewById(R.id.from_price)
        untilPrice = filters.findViewById(R.id.until_price)
        closeFilters = filters.findViewById(R.id.close)
        clearTypes = filters.findViewById(R.id.clear_types)
        clearColors = filters.findViewById(R.id.clear_colors)
        clearPrice = filters.findViewById(R.id.clear_price)
        typesRecycler = filters.findViewById(R.id.types_recycler)
        colorsRecycler = filters.findViewById(R.id.colors_recycler)
        applyFilters = filters.findViewById(R.id.apply)
//        initTypesRecyclerAdapter()
//        initFirmsRecyclerAdapter()
        closeFilters.setOnClickListener {
            binding.drawerLayout.closeDrawer(Gravity.END)
        }
        binding.openFilters.setOnClickListener {
            binding.drawerLayout.openDrawer(Gravity.END)
        }
        applyFilters.setOnClickListener {
            binding.drawerLayout.closeDrawer(Gravity.END)
//            applyFilters()
        }
        clearPrice.setOnClickListener {
            fromPrice.setText("")
            untilPrice.setText("")
        }

//        clearFirms.setOnClickListener {
//            firmsAdapter?.clearChecked()
//        }
//        clearTypes.setOnClickListener {
//            typesAdapter?.clearChecked()
//        }
        binding.sortType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val currentFiltersSettings = viewModel.filtersSettings
                currentFiltersSettings.sortBy = when(binding.sortType.selectedItemId.toInt()){
                    0 -> Sort.ByName
                    1 -> Sort.ByPriceDescending
                    2 -> Sort.ByPriceAscending
                    else -> Sort.ByName
                }
                viewModel.setFilters(currentFiltersSettings)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        viewModel.updateProductDataResult.observe(requireActivity(), Observer {
            if(it != null && it.products == null){
                showToast("Ошибка при обновлении данных")
            }
        })

        viewModel.updateProductData()

        initRecyclerAdapter()

    }

    override fun onStart() {
        super.onStart()
        changeToolBar(menu = false, homeButton = false, "Каталог")
    }

    override fun onResume() {
        super.onResume()
        applyFilters()
    }

    @SuppressLint("SetTextI18n")
    private fun applyFilters(){
//        val listTypes = typesAdapter.getCheckedTypes()
//        val listFirms = firmsAdapter.getCheckedFirms()
//        val listTypesId = ArrayList<Int>()
//        val listFirmsId = ArrayList<Int>()
//        if(listTypes.isEmpty()){
//            typesAdapter.getCheckedTypes().forEach { listTypesId.add(it.id) }
//        }else{
//            listTypes.forEach { listTypesId.add(it.id) }
//        }
//        if(listFirms.isEmpty()){
//            firmsAdapter.getCheckedFirms().forEach { listFirmsId.add(it.id) }
//        }else{
//            listFirms.forEach { listFirmsId.add(it.id) }
//        }
//
//        val from = if(fromPrice.text.isEmpty()){
//            0
//        }else{
//            fromPrice.text.toString().toInt()
//        }
//
//        val to = if(untilPrice.text.isEmpty()){
//            99999999
//        }else{
//            untilPrice.text.toString().toInt()
//        }
//        val sortBy = when(binding.sortType.selectedItemId.toInt()){
//            0 -> Sort.ByPopularity
//            1 -> Sort.ByPriceDescending
//            2 -> Sort.ByPriceAscending
//            3 -> Sort.ByName
//            else -> Sort.ByPopularity
//        }
//        viewModel.setFilters(FiltersSettings(sortBy, from, to, listFirmsId, listTypesId))
        viewModel.setFilters(FiltersSettings(Sort.ByName, 0, 99999, ArrayList(), ArrayList()))
    }
//
//    private fun initTypesRecyclerAdapter(){
//        typesAdapter = FilterRecyclerAdapter()
//        typesRecycler.adapter = typesAdapter
//        viewModel.allProductTypes.observe(requireActivity(), Observer {
//            typesAdapter.setTypes(it as ArrayList)
//        })
//    }
//
//    private fun initFirmsRecyclerAdapter(){
//        firmsAdapter = FilterRecyclerAdapter()
//        firmsRecycler.adapter = firmsAdapter
//        viewModel.allFirms.observe(requireActivity(), Observer {
//            firmsAdapter.setFirms(it as ArrayList)
//        })
//    }

    private fun initRecyclerAdapter(){
        recyclerAdapter = ProductRecyclerAdapter(requireContext())
        binding.productRecycler.adapter = recyclerAdapter
        viewModel.filteredProducts.observe(requireActivity(), Observer {
            recyclerAdapter.setItems(it)
        })
        recyclerAdapter.setAddToBasketListener {
            viewModel.addToBasket(it)
        }
    }

}