package com.example.counterops

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

class MyViewModel: ViewModel() {
    lateinit var opsSum: MutableLiveData<Int>
}