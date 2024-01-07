package vn.edu.hust.roomexample

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

// AddStudentActivity.kt

class AddStudentActivity : AppCompatActivity() {

    private lateinit var viewModel: StudentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        viewModel = ViewModelProvider(this).get(StudentViewModel::class.java)

        val editTextMSSV: EditText = findViewById(R.id.editTextMSSV)
        val editTextName: EditText = findViewById(R.id.editTextName)
        val editTextDOB: EditText = findViewById(R.id.editTextDOB)
        val editTextHometown: EditText = findViewById(R.id.editTextHometown)
        val buttonAddStudent: Button = findViewById(R.id.buttonAddStudent)

        buttonAddStudent.setOnClickListener {
            // Lấy dữ liệu từ EditText
            val mssv = editTextMSSV.text.toString()
            val name = editTextName.text.toString()
            val dob = editTextDOB.text.toString()
            val hometown = editTextHometown.text.toString()

            if (mssv.isNotBlank() && name.isNotBlank() && dob.isNotBlank() && hometown.isNotBlank()) {
                // Tạo đối tượng sinh viên và thêm vào cơ sở dữ liệu
                val newStudent = Student(mssv, name, dob, hometown)
                viewModel.insertStudent(newStudent)

                // Đặt kết quả thành thành công và kết thúc Activity
                setResult(Activity.RESULT_OK)
                finish()
            } else {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

