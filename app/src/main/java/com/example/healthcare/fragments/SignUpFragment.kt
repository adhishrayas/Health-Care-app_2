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


class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding ?= null
    private val binding get() = _binding!!


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
        binding.signUpButton.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_yourCodeFragment2)
        }

        binding.signInRedirect.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
        }

    }

}