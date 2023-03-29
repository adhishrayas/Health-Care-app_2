package com.example.healthcare.fragments.navFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.healthcare.databinding.ListItemsBinding

class adapter(private val doctorlist: ArrayList<Doctors>) : RecyclerView.Adapter<adapter.MyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adapter.MyViewHolder {
        val binding = ListItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }
    override fun onBindViewHolder(holder: adapter.MyViewHolder, position: Int) {
        val Doctor :Doctors = doctorlist[position]
        holder.bind(Doctor)
    }

    override fun getItemCount(): Int {
        return doctorlist.size
    }

    public class MyViewHolder(private val itemsBinding: ListItemsBinding):RecyclerView.ViewHolder(itemsBinding.root){
        fun bind(Doctor:Doctors){
            itemsBinding.Doctorname.text = Doctor.Doctor_name
            itemsBinding.DoctorExperience.text = Doctor.Doctor_Experience
            itemsBinding.DoctorSpecialization.text = Doctor.Doctor_specialization
        }
    }

}