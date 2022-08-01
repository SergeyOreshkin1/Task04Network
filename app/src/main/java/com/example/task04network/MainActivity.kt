package com.example.task04network

import android.app.AlertDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.example.task04network.adapter.CatFactsAdapter
import com.example.task04network.adapter.DogImagesAdapter
import com.example.task04network.databinding.ActivityMainBinding
import com.example.task04network.model.catFacts.CatFact
import com.example.task04network.viewModel.AnimalsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var dogImagesAdapter: DogImagesAdapter
    private lateinit var catFactAdapter: CatFactsAdapter
    private var images = mutableListOf<String>()
    private var facts = mutableListOf<CatFact>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProvider(this).get(AnimalsViewModel::class.java)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        changeTheme()
        val isTurnedOn = SharedPreference(this).getValueBoolean("switch", true)
        binding.changeTheme.isChecked = isTurnedOn

        viewModel.getDogImages("labrador", "10")
        viewModel.myDogImagesList.observe(this) {
            images = it as MutableList<String>
            setUpDogAdapter()
        }

        viewModel.getCatFacts(140, 10)
        viewModel.myCatFactsList.observe(this) {
            facts = it as MutableList<CatFact>
            setUpCatAdapter()
        }
   }

    private fun changeTheme() {
        binding.changeTheme.setOnCheckedChangeListener { _, _ ->

            if (binding.changeTheme.isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                binding.changeTheme.text = resources.getText(R.string.goToLightTheme)
                SharedPreference(this).save("switch", true)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                binding.changeTheme.text = resources.getText(R.string.goToNightTheme)
                SharedPreference(this).save("switch", false)
            }
        }
    }

    private fun setUpDogAdapter() {
        dogImagesAdapter = DogImagesAdapter(images)
        binding.rvDogs.adapter = dogImagesAdapter
    }

    private fun setUpCatAdapter() {
        catFactAdapter = CatFactsAdapter(facts)
        binding.rvCats.adapter = catFactAdapter
    }

    override fun onBackPressed() {
        AlertDialog.Builder(this).apply {
            setTitle(R.string.exit)

            setPositiveButton(R.string.BtnExitYes) { _, _ ->
                super.onBackPressed()
            }
            setNegativeButton(R.string.BtnExitNo) { _, _ -> }
            setCancelable(true)
        }.create().show()
    }
}