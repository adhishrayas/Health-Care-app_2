package com.example.healthcare.fragments.navFragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthcare.MainActivity
import com.example.healthcare.R
import com.example.healthcare.databinding.FragmentHomeBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import javax.annotation.Nullable

class homeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding?= null
    private val binding get() = _binding!!
    private lateinit var recyclerView:RecyclerView
    private lateinit var DoctorArrayList: ArrayList<Doctors>
    private lateinit var myAdapter: adapter
    private lateinit var db: FirebaseFirestore


    override fun onCreateView(
        inflater: LayoutInflater, @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?
    ):View?{
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        val view = binding.root
        return  view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        dataInitialize()
        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        myAdapter = adapter(DoctorArrayList)
        recyclerView.adapter = myAdapter
        MainActivity.bottomNav?.visibility = View.VISIBLE
        //view.addRedirect.setOnClickListener {
       //     findNavController().navigate(R.id.action_homeFragment_to_addFragment)
      //  }
    }

    private fun dataInitialize(){
        DoctorArrayList = arrayListOf()
        db = Firebase.firestore
        db.collection("doctors")
            .get()
            .addOnSuccessListener { result ->
                for(document in result){
                    var d = Doctors(Doctor_Experience = document.data["Doctor_Experience"].toString(), Doctor_name = document.data["Doctor_name"].toString(), Doctor_specialization = document.data["Doctor_specialization"].toString())
                    DoctorArrayList.add(d)
                }
            }
    }


}

