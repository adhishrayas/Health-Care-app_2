package com.example.healthcare.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.healthcare.MainActivity
import com.example.healthcare.R
import com.example.healthcare.databinding.FragmentSignInBinding
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class signInFragment : Fragment() {

    private var _binding: FragmentSignInBinding?= null
    private val binding get() = _binding!!
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignInBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

// write here

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MainActivity.bottomNav?.visibility = View.GONE
        firebaseAuth = FirebaseAuth.getInstance()
        binding.signInButton.setOnClickListener {
            val email = binding.email.text.toString()
            val pass = binding.pass.text.toString()
            firebaseAuth.signInWithEmailAndPassword(email,pass)
                .addOnSuccessListener { Task ->
                    Log.d(TAG,"Signed in sucesfully")
                }
            binding.signInButton.setOnClickListener{
                findNavController().navigate(R.id.action_signInFragment_to_homeFragment)
        }
            }

        binding.signUpRedirect.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }
    }

}

