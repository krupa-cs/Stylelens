package com.stylelens.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.stylelens.R
import com.stylelens.activities.StyleInputActivity
import com.stylelens.databinding.FragmentPeopleStyleBinding

class PeopleStyleFragment : Fragment() {
    
    private var _binding: FragmentPeopleStyleBinding? = null
    private val binding get() = _binding!!
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPeopleStyleBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupUI()
    }
    
    private fun setupUI() {
        binding.tvTitle.text = "Personal Styling"
        binding.tvDescription.text = "Get AI-powered outfit recommendations based on your body type, skin tone, and occasion"
        
        binding.btnStartStyling.setOnClickListener {
            val intent = Intent(requireContext(), StyleInputActivity::class.java)
            intent.putExtra("styleType", "people")
            startActivity(intent)
        }
        
        // Display features
        binding.tvFeature1.text = "✓ Body type analysis"
        binding.tvFeature2.text = "✓ Color harmony matching"
        binding.tvFeature3.text = "✓ Occasion-based recommendations"
        binding.tvFeature4.text = "✓ Sustainable fashion choices"
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
