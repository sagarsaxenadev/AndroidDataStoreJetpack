package com.datastoreexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var appDataStore :AppDataStore



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appDataStore= AppDataStore(this)
        btnSave.setOnClickListener {

            saveUserInfo()
        }
        showData()
    }

    private fun saveUserInfo() {

     GlobalScope.launch {
         appDataStore.saveName(edtName.text.toString().trim())
         appDataStore.saveEmail(edtEmail.text.toString().trim())
     }
        Toast.makeText(this@MainActivity, "User information saved", Toast.LENGTH_SHORT).show()

    }

private  fun showData(){
    appDataStore.userNameFlow.asLiveData().observe(this, Observer {

        tvName.text="Name: $it"
    })

    appDataStore.userEmailFlow.asLiveData().observe(this, Observer {
        tvEmail.text="Email: $it"

    })
}
}