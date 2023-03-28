package com.example.healthcare.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.healthcare.MainActivity.Companion.bottomNav
import com.example.healthcare.R
import com.example.healthcare.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment(R.layout.fragment_welcome) {

    private var _binding:FragmentWelcomeBinding ?= null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        _binding = FragmentWelcomeBinding.inflate(layoutInflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomNav?.visibility = View.GONE
       binding.buttonGetStarted.setOnClickListener {
           findNavController().navigate(R.id.action_welcomeFragment_to_signUpFragment)
       }
    }
}
