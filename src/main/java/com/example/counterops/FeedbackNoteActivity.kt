package com.example.counterops

import android.os.Bundle
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.viewModelScope
import com.example.counterops.databinding.ActivityFeedbackNoteBinding
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FeedbackNoteActivity : AppCompatActivity() {
    lateinit var viewModel: FeedbackNoteActivityViewModel
    lateinit var binding: ActivityFeedbackNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_feedback_note)

        binding.button.setOnClickListener {
            val noteText = binding.editTextTextPersonName.text.toString()
            val amount: Int = binding.seekBar.progress
            viewModel.viewModelScope.launch(Dispatchers.IO, CoroutineStart.DEFAULT) {
                OpsDatabase.getInstance().opsLogDAO.insert(
                    OpsLogEntry(timestamp = System.currentTimeMillis(), amount = amount, note = noteText)
                )
                runOnUiThread {
                    binding.editTextTextPersonName.text.clear()
                    binding.seekBar.progress = binding.seekBar.min
                }
            }
        }
        binding.seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.valueView.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }
}

class FeedbackNoteActivityViewModel: ViewModel()