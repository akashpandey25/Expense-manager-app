package com.example.expensemanagerapp.model

import com.google.firebase.Timestamp

data class ExpenseModel(
    var id: String="",
    var description:String?="",
    var amount: Int=0,
    var type:String?="",
    val timestamp: Timestamp? =null
){
    constructor() : this("", "", 0, "")
}
