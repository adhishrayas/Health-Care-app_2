package com.example.healthcare.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.healthcare.R
import com.example.healthcare.databinding.FragmentWelcomeBinding
import com.example.healthcare.databinding.FragmentYourCodeBinding


class yourCodeFragment : Fragment(R.layout.fragment_your_code) {

    private var _binding: FragmentYourCodeBinding?= null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentYourCodeBinding.inflate(layoutInflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.verifyButton.setOnClickListener{
            findNavController().navigate(R.id.action_yourCodeFragment_to_infoFragment)
        }
    }
}