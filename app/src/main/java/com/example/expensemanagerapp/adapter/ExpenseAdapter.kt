package com.example.expensemanagerapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.expensemanagerapp.databinding.CardDataBinding
import com.example.expensemanagerapp.fragments.HomeFragment
import com.example.expensemanagerapp.model.ExpenseModel
import java.text.SimpleDateFormat
import java.util.Locale

class ExpenseAdapter(val context:HomeFragment, var list:ArrayList<ExpenseModel>):
RecyclerView.Adapter<ExpenseAdapter.UserViewModel>(){
    class UserViewModel(val binding:CardDataBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewModel {
       return UserViewModel(CardDataBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }
    fun updateData(dataItem:ArrayList<ExpenseModel>){
        list=dataItem
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: UserViewModel, position: Int) {
        holder.binding.desc.text=list[position].description.toString()
        holder.binding.amount.text= list[position].amount.toString()
        holder.binding.recordtype.text=list[position].type.toString()
        val timestamp = list[position].timestamp
        val date = timestamp?.toDate()
        val sdf = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        val formattedDate = date?.let { sdf.format(it) }
        holder.binding.postedDate.text = formattedDate

    }
}