package com.example.healthcare.fragments.navFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.healthcare.databinding.ListItemsBinding

class adapter(private val doctorlist: ArrayList<Doctors>) : RecyclerView.Adapter<adapter.MyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ListItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val Doctor :Doctors = doctorlist[position]
        holder.bind(Doctor)
    }

    override fun getItemCount(): Int {
        return doctorlist.size
    }

    class MyViewHolder(private val itemsBinding: ListItemsBinding):RecyclerView.ViewHolder(itemsBinding.root){
        fun bind(Doctor:Doctors){
            itemsBinding.DoctorName.text = Doctor.Doctor_name
            itemsBinding.DoctorSpecialization.text = Doctor.Doctor_specialization
        }
    }

}