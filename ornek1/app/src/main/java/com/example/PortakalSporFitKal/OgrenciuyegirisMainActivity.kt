package com.example.PortakalSporFitKal
import android.content.Intent
import android.database.CursorIndexOutOfBoundsException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.PortakalSporFitKal.vttercuman.DBHelper
import kotlinx.android.synthetic.main.ogrenciuyegiris.*

class OgrenciuyegirisMainActivity:AppCompatActivity(){
    val db by lazy { DBHelper(this)  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ogrenciuyegiris)

        geri.setOnClickListener {
            val intent = Intent(this, OgrencigirisMainActivity::class.java)
            startActivity(intent)
        }
        giris.setOnClickListener {
            val girilenkadi = kula.text.toString()
            val girilensfr = sifre.text.toString()
            val result = db.gör("ID","sporcu","WHERE Kullanici_adi = '$girilenkadi' AND Sifre = '$girilensfr'")
            try {
                val id = db.fromdb(result,"ID")
                DBHelper.User.id = id.toInt()
                val enter = Intent(this, AnasMainActivity::class.java)
                startActivity(enter)

            }
            catch (e: CursorIndexOutOfBoundsException){
                Toast.makeText(this@OgrenciuyegirisMainActivity,"KULLANICI ADI VEYA SİFRE YANLIS!!",Toast.LENGTH_LONG).show()
            }


        }


    }

}

