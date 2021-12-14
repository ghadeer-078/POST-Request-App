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

class MainActivity3 : AppCompatActivity() {

    lateinit var edtID : EditText
    lateinit var edNamUpdt : EditText
    lateinit var edLocUpdt : EditText
    lateinit var updtBtn : Button
    lateinit var delBtn : Button
    lateinit var turnBtn : Button


    val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        connectView()

        updtBtn.setOnClickListener {
            val userID = edtID.text.toString().toInt()

            apiInterface?.updateUser(userID!!, UsersItem(
                edLocUpdt.text.toString(),
                edNamUpdt.text.toString(),
                userID
            )
            )?.enqueue(object : Callback<UsersItem> {
                override fun onResponse(call: Call<UsersItem>, response: Response<UsersItem>) {
                    Toast.makeText(this@MainActivity3, "User Updated", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<UsersItem>, t: Throwable) {
                    Toast.makeText(this@MainActivity3, "User not updated!", Toast.LENGTH_SHORT).show()
                }
            })
        }


        delBtn.setOnClickListener {
            val userID = edtID.text.toString().toInt()

            apiInterface?.deleteUser(userID!!)?.enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    Toast.makeText(this@MainActivity3, "User deleted!", Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(this@MainActivity3, "No User deleted", Toast.LENGTH_LONG).show()
                }
            })
        }


        turnBtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

    }


    private fun connectView(){
        edtID= findViewById(R.id.idEdt)
        edNamUpdt= findViewById(R.id.ednamUp)
        edLocUpdt= findViewById(R.id.edlocUp)
        updtBtn= findViewById(R.id.updateUbt)
        delBtn= findViewById(R.id.delBtn)
        turnBtn=findViewById(R.id.turnBtn)
    }


}