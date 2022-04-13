package com.tsu.codewars2

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tsu.codewars2.room.DataRepository
import kotlinx.coroutines.launch
import java.util.*

class MainViewModel(app: Application) : AndroidViewModel(app) {


    private val dataRepository = DataRepository(app)
    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean>
        get() = _isError


    fun add(input:String, output:String) {
        viewModelScope.launch {
            dataRepository.addNew(
                id = UUID.randomUUID().toString(),
                input = input,
                output = output
            )
            _isError.value=false;
        }

    }
}