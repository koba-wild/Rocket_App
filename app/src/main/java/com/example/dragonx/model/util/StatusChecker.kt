package com.example.dragonx.model.util

sealed class StatusChecker<out T> {
    data class Done<out R>(val data: R) : StatusChecker<R>()
    data class Error(var myException: Exception) : StatusChecker<Nothing>()
}