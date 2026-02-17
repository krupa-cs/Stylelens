package com.stylelens.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stylelens.databinding.ItemStyleBinding
import com.stylelens.models.StyleResult

class StyleAdapter(
    private val styles: List<StyleResult>,
    private val onItemClick: (StyleResult, Int) -> Unit
) : RecyclerView.Adapter<StyleAdapter.StyleViewHolder>() {
    
    inner class StyleViewHolder(private val binding: ItemStyleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        
        fun bind(style: StyleResult, position: Int) {
            binding.apply {
                tvStyleType.text = style.styleType.capitalize()
                tvRecommendation.text = style.recommendationText
                tvSustainabilityScore.text = "Sustainability: ${style.sustainabilityScore}/100"
                tvDate.text = style.createdAt
                
                progressSustainability.progress = style.sustainabilityScore
                
                root.setOnClickListener {
                    onItemClick(style, position)
                }
                
                // Long press for context menu
                root.setOnLongClickListener {
                    onItemClick(style, position)
                    true
                }
            }
        }
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StyleViewHolder {
        val binding = ItemStyleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return StyleViewHolder(binding)
    }
    
    override fun onBindViewHolder(holder: StyleViewHolder, position: Int) {
        holder.bind(styles[position], position)
    }
    
    override fun getItemCount() = styles.size
}
