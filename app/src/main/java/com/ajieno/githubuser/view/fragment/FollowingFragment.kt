package com.ajieno.githubuser.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.ajieno.githubuser.R
import com.ajieno.githubuser.model.User
import com.ajieno.githubuser.model.UserFavorite
import com.ajieno.githubuser.view.DetailUserActivity
import com.ajieno.githubuser.viewModel.ListFollowingAdapter
import com.ajieno.githubuser.viewModel.followingFilterList
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import kotlinx.android.synthetic.main.fragment_following.*
import org.json.JSONArray
import org.json.JSONObject


class FollowingFragment : Fragment() {

    companion object{
        private val TAG = FollowingFragment::class.java.simpleName
        const val Extra = "Extra"
        const val Extra_fav = "Extra_fav"
        private var userFavorite: UserFavorite? = null
        private lateinit var user1: UserFavorite
        private lateinit var user2: User
    }

    private var listData: ArrayList<User> = ArrayList()
    private lateinit var adapter: ListFollowingAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_following, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ListFollowingAdapter(listData)
        listData.clear()

        FollowingFragment.userFavorite = activity!!.intent.getParcelableExtra(DetailUserActivity.Extra_fav)
        if (FollowingFragment.userFavorite != null) {
            FollowingFragment.user1 = activity!!.intent.getParcelableExtra(FollowersFragment.Extra_fav) as UserFavorite
            getDataGit(FollowingFragment.user1.username.toString())
        } else {
            FollowingFragment.user2 = activity!!.intent.getParcelableExtra(FollowersFragment.Extra) as User
            getDataGit(FollowingFragment.user2.username.toString())
        }
    }

    private fun getDataGit(id: String){
        progressbar_following?.visibility = View.VISIBLE
        val client = AsyncHttpClient()
        client.addHeader("Authorization", "token <yourtoken>")
        client.addHeader("User-Agent", "request")
        val url = "https://api.github.com/users/$id/following"
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                    statusCode: Int,
                    headers: Array<Header>,
                    responseBody: ByteArray
            ) {
                progressbar_following?.visibility = View.INVISIBLE
                val result = String(responseBody)
                Log.d(TAG, result)
                try {
                    val jsonArray = JSONArray(result)
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        val username: String = jsonObject.getString("login")
                        getDataGitDetail(username)
                    }
                } catch (e: Exception) {
                    Toast.makeText(activity, e.message, Toast.LENGTH_SHORT)
                            .show()
                    e.printStackTrace()
                }
            }

            override fun onFailure(
                    statusCode: Int,
                    headers: Array<Header>,
                    responseBody: ByteArray,
                    error: Throwable
            ) {
                progressbar_following?.visibility = View.INVISIBLE
                val errorMessage = when (statusCode) {
                    401 -> "$statusCode : Bad Request"
                    403 -> "$statusCode : Forbidden"
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode : ${error.message}"
                }
                Toast.makeText(activity, errorMessage, Toast.LENGTH_LONG)
                        .show()
            }
        })
    }

    private fun getDataGitDetail(id: String) {
        progressbar_following?.visibility = View.VISIBLE
        val client = AsyncHttpClient()
        client.addHeader("Authorization", "token <yourtoken>")
        client.addHeader("User-Agent", "request")
        val url = "https://api.github.com/users/$id"
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                    statusCode: Int,
                    headers: Array<Header>,
                    responseBody: ByteArray
            ) {
                progressbar_following?.visibility = View.INVISIBLE
                val result = String(responseBody)
                Log.d(TAG, result)
                try {
                    val jsonObject = JSONObject(result)
                    val username: String? = jsonObject.getString("login").toString()
                    val name: String? = jsonObject.getString("name").toString()
                    val avatar: String? = jsonObject.getString("avatar_url").toString()
                    val company: String? = jsonObject.getString("company").toString()
                    val location: String? = jsonObject.getString("location").toString()
                    val repository: Int = jsonObject.getInt("public_repos")
                    val followers: Int = jsonObject.getInt("followers")
                    val following: Int = jsonObject.getInt("following")
                    listData.add(
                            User(
                                    username,
                                    name,
                                    avatar,
                                    company,
                                    location,
                                    repository,
                                    followers,
                                    following
                            )
                    )
                    showRecyclerList()
                } catch (e: Exception) {
                    if (context != null) {
                        Toast.makeText(activity, e.message, Toast.LENGTH_LONG).show()
                    }
                    e.printStackTrace()
                }
            }

            override fun onFailure(
                    statusCode: Int,
                    headers: Array<Header>,
                    responseBody: ByteArray,
                    error: Throwable
            ) {
                progressbar_following?.visibility = View.INVISIBLE
                val errorMessage = when (statusCode) {
                    401 -> "$statusCode : Bad Request"
                    403 -> "$statusCode : Forbidden"
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode : ${error.message}"
                }
                Toast.makeText(activity, errorMessage, Toast.LENGTH_LONG)
                        .show()
            }
        })
    }

    private fun showRecyclerList() {
        rv_following.layoutManager = LinearLayoutManager(activity)
        val listDataAdapter = ListFollowingAdapter(followingFilterList)
        rv_following.adapter = listDataAdapter

    }

}