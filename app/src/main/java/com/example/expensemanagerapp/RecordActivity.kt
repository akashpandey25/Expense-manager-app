package com.example.expensemanagerapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.expensemanagerapp.databinding.ActivityRecordBinding
import com.example.expensemanagerapp.model.ExpenseModel
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class RecordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecordBinding
    private var isDateSelected=false
    private lateinit var auth:FirebaseAuth
    private lateinit var db:FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        db=FirebaseFirestore.getInstance()
        auth=FirebaseAuth.getInstance()
        val currentUser=auth.currentUser


        binding.calanderdate.setOnClickListener {
            val datePicker= MaterialDatePicker.Builder.datePicker().setTitleText("Select date").build()
            datePicker.addOnPositiveButtonClickListener { selection->
                val sdf=SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
                val formattedDate=sdf.format(Date(selection))
                binding.calanderdate.text=formattedDate
                isDateSelected=true
            }
            datePicker.show(supportFragmentManager, "MaterialDatePicker")

            //mandatory date selection
            if(!isDateSelected){
                Toast.makeText(this, "Select a date", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnSave.setOnClickListener {
            val descp=binding.desc.text.toString()
            val amt=binding.amount.text.toString()
            val money=amt.toIntOrNull()?:0
            val selectedId=binding.radioGroup.checkedRadioButtonId
            val selectedType = when (selectedId) {
                R.id.expense-> "Expense"
                R.id.income -> "Income"
                else -> null
            }

            if(descp.isBlank()){
                Toast.makeText(this,"Enter the description", Toast.LENGTH_SHORT).show()
            }
            else if(amt.isBlank()){
                Toast.makeText(this,"Enter the description", Toast.LENGTH_SHORT).show()
            }
            else if(selectedType==null){
                Toast.makeText(this,"Select the record type",Toast.LENGTH_SHORT).show()
            }
            else{
                binding.btnSave.isEnabled=true
                val currentUserId = auth.currentUser!!.uid
                val newDocumentRef = db.collection("Records").document()


                val timestamp = Timestamp.now()


                // Create a data object with user information
                val data = ExpenseModel(

                    currentUserId,
                    descp,
                    money,
                    selectedType,
                    timestamp =timestamp
                )

                newDocumentRef.set(data)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            startActivity(Intent(this,MainActivity::class.java))
                            finish()
                            Toast.makeText(
                                this,
                                "Added Successfully",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        else{
                            Toast.makeText(this, "Unknown Error!! Please try again", Toast.LENGTH_SHORT).show()
                        }
                    }
            }

        }


        
    }
}