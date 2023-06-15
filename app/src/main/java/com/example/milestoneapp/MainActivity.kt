package com.example.milestoneapp

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.milestoneapp.databinding.ActivityMainBinding
import android.content.DialogInterface
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.TypedValue
import android.view.Gravity
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.marginBottom

class MainActivity : AppCompatActivity() {

    //マイルストーンデータの格納場所
    private var milestoneData: ArrayList<List<String?>> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //追加画面からの情報受け取り
        val a = intent.getStringExtra("EDIT_TEXT")
        val b = intent.getStringExtra("EDIT_INFORMATION")
        val c = intent.getStringExtra("EDIT_DATE")

        milestoneData.add(listOf(a,b,c))

        //画面を表示
        setContentView(showMilestone())
    }

    private fun showMilestone() :LinearLayout{
        //レイアウトの作成
        val layout = LinearLayout(this)
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

        val lp: LinearLayout.LayoutParams = titleLabel.layoutParams as LinearLayout.LayoutParams
        val mlp: MarginLayoutParams = lp
        mlp.setMargins(0,0,0,100)
        layout.addView(titleLabel)

        //ボタンの作成
        if(milestoneData.size == 0) {
            val noTitle = TextView(this)
            noTitle.setText(R.string.noTitle)

            titleLabel.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            layout.addView(noTitle)
        } else {
            for(i in 0 until milestoneData.size) {
                val btn = Button(this)
                this.title = milestoneData[i][0]
                btn.layoutParams = LinearLayout.LayoutParams(900,180)

                val lpSecond: LinearLayout.LayoutParams = btn.layoutParams as LinearLayout.LayoutParams
                val mlpSecond: MarginLayoutParams = lpSecond
                mlpSecond.setMargins(0,18,0,0)

                val drawable: GradientDrawable = GradientDrawable()
                drawable.cornerRadius = 200f
                drawable.setStroke(6, Color.parseColor("#77ccff"))
                btn.background = drawable

                layout.addView(btn)

                btn.setOnClickListener {
                    checkDetails(btn)
                }
            }
        }

        //追加ボタン
        val addBtn = Button(this)
        addBtn.setText(R.string.add_milestone)
        addBtn.layoutParams = LinearLayout.LayoutParams(900,180)
        addBtn.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 22F)

        val lpThird: LinearLayout.LayoutParams = addBtn.layoutParams as LinearLayout.LayoutParams
        val mlpThird: MarginLayoutParams = lpThird
        mlpThird.setMargins(0,18,0,0)

        val drawable: GradientDrawable = GradientDrawable()
        drawable.color = ColorStateList.valueOf(Color.parseColor("#641fcc"))
        drawable.cornerRadius = 200f
        addBtn.background = drawable

        layout.addView(addBtn)

        addBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, AddActivity::class.java)
            startActivity(intent)
        }

        return layout
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