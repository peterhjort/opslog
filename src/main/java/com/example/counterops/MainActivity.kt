package com.example.counterops

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.*
import com.example.counterops.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        binding.balanceView.text = viewModel.balance.value.toString()

        binding.buttonUp.setOnClickListener {
            Log.d("QQQ", "buttonUp onclick")
            viewModel.insertOpsLogEntry(5)
        }
        binding.buttonDown.setOnClickListener {
            Log.d("QQQ", "buttonDown onclick")
            viewModel.insertOpsLogEntry(-5)
        }
        binding.buttonUp.setOnLongClickListener {
            startActivity(Intent(this, FeedbackNoteActivity::class.java))
            false
        }

        viewModel.balance.observe(this, {
            binding.balanceView.text = it.toString()
        })
    }
}

class MainActivityViewModel: ViewModel() {
    private val repository = OpsLogRepository
    val balance = Transformations.map(repository.logData) { it.map { it.amount }.sum() }

    fun insertOpsLogEntry(amount: Int) {
        viewModelScope.launch {
            repository.newOpsLogEntry(amount, "")
        }
    }
}