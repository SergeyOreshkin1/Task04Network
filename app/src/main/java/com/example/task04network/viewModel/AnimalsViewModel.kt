package com.example.task04network.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task04network.model.catFacts.CatFact
import com.example.task04network.usecase.GetCatFactsUseCase
import com.example.task04network.usecase.GetDogImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimalsViewModel @Inject constructor(
    val getDogImagesUseCase: GetDogImagesUseCase,
    val getCatFactsUseCase: GetCatFactsUseCase
) : ViewModel(){

    private  var _myDogImagesList = MutableLiveData<List<String>?>()
    val myDogImagesList: LiveData<List<String>?> = _myDogImagesList

    private var _myCatFactsList = MutableLiveData<List<CatFact>?>()
    val myCatFactsList: LiveData<List<CatFact>?> = _myCatFactsList

    fun getDogImages(breed: String, limit: String) {
       viewModelScope.launch {
            try {
                _myDogImagesList.value = getDogImagesUseCase(breed, limit)

            } catch (t: Throwable) {

            }
        }
    }

    fun getCatFacts(max_length: Int, limit: Int) {
        viewModelScope.launch {
            try {
                _myCatFactsList.value = getCatFactsUseCase(max_length, limit)
            } catch (t: Throwable) {

            }
        }
    }
}