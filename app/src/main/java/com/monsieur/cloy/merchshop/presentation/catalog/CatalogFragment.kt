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
    private lateinit var typesAdapter: ProductTypesRecyclerAdapter
    private lateinit var colorsAdapter: ProductColorsRecyclerAdapter

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
        initTypesRecyclerAdapter()
        initColorsRecyclerAdapter()
        closeFilters.setOnClickListener {
            binding.drawerLayout.closeDrawer(Gravity.END)
        }
        binding.openFilters.setOnClickListener {
            binding.drawerLayout.openDrawer(Gravity.END)
        }
        applyFilters.setOnClickListener {
            binding.drawerLayout.closeDrawer(Gravity.END)
            applyFilters()
        }
        clearPrice.setOnClickListener {
            fromPrice.setText("")
            untilPrice.setText("")
        }

        clearColors.setOnClickListener {
            colorsAdapter?.clearChecked()
        }
        clearTypes.setOnClickListener {
            typesAdapter?.clearChecked()
        }
        binding.sortType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val currentFiltersSettings = viewModel.filtersSettings
                currentFiltersSettings.sortBy = when(binding.sortType.selectedItemId.toInt()){
                    0 -> Sort.ByPriceDescending
                    1 -> Sort.ByPriceAscending
                    2 -> Sort.ByName
                    else -> Sort.ByName
                }
                viewModel.setFilters(currentFiltersSettings)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

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
        val listTypes = typesAdapter.getCheckedTypes()
        val listColor = colorsAdapter.getCheckedColors()
        val listTypeNames = ArrayList<String>()
        val listColorNames = ArrayList<String>()
        if(listTypes.isEmpty()){
            typesAdapter.getCheckedTypes().forEach { listTypeNames.add(it.name) }
        }else{
            listTypes.forEach { listTypeNames.add(it.name) }
        }
        if(listColor.isEmpty()){
            colorsAdapter.getCheckedColors().forEach { listColorNames.add(it.colorName) }
        }else{
            listColor.forEach { listColorNames.add(it.colorName) }
        }

        val from = if(fromPrice.text.isEmpty()){
            0
        }else{
            fromPrice.text.toString().toInt()
        }

        val to = if(untilPrice.text.isEmpty()){
            99999999
        }else{
            untilPrice.text.toString().toInt()
        }
        val sortBy = when(binding.sortType.selectedItemId.toInt()){
            0 -> Sort.ByName
            1 -> Sort.ByPriceDescending
            2 -> Sort.ByPriceAscending
            else -> Sort.ByName
        }
        viewModel.setFilters(FiltersSettings(sortBy, from, to, listColorNames, listTypeNames))
        viewModel.setFilters(FiltersSettings(Sort.ByName, 0, 99999, ArrayList(), ArrayList()))
    }

    private fun initTypesRecyclerAdapter(){
        typesAdapter = ProductTypesRecyclerAdapter()
        typesRecycler.adapter = typesAdapter
        viewModel.types.observe(requireActivity(), Observer {
            typesAdapter.setTypes(it.map { ProductType(it, false) } as ArrayList)
        })
    }

    private fun initColorsRecyclerAdapter(){
        colorsAdapter = ProductColorsRecyclerAdapter()
        colorsRecycler.adapter = colorsAdapter
        viewModel.colors.observe(requireActivity(), Observer {
            colorsAdapter.setColors(it as ArrayList)
        })
    }

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