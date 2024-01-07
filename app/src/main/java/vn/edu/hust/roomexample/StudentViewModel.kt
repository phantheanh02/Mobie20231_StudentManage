package vn.edu.hust.roomexample

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StudentViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: StudentRepository
    val allStudents: LiveData<List<Student>>

    // LiveData để theo dõi quá trình thêm sinh viên
    private val _insertStatus = MutableLiveData<Boolean>()
    val insertStatus: LiveData<Boolean>
        get() = _insertStatus

    init {
        val studentDao = StudentDatabase.getDatabase(application).studentDao()
        repository = StudentRepository(studentDao)
        allStudents = repository.allStudents
    }

    fun insertStudent(student: Student) = viewModelScope.launch(Dispatchers.IO) {
        try {
            repository.insertStudent(student)
            // Thông báo về quá trình thêm sinh viên thành công
            _insertStatus.postValue(true)
        } catch (e: Exception) {
            // Xử lý lỗi khi thêm sinh viên
            _insertStatus.postValue(false)
        }
    }

    fun resetInsertStatus() {
        _insertStatus.value = null
    }

    fun updateStudent(student: Student) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateStudent(student)
    }

    fun deleteStudent(student: Student) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteStudent(student)
    }

    fun getStudentById(studentId: String): Student {
        return repository.getStudentById(studentId)
    }

}