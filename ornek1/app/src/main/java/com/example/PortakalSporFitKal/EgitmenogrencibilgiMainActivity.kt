package com.example.PortakalSporFitKal
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.PortakalSporFitKal.vttercuman.DBHelper
import kotlinx.android.synthetic.main.egitmenogrencibilgi.*

class EgitmenogrencibilgiMainActivity:AppCompatActivity(){
    val db by lazy { DBHelper(this)  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.egitmenogrencibilgi)

        val id = intent.getStringExtra("ID")
        val res = db.gör("*","sporcu","WHERE ID = $id")
        adi.text = db.fromdb(res,"Ad")
        soyadi.text = db.fromdb(res,"Soyad")
        val kilo= db.fromdb(res,"Kilo").toFloat()
        kilog.text=kilo.toString()
        val boy = db.fromdb(res,"Boy").toFloat()
        boym.text=boy.toString()
        telefon.text = db.fromdb(res,"Telefon")
        vki.text =  ((kilo / (boy *boy))).toString()

        anas.setOnClickListener {
            val intent = Intent(this,EgitmenAnasayfaMainActivity::class.java)
            startActivity(intent)
        }

        geri.setOnClickListener {
            val intent = Intent(this,EgitmenogrencilisteMainActivity::class.java)
            startActivity(intent)
        }

        ogrencisil.setOnClickListener {
            val ad =db.fromdb(res,"Ad")
            val alert=AlertDialog.Builder(this)
            alert.setTitle("SİL")
            alert.setMessage(ad+"  adlı kişiyi silmek ister misinz?")
            alert.setPositiveButton("Evet"
            ) { dialogInterface, i ->
                db.sil("sporcu",id.toString())
                Toast.makeText(this@EgitmenogrencibilgiMainActivity,"Kullanıcı Silindi!!",Toast.LENGTH_LONG).show()
                val intent = Intent(this@EgitmenogrencibilgiMainActivity,EgitmenogrencilisteMainActivity::class.java)
                startActivity(intent)
            }
            alert.setNegativeButton("Hayır")
            {dialogInterface, i ->
            }
            alert.show()
        }
    }
}

