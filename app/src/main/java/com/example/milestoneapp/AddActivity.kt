package com.example.milestoneapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.milestoneapp.databinding.ActivityAddBinding
import android.view.View
import android.widget.EditText

class AddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_add)

        binding = ActivityAddBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }


    private val editText: EditText = findViewById(R.id.editText)
    private val editInformation: EditText = findViewById(R.id.editInformation)
    private val editDate:EditText = findViewById(R.id.editDate)
    fun backToMilestone(view: View) {
        val intent = Intent(this@AddActivity, MainActivity::class.java)
        intent.putExtra("EDIT_TEXT", editText.text.toString())
        intent.putExtra("EDIT_INFORMATION", editInformation.text.toString())
        intent.putExtra("EDIT_DATE", editDate.text.toString())
        startActivity(intent)
    }
}