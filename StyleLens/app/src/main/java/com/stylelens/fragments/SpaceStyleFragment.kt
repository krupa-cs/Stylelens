package com.stylelens.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.stylelens.R
import com.stylelens.activities.StyleInputActivity
import com.stylelens.databinding.FragmentSpaceStyleBinding

class SpaceStyleFragment : Fragment() {
    
    private var _binding: FragmentSpaceStyleBinding? = null
    private val binding get() = _binding!!
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSpaceStyleBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupUI()
    }
    
    private fun setupUI() {
        binding.tvTitle.text = "Interior Styling"
        binding.tvDescription.text = "Transform your spaces with AI-powered interior design recommendations"
        
        binding.btnStartStyling.setOnClickListener {
            val intent = Intent(requireContext(), StyleInputActivity::class.java)
            intent.putExtra("styleType", "space")
            startActivity(intent)
        }
        
        // Display features
        binding.tvFeature1.text = "✓ Room type analysis"
        binding.tvFeature2.text = "✓ Color scheme suggestions"
        binding.tvFeature3.text = "✓ Furniture recommendations"
        binding.tvFeature4.text = "✓ Sustainable materials"
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
