package com.example.PortakalSporFitKal

import android.app.Activity
import android.app.ProgressDialog
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.PortakalSporFitKal.AnasMainActivity.Companion.m_UUID
import com.example.PortakalSporFitKal.AnasMainActivity.Companion.m_address
import com.example.PortakalSporFitKal.AnasMainActivity.Companion.m_bluetoothAdapter
import com.example.PortakalSporFitKal.AnasMainActivity.Companion.m_bluetoothSocket
import com.example.PortakalSporFitKal.AnasMainActivity.Companion.m_isConnected
import com.example.PortakalSporFitKal.AnasMainActivity.Companion.m_progress
import com.example.PortakalSporFitKal.vttercuman.DBHelper
import kotlinx.android.synthetic.main.ogrencianasayfa.*
import java.io.IOException
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

private class ConnectToDevice(c: Context): AsyncTask<Void, Void, String>(){

    private var connectSuccess:Boolean=true
    private val context: Context
    init {
        this.context=c
    }

    override fun onPreExecute() {
        super.onPreExecute()
        m_progress = ProgressDialog.show(context,"Bağlanıyor...","Lütfen Bekleyin")
    }
    override fun doInBackground(vararg params: Void?): String? {
        try {
            if (m_bluetoothSocket ==null || !m_isConnected){
                m_bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
                val device:BluetoothDevice= m_bluetoothAdapter.getRemoteDevice(
                    m_address
                )
                m_bluetoothSocket =device.createInsecureRfcommSocketToServiceRecord(
                    m_UUID
                )
                BluetoothAdapter.getDefaultAdapter().cancelDiscovery()
                m_bluetoothSocket!!.connect()
            }
        }catch (e: Exception){
            connectSuccess=false
            e.printStackTrace()
        }
        return null
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        if (!connectSuccess)
        {
            Toast.makeText(context,"Baglanamadı!!", Toast.LENGTH_LONG).show()
            Log.i("data","Bağlanamadı!!")
        }
        else
        {
            m_isConnected =true
            Toast.makeText(context,"Bağlandı!!", Toast.LENGTH_LONG).show()

        }
        m_progress.dismiss()
    }

}
class AnasMainActivity : AppCompatActivity() {
    private var m_bluetoothAdapter:BluetoothAdapter?=null
    private lateinit var m_pairedDevices:Set<BluetoothDevice>
    private val REQUEST_ENABLE_BLUETOOTH=1
    companion object{
        var EXTRA_ADRES:String="Devices_adress"
        var m_UUID: UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
        var m_bluetoothSocket: BluetoothSocket?=null
        lateinit var m_progress:ProgressDialog
        lateinit var m_bluetoothAdapter:BluetoothAdapter
        var m_isConnected:Boolean=false
        lateinit var m_address:String
    }
    private fun sendCommend(imput:String){
        if (m_bluetoothSocket != null)
        {
            try {
                m_bluetoothSocket!!.outputStream.write(imput.toByteArray())
            }
            catch (e: IOException){
                e.printStackTrace()
            }
        }
    }
    private fun disconnect(){
        if (m_bluetoothSocket != null)
        {
            try {
                m_bluetoothSocket!!.close()
                m_bluetoothSocket = null
                m_isConnected = false
            }catch (e: IOException)
            {
                e.printStackTrace()
            }
        }

    }

    val db by lazy { DBHelper(this)  }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ogrencianasayfa)
        m_bluetoothAdapter=BluetoothAdapter.getDefaultAdapter()
        button_pair.setOnClickListener {

            if (m_bluetoothAdapter==null){
                Toast.makeText(this@AnasMainActivity,"Bu cihaz bluetooth desteklemiyor!!", Toast.LENGTH_LONG).show()
            }
            else if (!m_bluetoothAdapter!!.isEnabled)
            {
                val enableBluetoothIntent=Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivityForResult(enableBluetoothIntent,REQUEST_ENABLE_BLUETOOTH)
            }
            else{
                m_bluetoothAdapter!!.disable()

            }
        }
        button_toggle.setOnClickListener {
            pairedDeviceList()
        }
        
        val id = DBHelper.User.id
        bilgiler.setOnClickListener{
            val infos = Intent(this,OgrencibilgilerMainActivity::class.java)
            startActivity(infos)
        }
        program.setOnClickListener{
            val intent = Intent(this,OgrenciProgramMainActivity1::class.java)
            startActivity(intent)
        }
        cikis.setOnClickListener{
            val intent = Intent(this,OgrenciuyegirisMainActivity::class.java)
            Toast.makeText(this@AnasMainActivity,"Çıkış Yapıldı!!", Toast.LENGTH_LONG).show()
            startActivity(intent)
        }

        val result = db.gör("*","sporcu","WHERE ID = $id")
        val ad = db.fromdb(result,"Ad")
        hosisim.text = "Hoşgeldin $ad"
    }
    fun pairedDeviceList() {
        m_bluetoothAdapter=BluetoothAdapter.getDefaultAdapter()
        m_pairedDevices=m_bluetoothAdapter!!.bondedDevices
        val list :ArrayList<BluetoothDevice> = ArrayList()
        if (m_pairedDevices.isNotEmpty()){
            for (device: BluetoothDevice in m_pairedDevices){
                list.add(device)
                Log.i("device",""+ device)
            }
        }
        else
        {
            Toast.makeText(this@AnasMainActivity,"Eşleşilmiş Bir Cihaz Bulunamadı!!", Toast.LENGTH_LONG).show()

        }
        val adapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,list)
        device_list.adapter=adapter
        device_list.onItemClickListener=AdapterView.OnItemClickListener { _,_, position, _ ->
            val device:BluetoothDevice=list[position]
            val address:String=device.address
            ConnectToDevice(this).execute()
            EXTRA_ADRES = address
            m_address = address
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==REQUEST_ENABLE_BLUETOOTH)
        {
            if (resultCode==Activity.RESULT_OK)
            {
                if (m_bluetoothAdapter!!.isEnabled){
                    Toast.makeText(this@AnasMainActivity,"Bluetooth Cevrimİçi", Toast.LENGTH_LONG).show()

                }
                else {
                    Toast.makeText(this@AnasMainActivity, "Bluetooth ÇevimDışı", Toast.LENGTH_LONG)
                        .show()
                }
            }else if(resultCode==Activity.RESULT_CANCELED)
            {
                Toast.makeText(this@AnasMainActivity,"Bluetooth İptal Edildi!!", Toast.LENGTH_LONG).show()

            }
        }
    }
}