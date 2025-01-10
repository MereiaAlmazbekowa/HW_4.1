package com.example.hw41.Data

import java.io.Serializable

data class UserModel(
    val name: String,
    val email: String,
    val password: String
) : Serializable
