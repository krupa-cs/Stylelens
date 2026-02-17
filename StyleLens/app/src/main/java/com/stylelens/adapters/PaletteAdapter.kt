package com.stylelens.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stylelens.databinding.ItemPaletteBinding
import com.stylelens.models.ColorPalette

class PaletteAdapter(
    private val palettes: List<ColorPalette>,
    private val onItemClick: (ColorPalette) -> Unit
) : RecyclerView.Adapter<PaletteAdapter.PaletteViewHolder>() {
    
    inner class PaletteViewHolder(private val binding: ItemPaletteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        
        fun bind(palette: ColorPalette) {
            binding.apply {
                tvPaletteName.text = palette.paletteName
                tvUsageType.text = palette.usageType
                
                // Set color views
                try {
                    viewPrimaryColor.setBackgroundColor(Color.parseColor(palette.primaryColor))
                    viewSecondaryColor.setBackgroundColor(Color.parseColor(palette.secondaryColor))
                    viewAccentColor.setBackgroundColor(Color.parseColor(palette.accentColor))
                } catch (e: Exception) {
                    // Default colors if parsing fails
                    viewPrimaryColor.setBackgroundColor(Color.GRAY)
                    viewSecondaryColor.setBackgroundColor(Color.LTGRAY)
                    viewAccentColor.setBackgroundColor(Color.DKGRAY)
                }
                
                root.setOnClickListener {
                    onItemClick(palette)
                }
            }
        }
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaletteViewHolder {
        val binding = ItemPaletteBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PaletteViewHolder(binding)
    }
    
    override fun onBindViewHolder(holder: PaletteViewHolder, position: Int) {
        holder.bind(palettes[position])
    }
    
    override fun getItemCount() = palettes.size
}
