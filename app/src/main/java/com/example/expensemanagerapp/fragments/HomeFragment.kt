package com.example.expensemanagerapp.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.expensemanagerapp.R
import com.example.expensemanagerapp.RecordActivity
import com.example.expensemanagerapp.adapter.ExpenseAdapter
import com.example.expensemanagerapp.databinding.FragmentHomeBinding
import com.example.expensemanagerapp.model.ExpenseModel
import com.google.ar.core.Config
import com.google.firebase.firestore.FirebaseFirestore

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var db:FirebaseFirestore
    private lateinit var list:ArrayList<ExpenseModel>
    private  lateinit var adapter: ExpenseAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentHomeBinding.inflate(layoutInflater)

        list= java.util.ArrayList()
        adapter= ExpenseAdapter(this, list)

        db=FirebaseFirestore.getInstance()
        db.collection("Records").addSnapshotListener{value, error->
            val list= arrayListOf<ExpenseModel>()
            val data=value?.toObjects(ExpenseModel::class.java)
            list.addAll(data!!)

            binding.recyclerView.adapter=ExpenseAdapter(this,list)
            adapter.updateData(list)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fab.setOnClickListener {
            startActivity(Intent(requireContext(), RecordActivity::class.java))
        }

    }
}