package vn.edu.hust.roomexample

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students")
data class Student(
    @PrimaryKey
    @NonNull
    val studentId: String,
    var name: String,
    var dateOfBirth: String,
    var hometown: String
)