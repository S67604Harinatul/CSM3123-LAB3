package com.example.sharedpreferencesdemo

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var greetingTextView: TextView
    private lateinit var nameEditText: EditText
    private lateinit var ageEditText: EditText
    private lateinit var cityEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var loadButton: Button
    private lateinit var clearButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize UI components
        greetingTextView = findViewById(R.id.tv_greeting)
        nameEditText = findViewById(R.id.et_name)
        ageEditText = findViewById(R.id.et_age)
        cityEditText = findViewById(R.id.et_city)
        saveButton = findViewById(R.id.btn_save)
        loadButton = findViewById(R.id.btn_load)
        clearButton = findViewById(R.id.btn_clear)

        // Set up button click listeners
        saveButton.setOnClickListener {
            saveData()
        }

        loadButton.setOnClickListener {
            loadData()
        }

        clearButton.setOnClickListener {
            clearData()
        }
    }

    private fun saveData() {
        val name = nameEditText.text.toString()
        val age = ageEditText.text.toString()
        val city = cityEditText.text.toString()

        // Validate input fields
        if (name.isEmpty() || age.isEmpty() || city.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val sharedPreferences = getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putString("username", name)
        editor.putString("age", age)
        editor.putString("city", city)
        editor.apply()

        greetingTextView.text = "Data saved!"
    }

    private fun loadData() {
        val sharedPreferences = getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)
        val name = sharedPreferences.getString("username", "No name saved")
        val age = sharedPreferences.getString("age", "No age saved")
        val city = sharedPreferences.getString("city", "No city saved")

        greetingTextView.text = "Welcome, $name! Age: $age, City: $city"
    }

    private fun clearData() {
        val sharedPreferences = getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // Clear all stored data
        editor.clear()
        editor.apply()

        greetingTextView.text = "Data cleared!"
        nameEditText.text.clear()
        ageEditText.text.clear()
        cityEditText.text.clear()
    }
}
