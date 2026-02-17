package com.stylelens.activities

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.stylelens.R
import com.stylelens.adapters.ProductAdapter
import com.stylelens.databinding.ActivityProductCatalogBinding
import com.stylelens.models.Product

class ProductCatalogActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityProductCatalogBinding
    private lateinit var productAdapter: ProductAdapter
    private var allProducts = mutableListOf<Product>()
    private var filteredProducts = mutableListOf<Product>()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductCatalogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupToolbar()
        setupRecyclerView()
        loadProducts()
        setupListeners()
    }
    
    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Product Catalog"
    }
    
    private fun setupRecyclerView() {
        productAdapter = ProductAdapter(filteredProducts) { product ->
            Toast.makeText(this, "Selected: ${product.name}", Toast.LENGTH_SHORT).show()
        }
        
        binding.recyclerViewProducts.apply {
            layoutManager = GridLayoutManager(this@ProductCatalogActivity, 2)
            adapter = productAdapter
        }
    }
    
    private fun loadProducts() {
        // Sample products (in real app, load from API)
        allProducts = mutableListOf(
            Product(1, "Clothing", "Sustainable Cotton Shirt", "#FFFFFF", 29.99, 85, ""),
            Product(2, "Clothing", "Eco-Friendly Jeans", "#0000FF", 49.99, 90, ""),
            Product(3, "Accessories", "Recycled Leather Bag", "#8B4513", 79.99, 80, ""),
            Product(4, "Footwear", "Organic Canvas Shoes", "#000000", 59.99, 75, ""),
            Product(5, "Clothing", "Bamboo Dress", "#FF69B4", 39.99, 95, ""),
            Product(6, "Accessories", "Wooden Sunglasses", "#654321", 24.99, 70, "")
        )
        
        filteredProducts.clear()
        filteredProducts.addAll(allProducts)
        productAdapter.notifyDataSetChanged()
    }
    
    private fun setupListeners() {
        // Filter Button - Shows Popup Menu
        binding.btnFilter.setOnClickListener { view ->
            showFilterPopupMenu(view)
        }
    }
    
    private fun showFilterPopupMenu(view: View) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.menuInflater.inflate(R.menu.menu_popup, popupMenu.menu)
        
        popupMenu.setOnMenuItemClickListener { item: MenuItem ->
            when (item.itemId) {
                R.id.filter_all -> {
                    filterProducts("All")
                    true
                }
                R.id.filter_clothing -> {
                    filterProducts("Clothing")
                    true
                }
                R.id.filter_accessories -> {
                    filterProducts("Accessories")
                    true
                }
                R.id.filter_footwear -> {
                    filterProducts("Footwear")
                    true
                }
                R.id.filter_high_sustainability -> {
                    filterBySustainability(80)
                    true
                }
                else -> false
            }
        }
        
        popupMenu.show()
    }
    
    private fun filterProducts(category: String) {
        filteredProducts.clear()
        if (category == "All") {
            filteredProducts.addAll(allProducts)
        } else {
            filteredProducts.addAll(allProducts.filter { it.category == category })
        }
        productAdapter.notifyDataSetChanged()
        Toast.makeText(this, "Filtered by: $category", Toast.LENGTH_SHORT).show()
    }
    
    private fun filterBySustainability(minScore: Int) {
        filteredProducts.clear()
        filteredProducts.addAll(allProducts.filter { it.sustainabilityRating >= minScore })
        productAdapter.notifyDataSetChanged()
        Toast.makeText(this, "Showing products with sustainability ≥ $minScore", Toast.LENGTH_SHORT).show()
    }
    
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
