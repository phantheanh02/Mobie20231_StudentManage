package vn.edu.hust.roomexample

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class DetailActivity : AppCompatActivity() {
    private lateinit var studentViewModel: StudentViewModel
    private lateinit var student: Student
    private lateinit var editTextName: EditText
    private lateinit var editTextDateOfBirth: EditText
    private lateinit var editTextHometown: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        studentViewModel = ViewModelProvider(this).get(StudentViewModel::class.java)

        val studentId = intent.getStringExtra("studentId")
        studentId?.let {
            student = studentViewModel.getStudentById(it)
        }


        editTextName = findViewById(R.id.editTextName)
        editTextDateOfBirth = findViewById(R.id.editTextDateOfBirth)
        editTextHometown = findViewById(R.id.editTextHometown)

        // Set initial values
        editTextName.setText(student.name)
        editTextDateOfBirth.setText(student.dateOfBirth)
        editTextHometown.setText(student.hometown)

        val buttonUpdate: Button = findViewById(R.id.buttonUpdate)
        val buttonDelete: Button = findViewById(R.id.buttonDelete)

        buttonUpdate.setOnClickListener {
            // Update student and finish activity
            updateStudent()
            finish()
        }

        buttonDelete.setOnClickListener {
            // Delete student and finish activity
            deleteStudent()
            finish()
        }
    }

    private fun updateStudent() {
        student.name = editTextName.text.toString()
        student.dateOfBirth = editTextDateOfBirth.text.toString()
        student.hometown = editTextHometown.text.toString()
        studentViewModel.updateStudent(student)
    }

    private fun deleteStudent() {
        studentViewModel.deleteStudent(student)
    }
}
