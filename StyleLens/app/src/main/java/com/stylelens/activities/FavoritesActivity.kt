package com.stylelens.activities

import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.stylelens.R
import com.stylelens.adapters.StyleAdapter
import com.stylelens.databinding.ActivityFavoritesBinding
import com.stylelens.models.StyleResult

class FavoritesActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityFavoritesBinding
    private lateinit var styleAdapter: StyleAdapter
    private var favoriteStyles = mutableListOf<StyleResult>()
    private var selectedPosition = -1
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupToolbar()
        setupRecyclerView()
        loadFavorites()
    }
    
    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Favorites"
    }
    
    private fun setupRecyclerView() {
        styleAdapter = StyleAdapter(favoriteStyles) { style, position ->
            selectedPosition = position
            // Long press will show context menu
        }
        
        binding.recyclerViewFavorites.apply {
            layoutManager = LinearLayoutManager(this@FavoritesActivity)
            adapter = styleAdapter
        }
        
        // Register for context menu (long press)
        registerForContextMenu(binding.recyclerViewFavorites)
    }
    
    private fun loadFavorites() {
        // Sample favorites (in real app, load from database/API)
        favoriteStyles = mutableListOf(
            StyleResult(1, 1, 1, "people", "Casual outfit for pear body type", 85, "2024-02-01"),
            StyleResult(2, 1, 2, "space", "Modern minimalist living room", 78, "2024-02-02"),
            StyleResult(3, 1, 3, "character", "Elegant character design", 92, "2024-02-03")
        )
        
        styleAdapter.notifyDataSetChanged()
    }
    
    // Context Menu (Long Press Menu)
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.menu_context, menu)
        menu?.setHeaderTitle("Options")
    }
    
    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.context_view -> {
                if (selectedPosition >= 0) {
                    Toast.makeText(this, "Viewing style", Toast.LENGTH_SHORT).show()
                }
                true
            }
            R.id.context_share -> {
                if (selectedPosition >= 0) {
                    Toast.makeText(this, "Sharing style", Toast.LENGTH_SHORT).show()
                }
                true
            }
            R.id.context_delete -> {
                if (selectedPosition >= 0) {
                    favoriteStyles.removeAt(selectedPosition)
                    styleAdapter.notifyItemRemoved(selectedPosition)
                    Toast.makeText(this, "Removed from favorites", Toast.LENGTH_SHORT).show()
                }
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }
    
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
