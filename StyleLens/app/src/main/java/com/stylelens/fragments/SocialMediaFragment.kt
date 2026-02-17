package com.stylelens.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.stylelens.R
import com.stylelens.activities.StyleInputActivity
import com.stylelens.databinding.FragmentSocialMediaBinding

class SocialMediaFragment : Fragment() {
    
    private var _binding: FragmentSocialMediaBinding? = null
    private val binding get() = _binding!!
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSocialMediaBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupUI()
    }
    
    private fun setupUI() {
        binding.tvTitle.text = "Social Media Aesthetics"
        binding.tvDescription.text = "Create cohesive social media aesthetics with AI"
        
        binding.btnStartStyling.setOnClickListener {
            val intent = Intent(requireContext(), StyleInputActivity::class.java)
            intent.putExtra("styleType", "social_media")
            startActivity(intent)
        }
        
        // Display features
        binding.tvFeature1.text = "✓ Feed aesthetic planning"
        binding.tvFeature2.text = "✓ Color theme consistency"
        binding.tvFeature3.text = "✓ Brand identity"
        binding.tvFeature4.text = "✓ Engagement optimization"
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
