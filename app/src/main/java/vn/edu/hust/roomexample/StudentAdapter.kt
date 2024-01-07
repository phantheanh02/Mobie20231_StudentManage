package vn.edu.hust.roomexample

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {
    private var students = listOf<Student>()

    // Constructor and methods for updating data

    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewMSSV: TextView = itemView.findViewById(R.id.textViewMSSV)
        val textViewName: TextView = itemView.findViewById(R.id.textViewName)
        val textViewEmail: TextView = itemView.findViewById(R.id.textViewEmail)
        val buttonViewDetails: Button = itemView.findViewById(R.id.buttonViewDetails)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_student, parent, false)
        return StudentViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val currentStudent = students[position]
    
        holder.textViewMSSV.text = currentStudent.studentId
        holder.textViewName.text = currentStudent.name
        holder.textViewEmail.text = generateEmail(currentStudent)

        holder.buttonViewDetails.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra("studentId", currentStudent.studentId)
            holder.itemView.context.startActivity(intent)
        }
    }



    override fun getItemCount(): Int {
        return students.size
    }

    // Add this function in your StudentAdapter
    private fun generateEmail(student: Student): String {
        val emailPrefix = student.name.toLowerCase().replace(" ", "") // Convert name to lowercase and remove spaces
        return "${student.studentId}_$emailPrefix@hust.edu.vn"
    }

    fun setStudents(studentsLiveData: LiveData<List<Student>>) {
        studentsLiveData.observeForever { newStudents ->
            // Khi LiveData thay đổi, cập nhật dữ liệu và thông báo cho RecyclerView
            students = newStudents
            notifyDataSetChanged()
        }
    }

    fun setStudents(students: List<Student>) {
        this.students = students
        notifyDataSetChanged()
    }
}
