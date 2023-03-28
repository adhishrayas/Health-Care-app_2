package com.example.healthcare.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.healthcare.MainActivity.Companion.bottomNav
import com.example.healthcare.R
import com.example.healthcare.databinding.FragmentSignUpBinding
import com.example.healthcare.databinding.FragmentWelcomeBinding
import com.google.firebase.auth.FirebaseAuth


class SignUpFragment : Fragment() {



    private var _binding: FragmentSignUpBinding ?= null
    private val binding get() = _binding!!
    private lateinit var firebaseAuth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        _binding = FragmentSignUpBinding.inflate(layoutInflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomNav?.visibility = View.GONE

        firebaseAuth = FirebaseAuth.getInstance()

        binding.signUpButton.setOnClickListener {
            val email = binding.email.text.toString()
            val pass = binding.email.text.toString()

            firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                if (it.isSuccessful){
                    binding.signUpButton.setOnClickListener {
                        findNavController().navigate(R.id.action_signUpFragment_to_infoFragment)
                    }
                }
            }

        }

//
//        binding.signUpButton.setOnClickListener {
//            findNavController().navigate(R.id.action_signUpFragment_to_yourCodeFragment2)
//        }

        binding.signInRedirect.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
        }



    }

}