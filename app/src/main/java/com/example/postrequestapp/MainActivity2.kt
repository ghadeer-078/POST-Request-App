package com.example.postrequestapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity2 : AppCompatActivity() {

    lateinit var saveBtn : Button
    lateinit var viewBtn : Button
    lateinit var edName : EditText
    lateinit var edLocation : EditText

    val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        connectView()

        saveBtn.setOnClickListener {
            saveUinfo()
        }

        viewBtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

    }

    private fun connectView(){
        edName = findViewById(R.id.edName)
        edLocation = findViewById(R.id.edLoc)
        saveBtn = findViewById(R.id.svBtn)
        viewBtn = findViewById(R.id.vBtn)
    }

    private fun saveUinfo(){
        val name = edName.text.toString()
        val location = edLocation.text.toString()

        apiInterface!!.addUser(
            UsersItem(
                name,
                location,
                0
            )
        ).enqueue(object : Callback<UsersItem> {
            override fun onResponse(call: Call<UsersItem>, response: Response<UsersItem>) {
                Toast.makeText(applicationContext, "Save Successful", Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<UsersItem>, t: Throwable) {
                Toast.makeText(applicationContext, "Not Save Successful", Toast.LENGTH_LONG).show()
            }
        })

        //clear all editTexts
        edName.text.clear()
        edName.clearFocus()
        edLocation.text.clear()
        edLocation.clearFocus()
    }

}