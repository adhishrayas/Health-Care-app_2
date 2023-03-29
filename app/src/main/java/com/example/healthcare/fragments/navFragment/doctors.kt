package com.example.healthcare.fragments.navFragment
import com.google.firebase.firestore.IgnoreExtraProperties;
@IgnoreExtraProperties
data class Doctors(
    var Doctor_name:String?,
    var Doctor_Experience:String?,
    var Doctor_specialization: String?,
)
