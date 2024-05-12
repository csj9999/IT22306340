package com.example.taskflow

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.taskflow.databinding.ActivityAddTaskBinding

class addTask : AppCompatActivity() {

private lateinit var binding: ActivityAddTaskBinding
private lateinit var db: TaskDatabseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db= TaskDatabseHelper(this)

        binding.saveButton.setOnClickListener{
            val title = binding.titleEdittext.text.toString()
            val content = binding.contentEditText.text.toString()
            val date = binding.dateEditText.text.toString()

            val task = Task( 0, title, content, date)
            db.insertTask(task)
            finish()
            Toast.makeText(this,"Task Added", Toast.LENGTH_SHORT).show()
        }

        }
    }
