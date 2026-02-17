package com.stylelens.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.stylelens.R
import com.stylelens.activities.StyleInputActivity
import com.stylelens.databinding.FragmentCharacterStyleBinding

class CharacterStyleFragment : Fragment() {
    
    private var _binding: FragmentCharacterStyleBinding? = null
    private val binding get() = _binding!!
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterStyleBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupUI()
    }
    
    private fun setupUI() {
        binding.tvTitle.text = "Character Design"
        binding.tvDescription.text = "Create stunning character designs with AI-powered styling"
        
        binding.btnStartStyling.setOnClickListener {
            val intent = Intent(requireContext(), StyleInputActivity::class.java)
            intent.putExtra("styleType", "character")
            startActivity(intent)
        }
        
        // Display features
        binding.tvFeature1.text = "✓ Character personality analysis"
        binding.tvFeature2.text = "✓ Costume design"
        binding.tvFeature3.text = "✓ Color palette matching"
        binding.tvFeature4.text = "✓ Style consistency"
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
