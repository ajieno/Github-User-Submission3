package com.ajieno.githubuser.util

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.ajieno.githubuser.db.DatabaseFavUser
import com.ajieno.githubuser.db.DatabaseFavUser.UserFavColumns.Companion.CONTENT_URI
import com.ajieno.githubuser.db.DatabaseFavUser.UserFavColumns.Companion.TABLE_NAME
import com.ajieno.githubuser.db.FavUserHelper

class FavProvider : ContentProvider() {

    companion object{
        private const val FAVUSER = 1
        private const val FAVUSER_ID = 2
        private val sUriMatcher = UriMatcher(UriMatcher.NO_MATCH)
        private lateinit var favHelper: FavUserHelper

        init {
            sUriMatcher.addURI(DatabaseFavUser.AUTHORITY, TABLE_NAME, FAVUSER)
            sUriMatcher.addURI(
                DatabaseFavUser.AUTHORITY,
                "$TABLE_NAME/#",
                FAVUSER_ID
            )
        }

    }

    override fun onCreate(): Boolean {
        favHelper = FavUserHelper.getInstance(context as Context)
        favHelper.open()
        return true
    }

    override fun query(
        uri: Uri, strings: Array<String>?, s: String?, strings1: Array<String>?, s1: String? ): Cursor? {
        return when (sUriMatcher.match(uri)) {
            FAVUSER -> favHelper.queryAll()
            FAVUSER_ID -> favHelper.queryById(uri.lastPathSegment.toString())
            else -> null
        }
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val added: Long = when (FAVUSER) {
            sUriMatcher.match(uri) -> favHelper.insert(values)
            else -> 0
        }

        context?.contentResolver?.notifyChange(CONTENT_URI, null)
        return Uri.parse("$CONTENT_URI/$added")
    }

    override fun update(uri: Uri, contentValues: ContentValues?, s: String?, strings: Array<String>?): Int {
        val updated: Int = when (FAVUSER_ID) {
            sUriMatcher.match(uri) -> favHelper.update(
                uri.lastPathSegment.toString(),
                contentValues
            )
            else -> 0
        }

        context?.contentResolver?.notifyChange(CONTENT_URI, null)
        return updated
    }

    override fun delete(uri: Uri, s: String?, strings: Array<String>?): Int {
        val deleted: Int = when (FAVUSER_ID) {
            sUriMatcher.match(uri) -> favHelper.deleteById(uri.lastPathSegment.toString())
            else -> 0
        }

        context?.contentResolver?.notifyChange(CONTENT_URI, null)
        return deleted
    }
}