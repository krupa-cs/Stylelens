package com.stylelens.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.stylelens.R
import com.stylelens.activities.ProductCatalogActivity
import com.stylelens.activities.StyleInputActivity
import com.stylelens.adapters.PaletteAdapter
import com.stylelens.databinding.FragmentDashboardBinding
import com.stylelens.models.ColorPalette
import com.stylelens.utils.SharedPrefsManager

class DashboardFragment : Fragment() {
    
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private lateinit var prefs: SharedPrefsManager
    private lateinit var paletteAdapter: PaletteAdapter
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        prefs = SharedPrefsManager.getInstance(requireContext())
        
        setupWelcomeCard()
        setupQuickActions()
        setupTrendingPalettes()
        setupAIInsights()
    }
    
    private fun setupWelcomeCard() {
        val userName = prefs.getUserName() ?: "User"
        binding.tvWelcome.text = "Welcome, $userName!"
        binding.tvSDGMessage.text = "🌱 SDG 12: Responsible Consumption & Production"
    }
    
    private fun setupQuickActions() {
        // Quick Action Cards
        binding.cardStyleYourself.setOnClickListener {
            val intent = Intent(requireContext(), StyleInputActivity::class.java)
            intent.putExtra("styleType", "people")
            startActivity(intent)
        }
        
        binding.cardStyleRoom.setOnClickListener {
            val intent = Intent(requireContext(), StyleInputActivity::class.java)
            intent.putExtra("styleType", "space")
            startActivity(intent)
        }
        
        binding.cardStyleCharacter.setOnClickListener {
            val intent = Intent(requireContext(), StyleInputActivity::class.java)
            intent.putExtra("styleType", "character")
            startActivity(intent)
        }
        
        binding.cardStyleProduct.setOnClickListener {
            startActivity(Intent(requireContext(), ProductCatalogActivity::class.java))
        }
    }
    
    private fun setupTrendingPalettes() {
        val trendingPalettes = listOf(
            ColorPalette(1, "Earth Luxe", "#8B7355", "#D2B48C", "#F5DEB3", "Formal", ""),
            ColorPalette(2, "Neo Modern", "#2C3E50", "#ECF0F1", "#3498DB", "Business", ""),
            ColorPalette(3, "Vibrant Street", "#E74C3C", "#F39C12", "#9B59B6", "Casual", "")
        )
        
        paletteAdapter = PaletteAdapter(trendingPalettes) { palette ->
            // Handle palette selection
        }
        
        binding.recyclerViewPalettes.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = paletteAdapter
        }
    }
    
    private fun setupAIInsights() {
        // Display AI-generated insights
        val sustainabilityScore = 78 // In real app, fetch from backend
        binding.tvAIInsight1.text = "You reused 3 outfits this month 🌱"
        binding.tvAIInsight2.text = "Sustainability score: $sustainabilityScore%"
        binding.tvAIInsight3.text = "Saved $45 by optimizing your wardrobe!"
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
