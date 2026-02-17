package com.stylelens.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stylelens.databinding.ItemProductBinding
import com.stylelens.models.Product

class ProductAdapter(
    private val products: List<Product>,
    private val onItemClick: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    
    inner class ProductViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        
        fun bind(product: Product) {
            binding.apply {
                tvProductName.text = product.name
                tvProductCategory.text = product.category
                tvProductPrice.text = "$${product.price}"
                tvSustainabilityRating.text = "Eco: ${product.sustainabilityRating}/100"
                
                // Set color indicator
                try {
                    viewColorIndicator.setBackgroundColor(Color.parseColor(product.color))
                } catch (e: Exception) {
                    viewColorIndicator.setBackgroundColor(Color.GRAY)
                }
                
                // Set sustainability color
                val sustainabilityColor = when {
                    product.sustainabilityRating >= 80 -> Color.parseColor("#4CAF50") // Green
                    product.sustainabilityRating >= 60 -> Color.parseColor("#FF9800") // Orange
                    else -> Color.parseColor("#F44336") // Red
                }
                tvSustainabilityRating.setTextColor(sustainabilityColor)
                
                root.setOnClickListener {
                    onItemClick(product)
                }
            }
        }
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductViewHolder(binding)
    }
    
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position])
    }
    
    override fun getItemCount() = products.size
}
