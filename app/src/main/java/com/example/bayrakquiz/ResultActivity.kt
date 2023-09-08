package com.example.bayrakquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val dogruSayac = intent.getIntExtra("dogruSayac", 0)

        val sonucText:TextView = findViewById(R.id.sonucText)
        val basariText:TextView = findViewById(R.id.yuzdeSonucText)

        sonucText.text = "$dogruSayac DOGRU ${10-dogruSayac} YANLIS"
        basariText.text = "% ${(dogruSayac*10)} Basari Yuzdesine Sahipsin"

        val basariYuzde = dogruSayac*10

        val sonucDeger:TextView = findViewById(R.id.sonucDegerTest)
        when(basariYuzde){
            10 -> sonucDeger.text = "AGA HIC BILEMEDIN?"
            20 -> sonucDeger.text = "BU DEGER COK BILE"
            30 -> sonucDeger.text = "DOGRU SOYLE SAKALIYOR MUSUN"
            40 -> sonucDeger.text = "YARISINI BILE BILEMEDIN"
            50 -> sonucDeger.text = "KULTURSUZ"
            60 -> sonucDeger.text = "YANI BILEMEDIM"
            70 -> sonucDeger.text = "EH ISTE GIDERIN VAR"
            80 -> sonucDeger.text = "IYI BIR YUZDE CALISMAYA DEVAM"
            90 -> sonucDeger.text = "HELAL LAN HEPSINI BILDIN SAYIYORUM"
            100 -> sonucDeger.text = "SEN BU ULKENIN PARLAK ZEKALARINDANSIN"
            else -> {
                sonucDeger.text = "SENIN KADARI YOK"
            }
        }

        val tekrardene:Button = findViewById(R.id.tekrarDene)
        tekrardene.setOnClickListener {
            startActivity(Intent(this@ResultActivity, QuizActivity::class.java))
            finish()
        }
    }
}
