package com.example.sqlitedemo

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var nameEditText: EditText
    private lateinit var ageEditText: EditText
    private lateinit var resultTextView: TextView
    private lateinit var addButton: Button
    private lateinit var viewButton: Button
    private lateinit var updateButton: Button
    private lateinit var deleteButton: Button
    private lateinit var idEditText: EditText
    private lateinit var viewSortedButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        databaseHelper = DatabaseHelper(this)
        idEditText = findViewById(R.id.et_id)
        nameEditText = findViewById(R.id.et_name)
        ageEditText = findViewById(R.id.et_age)
        resultTextView = findViewById(R.id.tv_result)
        addButton = findViewById(R.id.btn_add)
        viewButton = findViewById(R.id.btn_view)
        updateButton = findViewById(R.id.btn_update)
        deleteButton = findViewById(R.id.btn_delete)
        viewSortedButton = findViewById(R.id.btn_view_sorted)

        addButton.setOnClickListener { addUser() }
        viewButton.setOnClickListener { viewUsers() }
        updateButton.setOnClickListener { updateUser() }
        deleteButton.setOnClickListener { deleteUser() }
        viewSortedButton.setOnClickListener { viewUsersSorted() }
    }

    private fun addUser() {
        val id = idEditText.text.toString()
        val name = nameEditText.text.toString()
        val age = ageEditText.text.toString().toIntOrNull()

        if (id.isNotEmpty() && name.isNotEmpty() && age != null) {
            val success = databaseHelper.addUser(id, name, age)
            if (success) {
                Toast.makeText(this, "User added successfully!", Toast.LENGTH_SHORT).show()
                idEditText.text.clear()
                nameEditText.text.clear()
                ageEditText.text.clear()
            } else {
                Toast.makeText(this, "Failed to add user", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Please enter a valid ID, name, and age", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUser() {
        val id = idEditText.text.toString()
        val name = nameEditText.text.toString()
        val age = ageEditText.text.toString().toIntOrNull()

        if (id.isNotEmpty() && name.isNotEmpty() && age != null) {
            val success = databaseHelper.updateUser(id, name, age)
            if (success) {
                Toast.makeText(this, "User updated successfully!", Toast.LENGTH_SHORT).show()
                idEditText.text.clear()
                nameEditText.text.clear()
                ageEditText.text.clear()
            } else {
                Toast.makeText(this, "Failed to update user", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Please enter valid ID, name, and age", Toast.LENGTH_SHORT).show()
        }
    }

    private fun deleteUser() {
        val id = idEditText.text.toString()

        if (id.isNotEmpty()) {
            val success = databaseHelper.deleteUser(id)
            if (success) {
                Toast.makeText(this, "User deleted successfully!", Toast.LENGTH_SHORT).show()
                idEditText.text.clear()
            } else {
                Toast.makeText(this, "Failed to delete user", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Please enter a valid ID", Toast.LENGTH_SHORT).show()
        }
    }

    private fun viewUsersSorted() {
        val users = databaseHelper.getAllUsersSorted()
        resultTextView.text = if (users.isNotEmpty()) {
            users.joinToString("\n")
        } else {
            "No users found"
        }
    }

    private fun viewUsers() {
        val users = databaseHelper.getAllUsers()
        resultTextView.text = if (users.isNotEmpty()) {
            users.joinToString("\n")
        } else {
            "No users found"
        }
    }
}
