package com.example.milestoneapp

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.milestoneapp.databinding.ActivityMainBinding
import android.content.DialogInterface
import android.content.Intent
import android.util.TypedValue
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    //マイルストーンデータの格納場所
    private var milestoneData: ArrayList<List<String?>> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //追加画面からの情報受け取り
        var a = intent.getStringExtra("EDIT_TEXT")
        var b = intent.getStringExtra("EDIT_INFORMATION")
        var c = intent.getStringExtra("EDIT_DATE")

        milestoneData.add(listOf(a,b,c))

        //レイアウトの作成
        val layout: LinearLayout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        layout.gravity = Gravity.CENTER

        //タイトルの作成
        val titleLabel = TextView(this)
        titleLabel.setText(R.string.title)

        titleLabel.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        titleLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 22F)

        /*val MLP = titleLabel.layoutParams as ViewGroup.MarginLayoutParams
        MLP.setMargins(0,0,10,74)
        titleLabel.layoutParams = MLP*/
        layout.addView(titleLabel)

        //ボタンの作成
        for(i in 0 until milestoneData.size) {
            val btn = Button(this)
            btn.text = milestoneData[i][0]
            /*val MLPP = btn.layoutParams as ViewGroup.MarginLayoutParams
            MLPP.setMargins(0,6,10,0)
            titleLabel.layoutParams = MLPP*/
            layout.addView(btn)

            btn.setOnClickListener {
                checkDetails(btn)
            }
        }

        //追加ボタン
        val addBtn = Button(this)
        addBtn.setText(R.string.add_milestone)
        /*val MLPPP = addBtn.layoutParams as ViewGroup.MarginLayoutParams
        MLPPP.setMargins(0,6,10,0)
        titleLabel.layoutParams = MLPPP*/
        layout.addView(addBtn)

        addBtn.setOnClickListener {
            val intent = Intent(applicationContext, AddActivity::class.java)
            startActivity(intent)
        }

        //画面を表示
        setContentView(layout)
    }

    fun showMilestone() {

        var a = intent.getStringExtra("EDIT_TEXT")
        var b = intent.getStringExtra("EDIT_INFORMATION")
        var c = intent.getStringExtra("EDIT_DATE")

        //milestoneData.add(listOf(a,b,c))

        /*for(i in 0 until milestoneData.size) {
            binding.Milestone${i}.text = milestoneData[i][0]
        }*/

    }

    fun checkDetails(btn: Button){

        val btnText = btn.text.toString()

        var alertTitle: String? = ""
        var alertDetails: String? = ""
        var alertDate: String? = ""

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
}