package com.ajieno.githubuser.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ajieno.githubuser.R
import com.ajieno.githubuser.model.User
import com.ajieno.githubuser.util.ListUserAdapter
import com.ajieno.githubuser.util.UserData

class MainActivity : AppCompatActivity() {
    private lateinit var rvUser: RecyclerView
    private var list: ArrayList<User> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvUser = findViewById(R.id.rv_user)
        rvUser.setHasFixedSize(true)

        list.addAll(UserData.listData)

        showRecyclerviewList()


    }

    private fun showRecyclerviewList(){
        rvUser.layoutManager = LinearLayoutManager(this)
        val listUserAdapter = ListUserAdapter(list)
        rvUser.adapter = listUserAdapter
    }
}