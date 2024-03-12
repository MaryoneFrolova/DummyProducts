package ru.maryone.dummyproducts.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.maryone.dummyproducts.App
import ru.maryone.dummyproducts.R
import ru.maryone.dummyproducts.databinding.ActivityMainBinding
import ru.maryone.dummyproducts.domain.model.ProductItem
import ru.maryone.dummyproducts.presentation.adapter.ProductDiffUtilCallback
import ru.maryone.dummyproducts.presentation.adapter.ProductListAdapter
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var vmFactory: MainViewModelFactory
    lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private val productListAdapter = ProductListAdapter(emptyList())
    private var elements = emptyList<ProductItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (application as App).appComponent.inject(this)
        viewModel = ViewModelProvider(this,vmFactory)[MainViewModel::class.java]
        initRecycler()

        initObserves()
    }

    private fun initRecycler() {
        binding.productsRecyclerView.adapter = productListAdapter

        binding.productsRecyclerView.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        productListAdapter.onReachEndListener = { viewModel.getProducts() }

        val dividerItemDecoration = DividerItemDecoration(this, RecyclerView.VERTICAL)
        dividerItemDecoration.setDrawable(
            AppCompatResources.getDrawable(
                this,
                R.drawable.drawable_divider
            )!!
        )
        binding.productsRecyclerView.addItemDecoration(dividerItemDecoration)
    }

    private fun initObserves() {
        viewModel.getProductList().observe(this) { newElements ->
            updateList(newElements)
        }

        viewModel.getIsLoading().observe(this) { isLoading ->
            if (isLoading) {
                binding.progressBarLoading.visibility = View.VISIBLE
            } else {
                binding.progressBarLoading.visibility = View.GONE
            }
        }
    }

    private fun updateList(newElements: List<ProductItem>) {
        val result = DiffUtil.calculateDiff(
            ProductDiffUtilCallback(
                oldElements = elements,
                newElements = newElements
            )
        )
        productListAdapter.let { adapter ->
            adapter.elements = newElements
            result.dispatchUpdatesTo(adapter)
        }
        elements = newElements
    }
}