package com.example.taskflow

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter (private var tasks: List<Task>, private val context: Context) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    private  val db : TaskDatabseHelper = TaskDatabseHelper(context)

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextview)
        val contentTextView: TextView = itemView.findViewById(R.id.contentTextView)
        val dateTextView: TextView = itemView.findViewById(R.id.dateTextView)
        val updateButton: ImageView= itemView.findViewById(R.id.updateButton)
        val deleteButton: ImageView= itemView.findViewById(R.id.deleteButton)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return TaskViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.titleTextView.text = task.title
        holder.contentTextView.text = task.content
        holder.dateTextView.text = task.taskDate

        holder.updateButton.setOnClickListener{
            val intent = Intent(holder.itemView.context,UpdateActivity::class.java).apply{
                putExtra("task_id",task.id)
            }
            holder.itemView.context.startActivity(intent)
        }

        holder.deleteButton.setOnClickListener{
            db.deleteTask(task.id)
            refreshData(db.getAllTasks())
            Toast.makeText(holder.itemView.context,"Task Delete",Toast.LENGTH_SHORT).show()

        }



    }

    fun refreshData(newTasks: List<Task>) {
        tasks = newTasks
        notifyDataSetChanged()
    }
}
