package com.example.healthcare.fragments.navFragment

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SpinnerAdapter
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.healthcare.R
import com.example.healthcare.databinding.FragmentInfoBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class infoFragment : Fragment() {
    private val gender = arrayOf("Male","Female","Rather not say")
    private var _binding: FragmentInfoBinding?= null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentInfoBinding.inflate(layoutInflater,container,false)
        val spinner = binding.spinner
        spinner.adapter = activity?.let { ArrayAdapter(it.applicationContext, androidx.transition.R.layout.support_simple_spinner_dropdown_item,gender) } as SpinnerAdapter
         spinner.onItemSelectedListener = object:AdapterView.OnItemSelectedListener{
             override fun onNothingSelected(parent:AdapterView<*>?){
                 println("Error")
             }

             override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                 val type = parent?.getItemAtPosition(position).toString()
                 Toast.makeText(activity,type,Toast.LENGTH_LONG).show()
                 println(type)
             }
         }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.continueButton.setOnClickListener {
            val name = binding.Name.text.toString()
            val Phone_no = binding.phoneno.text.toString()
            val Age = binding.Age.text.toString()
            val Ailments = binding.Ailments.text.toString()
            val db = Firebase.firestore
            var currentuser = FirebaseAuth.getInstance().currentUser
            var newpatient = patient(uid = currentuser?.uid, display_name = name, email = currentuser?.email,photoURL = null,ailments = Ailments, age = Age, phone_no = Phone_no)
            db.collection("patients")
                .add(newpatient)
                .addOnSuccessListener {
                    Log.d(TAG,"Patient added")
                }
            binding.continueButton.setOnClickListener {
                findNavController().navigate(R.id.action_infoFragment_to_homeFragment)
            }

        }
        binding.back.setOnClickListener{
            findNavController().navigate(R.id.action_infoFragment_to_signUpFragment)
        }
    }


}