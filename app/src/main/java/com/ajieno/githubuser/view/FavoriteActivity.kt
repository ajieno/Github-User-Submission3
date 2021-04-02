package com.ajieno.githubuser.view

import android.database.ContentObserver
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.ajieno.githubuser.R
import com.ajieno.githubuser.db.DatabaseFavUser.UserFavColumns.Companion.CONTENT_URI
import com.ajieno.githubuser.db.FavUserHelper
import com.ajieno.githubuser.model.User
import com.ajieno.githubuser.model.UserFavorite
import com.ajieno.githubuser.util.MappingHelper
import com.ajieno.githubuser.viewModel.ListUserAdapter
import com.ajieno.githubuser.viewModel.ListUserFavAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_favorite.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class FavoriteActivity : AppCompatActivity() {

    private lateinit var adapter: ListUserFavAdapter

    companion object{
        private const val  EXTRA_STATE = "EXTRA_STATE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        rv_fav_user.layoutManager = LinearLayoutManager(this)
        rv_fav_user.setHasFixedSize(true)

        adapter = ListUserFavAdapter(this)
        rv_fav_user.adapter = adapter

        val handlerThread = HandlerThread("DataObserver")
        handlerThread.start()
        val handler = Handler(handlerThread.looper)

        val myObserver = object  : ContentObserver(handler){
            override fun onChange(selfChange: Boolean) {
                loadNotesAsync()
            }
        }

        contentResolver.registerContentObserver(CONTENT_URI, true, myObserver)

        if (savedInstanceState == null) {
            loadNotesAsync()
        } else {
            val list = savedInstanceState.getParcelableArrayList<UserFavorite>(EXTRA_STATE)
            if (list != null) {
                adapter.listUserFavorite = list
            }
        }

    }


    private fun loadNotesAsync() {
        GlobalScope.launch(Dispatchers.Main) {
            progressbar_fav.visibility = View.VISIBLE

            val favUserHelper = FavUserHelper.getInstance(applicationContext)
            favUserHelper.open()

            val deferredNotes = async(Dispatchers.IO) {
                val cursor = contentResolver?.query(CONTENT_URI, null, null, null, null)
                MappingHelper.mapCursorToArrayList(cursor)
            }
            val favData = deferredNotes.await()
            progressbar_fav.visibility = View.INVISIBLE
            if (favData.size > 0) {
                adapter.listUserFavorite = favData
            } else {
                adapter.listUserFavorite = ArrayList()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(EXTRA_STATE, adapter.listUserFavorite)
    }

    override fun onResume() {
        super.onResume()
        loadNotesAsync()
    }
}