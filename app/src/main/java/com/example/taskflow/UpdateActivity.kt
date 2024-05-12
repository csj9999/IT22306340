package com.example.taskflow

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.taskflow.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateBinding
    private lateinit var db: TaskDatabseHelper
    private var taskId: Int=-1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        db = TaskDatabseHelper(this)

        taskId=intent.getIntExtra("task_id",-1)
        if(taskId==-1){
            finish()
            return
        }
        val task = db.getTaskbyID(taskId)
        binding.updateTitleEditText.setText(task.title)
        binding.updateContentEditText.setText(task.content)
        binding.updateDateEditText.setText(task.taskDate)

        binding.updateSaveButton.setOnClickListener{
            val newTitle = binding.updateTitleEditText.text.toString()
            val newContent = binding.updateContentEditText.text.toString()
            val newDate = binding.updateDateEditText.text.toString()

            val updateTask = Task(taskId,newTitle,newContent,newDate)
            db.updateTask(updateTask)
            finish()
            Toast.makeText(this,"Changes Saved",Toast.LENGTH_SHORT).show()
        }
    }
}
