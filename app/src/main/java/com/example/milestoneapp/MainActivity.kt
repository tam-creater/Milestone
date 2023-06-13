package com.example.milestoneapp

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.milestoneapp.databinding.ActivityMainBinding
import android.content.DialogInterface
import android.content.Intent

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    //データを格納（変更・追加不可）
    private val milestoneData = mutableListOf(
        mutableListOf("会議","研修について", "2023/7/10"),
        mutableListOf("研修", "BS研修", "2023/7/12"),
        mutableListOf("開発", "画面設計", "2023/7/20"),
        mutableListOf("ミーティング", "7月の予定", "2023/6/15")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //マイルストーンを表示
        showMilestone()
    }

    fun showMilestone() {

        //マイルストーンのタイトルのみを格納
        binding.Milestone1.text = milestoneData[0][0]
        binding.Milestone2.text = milestoneData[1][0]
        binding.Milestone3.text = milestoneData[2][0]
        binding.Milestone4.text = milestoneData[3][0]

        /*for(i in 0 until milestoneData.size) {
            binding.Milestone${i}.text = milestoneData[i][0]
        }*/

    }

    fun checkDetails(view: View){
        //どのマイルストーンが選択されたか
        val pushedBtn: Button = findViewById(view.id)
        val btnText = pushedBtn.text.toString()

        //ダイアログのタイトル・詳細・日程を作成
        var alertTitle: String = ""
        var alertDetails: String = ""
        var alertDate: String = ""

        for(i in 0 until milestoneData.size){
            if(btnText == milestoneData[i][0]){
                alertTitle = milestoneData[i][0]
                alertDetails = milestoneData[i][1]
                alertDate = milestoneData[i][2]
            }
        }

        AlertDialog.Builder(this)
            .setTitle(alertTitle)
            .setMessage(alertDetails)
            .setPositiveButton("OK") { dialogInterface, which ->
                showMilestone()
            }
            .show()
    }

    fun addMilestone(view: View) {
        val intent = Intent(this@MainActivity, AddActivity::class.java)
        startActivity(intent)
    }

}