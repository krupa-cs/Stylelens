package com.stylelens.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.stylelens.R
import com.stylelens.activities.ProductCatalogActivity
import com.stylelens.databinding.FragmentProductBinding

class ProductFragment : Fragment() {
    
    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupUI()
    }
    
    private fun setupUI() {
        binding.tvTitle.text = "Product Styling"
        binding.tvDescription.text = "Discover sustainable products styled for you"
        
        binding.btnBrowseProducts.setOnClickListener {
            startActivity(Intent(requireContext(), ProductCatalogActivity::class.java))
        }
        
        // Display features
        binding.tvFeature1.text = "✓ Sustainable product catalog"
        binding.tvFeature2.text = "✓ Eco-rating system"
        binding.tvFeature3.text = "✓ Budget-friendly options"
        binding.tvFeature4.text = "✓ Personalized recommendations"
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
