package com.example.task04network

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.RecyclerView
import com.example.task04network.catFacts.CatFactsAPIService
import com.example.task04network.catFacts.Data
import com.example.task04network.dogImages.DogImagesAPIService
import com.example.task04network.adapter.DogImagesAndCatFactsAdapter
import com.example.task04network.adapter.DogsAndCatFacts
import com.example.task04network.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: DogImagesAndCatFactsAdapter
    private val data = mutableListOf<DogsAndCatFacts>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.changeTheme.setOnCheckedChangeListener { _, isChecked ->

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

        val isTurnedOn = SharedPreference(this).getValueBoolean("switch", true)
        binding.changeTheme.isChecked = isTurnedOn

        var dogs: List<String>
        var facts: List<Data>
        val limit = 10

        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Default) {

                dogs = getDogByBreed("labrador", limit)
                facts = getCatFacts(140, limit)
            }

            if (dogs.isNotEmpty() || facts.isNotEmpty()) {
                for (i in 1..limit) {
                    data.add(
                        DogsAndCatFacts(
                            dogs[i-1],
                            facts[i-1].fact
                        )
                    )
                }
            }
            setUpAdapter()
        }

        binding.rvDogs.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)){

                    CoroutineScope(Dispatchers.IO).launch {
                        withContext(Dispatchers.Default) {

                            dogs = getDogByBreed("labrador", limit)
                            facts = getCatFacts(140, limit)
                        }

                        if (dogs.isNotEmpty() || facts.isNotEmpty()) {
                            for (i in 1..limit) {
                                data.add(
                                    DogsAndCatFacts(
                                       dogs[i-1],
                                        facts[i-1].fact
                                    )
                                )
                            }
                        }
                        withContext(Dispatchers.Main) {
                            adapter.notifyDataSetChanged()
                        }

                    }
                }
            }
        })
    }

    private fun setUpAdapter() {
        adapter = DogImagesAndCatFactsAdapter(data)
        runOnUiThread {
            binding.rvDogs.adapter = adapter
        }
    }

    private fun showError() {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
    }

    private fun getRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private suspend fun getDogByBreed(query: String, limit: Int): List<String> {

        var list = listOf<String>()

        val call =
            getRetrofit("https://dog.ceo/api/breed/").create(DogImagesAPIService::class.java)
                .getDogsByBreeds("$query/images/random/$limit")
        val dogs = call.body()

        if (call.isSuccessful) {
            list = dogs?.dogImage ?: emptyList()
        } else {
            showError()
        }
        return list
    }

    private suspend fun getCatFacts(max_length: Int, limit: Int): List<Data> {
        var list = listOf<Data>()
        val call = getRetrofit("https://catfact.ninja/").create(CatFactsAPIService::class.java)
            .getCatsFacts(max_length, limit)
        val response = call.body()

        if (call.isSuccessful) {
            list = response?.data ?: emptyList()
        }
        return list
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


