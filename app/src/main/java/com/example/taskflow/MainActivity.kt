package com.example.taskflow

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskflow.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db: TaskDatabseHelper
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = TaskDatabseHelper(this)
        taskAdapter = TaskAdapter(db.getAllTasks(),this)

        binding.tasksRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.tasksRecyclerView.adapter = taskAdapter

        binding.addButton.setOnClickListener{
            val intent = Intent(this,addTask::class.java)
            startActivity(intent)

        }
    }

    override fun onResume() {
        super.onResume()
        taskAdapter.refreshData(db.getAllTasks())
    }

}