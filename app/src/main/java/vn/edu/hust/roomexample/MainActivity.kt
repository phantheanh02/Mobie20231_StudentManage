package vn.edu.hust.roomexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import vn.edu.hust.roomexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var studentDao: StudentDao
    private lateinit var studentAdapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listStudents = listOf(
            Student("20204941", "Phan The Anh", "2002-02-01", "Hanoi"),
            Student("20204942", "Jane Doe", "2002-05-15", "US"),
            Student("20204943", "Bob Smith", "2002-09-30", "UK")
        )

        studentDao = StudentDatabase.getDatabase(this).studentDao()
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        studentAdapter = StudentAdapter()
        recyclerView.adapter = studentAdapter

        // Load students and update adapter
        studentAdapter.setStudents(listStudents)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fabAdd.setOnClickListener {
            val intent = Intent(this, AddStudentActivity::class.java)
            startActivity(intent)
        }

    }
}
