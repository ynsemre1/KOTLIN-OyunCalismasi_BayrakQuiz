package com.example.bayrakquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import org.w3c.dom.Text

class QuizActivity : AppCompatActivity() {

    private lateinit var sorular:ArrayList<Bayraklar>
    private lateinit var yanlisSecenekler:ArrayList<Bayraklar>
    private lateinit var dogruSoru:Bayraklar
    private lateinit var tumSecenekler:HashSet<Bayraklar>
    private lateinit var vt:VeriTabaniYardimcisi

    private var soruSayac = 0
    private var dogruSayac = 0
    private var yanlisSayac = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        vt = VeriTabaniYardimcisi(this)

        sorular = BayraklarDAO().rastgeleBesBayrak(vt)
        soruYukle()

        val buttonA:Button = findViewById(R.id.aButton)
        val buttonB:Button = findViewById(R.id.bButton)
        val buttonC:Button = findViewById(R.id.dButton)
        val buttonD:Button = findViewById(R.id.cButton)

        buttonA.setOnClickListener {
            dogruKontrol(buttonA)
            soruSayacKontrol()
        }
        buttonB.setOnClickListener {
            dogruKontrol(buttonB)
            soruSayacKontrol()
        }
        buttonC.setOnClickListener {
            dogruKontrol(buttonC)
            soruSayacKontrol()
        }
        buttonD.setOnClickListener {
            dogruKontrol(buttonD)
            soruSayacKontrol()
        }
    }

    fun soruYukle(){
        val soruText:TextView = findViewById(R.id.soruSayisi)
        soruText.text = "${soruSayac+1}.Soru"

        dogruSoru = sorular.get(soruSayac)

        val bayrakImage:ImageView = findViewById(R.id.bayrakImage)
        bayrakImage.setImageResource(resources.getIdentifier(dogruSoru.bayrak_resim,"drawable",packageName))

        yanlisSecenekler = BayraklarDAO().rastgeleUcYanlisBayrak(vt,dogruSoru.bayrak_id)

        tumSecenekler = HashSet()
        tumSecenekler.add(dogruSoru)
        tumSecenekler.add(yanlisSecenekler.get(0))
        tumSecenekler.add(yanlisSecenekler.get(1))
        tumSecenekler.add(yanlisSecenekler.get(2))

        val buttonA:Button = findViewById(R.id.aButton)
        val buttonB:Button = findViewById(R.id.bButton)
        val buttonC:Button = findViewById(R.id.dButton)
        val buttonD:Button = findViewById(R.id.cButton)

        buttonA.text = tumSecenekler.elementAt(0).bayrak_ad
        buttonB.text = tumSecenekler.elementAt(1).bayrak_ad
        buttonC.text = tumSecenekler.elementAt(2).bayrak_ad
        buttonD.text = tumSecenekler.elementAt(3).bayrak_ad
    }

    fun soruSayacKontrol(){
        soruSayac++
        if (soruSayac != 10){
            soruYukle()
        }else{
            val intent = Intent(this@QuizActivity, ResultActivity::class.java)
            intent.putExtra("dogruSayac",dogruSayac)
            startActivity(intent)
            finish()
        }
    }

    fun dogruKontrol(button: Button){
        val buttonYazi = button.text.toString()
        val dogruCevap = dogruSoru.bayrak_ad

        if (buttonYazi == dogruCevap){
            dogruSayac++
        }else{
            yanlisSayac++
        }

        val dogruText:TextView = findViewById(R.id.dogruSayisi)
        val yanlisText:TextView = findViewById(R.id.yanlisSayisi)

        dogruText.text = "Dogru Sayisi: $dogruSayac"
        yanlisText.text = "Yanlis Sayisi: $yanlisSayac"
    }
}