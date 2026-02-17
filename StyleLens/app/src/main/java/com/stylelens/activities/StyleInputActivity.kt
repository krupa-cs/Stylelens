package com.stylelens.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.stylelens.R
import com.stylelens.databinding.ActivityStyleInputBinding
import com.stylelens.utils.Constants

class StyleInputActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityStyleInputBinding
    private var selectedBodyType = ""
    private var selectedSkinTone = ""
    private var selectedOccasion = ""
    private var selectedGender = "Female"
    private var budgetValue = 100
    private var accessoriesChecked = false
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStyleInputBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupToolbar()
        setupSpinners()
        setupRadioButtons()
        setupCheckBoxes()
        setupSeekBar()
        setupAutoComplete()
        setupListeners()
    }
    
    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Style Input"
    }
    
    private fun setupSpinners() {
        // Body Type Spinner
        val bodyTypeAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            Constants.BODY_TYPES
        )
        bodyTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerBodyType.adapter = bodyTypeAdapter
        binding.spinnerBodyType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedBodyType = Constants.BODY_TYPES[position]
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        
        // Skin Tone Spinner
        val skinToneAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            Constants.SKIN_TONES
        )
        skinToneAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerSkinTone.adapter = skinToneAdapter
        binding.spinnerSkinTone.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedSkinTone = Constants.SKIN_TONES[position]
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        
        // Occasion Spinner
        val occasionAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            Constants.OCCASIONS
        )
        occasionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerOccasion.adapter = occasionAdapter
        binding.spinnerOccasion.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedOccasion = Constants.OCCASIONS[position]
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }
    
    private fun setupRadioButtons() {
        // Gender RadioButton
        binding.radioGroupGender.setOnCheckedChangeListener { _, checkedId ->
            selectedGender = when (checkedId) {
                R.id.radioMale -> "Male"
                R.id.radioFemale -> "Female"
                R.id.radioOther -> "Other"
                else -> "Female"
            }
        }
    }
    
    private fun setupCheckBoxes() {
        // Accessories CheckBox
        binding.checkBoxAccessories.setOnCheckedChangeListener { _, isChecked ->
            accessoriesChecked = isChecked
        }
    }
    
    private fun setupSeekBar() {
        // Budget SeekBar
        binding.seekBarBudget.max = 500
        binding.seekBarBudget.progress = 100
        binding.tvBudgetValue.text = "$100"
        
        binding.seekBarBudget.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                budgetValue = progress
                binding.tvBudgetValue.text = "$$progress"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }
    
    private fun setupAutoComplete() {
        // Color Preference AutoCompleteTextView
        val colors = arrayOf(
            "Red", "Blue", "Green", "Yellow", "Black", "White",
            "Pink", "Purple", "Orange", "Brown", "Gray", "Navy"
        )
        val colorAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, colors)
        binding.autoCompleteColor.setAdapter(colorAdapter)
        binding.autoCompleteColor.threshold = 1
    }
    
    private fun setupListeners() {
        binding.btnGenerateStyle.setOnClickListener {
            if (validateInputs()) {
                generateStyle()
            }
        }
    }
    
    private fun validateInputs(): Boolean {
        if (selectedBodyType.isEmpty() || selectedSkinTone.isEmpty() || selectedOccasion.isEmpty()) {
            Toast.makeText(this, "Please fill all required fields", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
    
    private fun generateStyle() {
        val sustainabilityMode = binding.switchSustainability.isChecked
        val colorPreference = binding.autoCompleteColor.text.toString()
        
        // Navigate to StyleResultActivity with data
        val intent = Intent(this, StyleResultActivity::class.java).apply {
            putExtra("bodyType", selectedBodyType)
            putExtra("skinTone", selectedSkinTone)
            putExtra("occasion", selectedOccasion)
            putExtra("gender", selectedGender)
            putExtra("budget", budgetValue)
            putExtra("accessories", accessoriesChecked)
            putExtra("sustainability", sustainabilityMode)
            putExtra("colorPreference", colorPreference)
        }
        startActivity(intent)
    }
    
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
