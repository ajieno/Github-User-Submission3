package com.ajieno.githubuser.util

import com.ajieno.githubuser.model.User

object UserData {
    private  val userUsername = arrayOf(
        "JakeWharton",
        "amitshekhariitbhu",
        "romainguy",
        "chrisbanes",
        "tipsy",
        "ravi8x",
        "jasoet",
        "budioktaviyan",
        "hendisantika",
        "sidiqpermana"
    )

    private val userName = arrayOf(
        "Jake Wharton",
        "AMIT SHEKHAR",
        "Romain Guy",
        "Chris Banes",
        "David",
        "Ravi Tamada",
        "Deny Prasetyo",
        "Budi Oktaviyan",
        "Hendi Santika",
        "Sidiq Permana"
    )

    private val userAvatar = arrayOf(
        "aww"
    )

    private val userCompany = arrayOf(
        "Google, Inc.",
        "@MindOrksOpenSource",
        "Google",
        "@google working on @android",
        "Working Group Two",
        "AndroidHive | Droid5",
        "@gojek-engineering",
        "@KotlinID",
        "@JVMDeveloperID @KotlinID @IDDevOps",
        "Nusantara Beta Studio"
    )

    private val userLocation = arrayOf(
        "Pittsburgh, PA, USA",
        "New Delhi, India",
        "California",
        "Sydney, Australia",
        "Trondheim, Norway",
        "India",
        "Kotagede, Yogyakarta, Indonesia",
        "Jakarta, Indonesia",
        "Bojongsoang - Bandung Jawa Barat",
        "Jakarta Indonesia"
    )

    private val userRepository = arrayOf(
        "102",
        "37",
        "9",
        "30",
        "56",
        "28",
        "44",
        "110",
        "1064",
        "65"
    )

    private val userFollower = arrayOf(
        "56995",
        "5253",
        "7972",
        "14725",
        "788",
        "18628",
        "277",
        "178",
        "428",
        "465"
    )

    private val userFollowing = arrayOf(
        "12",
        "2",
        "0",
        "1",
        "0",
        "3",
        "39",
        "23",
        "61",
        "10"
    )

    val listData: ArrayList<User>
        get() {
            val list = arrayListOf<User>()
            for (position in userUsername.indices) {
                val user = User()
                user.username = userName[position]
                user.name = userUsername[position]
                user.avatar = userAvatar[position]
                user.company = userCompany[position]
                user.location = userLocation[position]
                user.repository = userRepository[position]
                user.follower = userFollower[position]
                user.following = userFollowing[position]
                list.add(user)
            }
            return list
        }
}