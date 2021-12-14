package com.example.postrequestapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {


    lateinit var addNew: Button
    lateinit var updtUser: Button
    lateinit var rvUsers : RecyclerView
    private lateinit var rvAdapter: rvAdapter
    lateinit var listalluser : Users


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        connectView()
        loadRCV()

        addNew.setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java))
        }

        updtUser.setOnClickListener {
            startActivity(Intent(this, MainActivity3::class.java))
        }

        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        val call: Call<Users> = apiInterface!!.getAll()

        call?.enqueue(object : Callback<Users> {
            override fun onResponse(call: Call<Users>, response: Response<Users>
            ) {
                listalluser = response.body()!!
                rvAdapter.update(listalluser)
            }

            override fun onFailure(call: Call<Users>, t: Throwable) {
                call.cancel()
                Log.d("MainActivity","${t.message}")
            }
        })


    }

    private fun connectView(){
        addNew = findViewById(R.id.addBtn)
        updtUser = findViewById(R.id.updtBtn)
        rvUsers = findViewById(R.id.RVusers)
        listalluser = Users()
    }

    private fun loadRCV(){
        rvUsers.adapter = rvAdapter(listalluser)
        rvUsers.layoutManager = LinearLayoutManager(this)
    }

}