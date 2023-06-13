package com.example.milestoneapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.milestoneapp.databinding.ActivityAddBinding
import android.view.View

class AddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_add)

        binding = ActivityAddBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    fun backToMilestone(view: View) {
        val intent = Intent(this@AddActivity, MainActivity::class.java)
        startActivity(intent)
    }
}