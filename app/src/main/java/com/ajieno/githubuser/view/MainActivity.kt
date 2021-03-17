package com.ajieno.githubuser.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ajieno.githubuser.R
import com.ajieno.githubuser.model.User
import com.ajieno.githubuser.viewModel.ListUserAdapter
import com.ajieno.githubuser.viewModel.userFilterList
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    private lateinit var rvUser: RecyclerView
    private var listData: ArrayList<User> = arrayListOf()
    private lateinit var adapter: ListUserAdapter

    companion object{
        private val TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = ListUserAdapter(listData)

        recylerViewConfig()
        getUser()
        getUser2()


    }

    private fun getUser2() {
        "hiyaaa"
    }

    private fun recylerViewConfig() {
        rvUser.layoutManager = LinearLayoutManager(rvUser.context)
        rvUser.setHasFixedSize(true)
        rvUser.addItemDecoration(
                DividerItemDecoration(
                        rvUser.context,
                        DividerItemDecoration.VERTICAL
                )
        )

    }

    private fun getUser(){
        progressBar.visibility = View.VISIBLE
        val client = AsyncHttpClient()
        val url = "https://quote-api.dicoding.dev/list"
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?) {

                progressBar.visibility = View.INVISIBLE
                val result = responseBody?.let { String(it) }
                Log.d(TAG, result)

                try {
                    val items = JSONArray(result)
                    for (i in 0 until items.length()) {
                        val item = items.getJSONObject(i)
                        val name = item.getString("author")
                        getDataGitDetail(name)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?, error: Throwable?) {
                Toast.makeText(this@MainActivity, error?.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun getDataGitDetail(id: String) {
        progressBar.visibility = View.VISIBLE
        val client = AsyncHttpClient()
        val url = "https://quote-api.dicoding.dev/list/$id"
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?) {
                progressBar.visibility = View.INVISIBLE
                val result = responseBody?.let { String(it) }
                Log.d(TAG, result)
                try {

                    showRecyclerList()
                } catch (e: Exception) {
                    Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
            }

            override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?, error: Throwable?) {

            }
        })
    }

    private fun showRecyclerList(){
        rvUser.layoutManager = LinearLayoutManager(this@MainActivity)
        val listUserAdapter = ListUserAdapter(userFilterList)
        rvUser.adapter = listUserAdapter
    }
}