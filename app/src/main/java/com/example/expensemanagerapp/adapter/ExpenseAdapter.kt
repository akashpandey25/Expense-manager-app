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

    private  var filterdList=list.toMutableList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewModel {
       return UserViewModel(CardDataBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return filterdList.size
    }
    fun updateData(dataItem:ArrayList<ExpenseModel>){
        list=dataItem
        filterdList = dataItem.toMutableList()
        notifyDataSetChanged()
    }
    fun filter(query: String) {
        filterdList = if (query.isEmpty()) {
            list.toMutableList() // Show all items if query is empty
        } else {
            list.filter { expense ->
                expense.description?.contains(query, ignoreCase = true) == true ||
                        expense.type?.contains(query, ignoreCase = true) == true
            }.toMutableList()
        }
        notifyDataSetChanged() // Refresh RecyclerView
    }


    override fun onBindViewHolder(holder: UserViewModel, position: Int) {
        val expense = filterdList[position] // Use filteredList
        holder.binding.desc.text = expense.description
        holder.binding.amount.text = expense.amount.toString()
        holder.binding.recordtype.text = expense.type
        val timestamp = list[position].timestamp
        val date = timestamp?.toDate()
        val sdf = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        val formattedDate = date?.let { sdf.format(it) }
        holder.binding.postedDate.text = formattedDate



    }
}