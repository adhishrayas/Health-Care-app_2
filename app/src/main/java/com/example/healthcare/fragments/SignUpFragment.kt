package com.example.healthcare.fragments


import android.content.ContentValues.TAG
import android.content.Intent
import android.content.IntentSender
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.healthcare.MainActivity.Companion.bottomNav
import com.example.healthcare.R
import com.example.healthcare.databinding.FragmentSignUpBinding
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
class SignUpFragment : Fragment(R.layout.fragment_sign_up) {



    private var _binding: FragmentSignUpBinding ?= null
    private val binding get() = _binding!!
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var  oneTapClient: SignInClient
    private lateinit var signUpRequest: BeginSignInRequest
    private val REQ_ONE_TAP = 2
    private var showOneTapUI = true


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        _binding = FragmentSignUpBinding.inflate(layoutInflater,container,false)
        oneTapClient = Identity.getSignInClient(this.requireActivity())
        signUpRequest = BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setServerClientId(getString(R.string.AIzaSyBq4fy4kHBx0m6DmOR9x3pXkaIJJCI9L6Q))
                    .setFilterByAuthorizedAccounts(false)
                    .build())
            .build()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomNav?.visibility = View.GONE
        firebaseAuth = FirebaseAuth.getInstance()
        binding.signUpButton.setOnClickListener {
            val email = binding.email.text.toString()
            val pass = binding.email.text.toString()
            val db = Firebase.firestore
            firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                if (it.isSuccessful){
                    data class Newuser(
                        var uid:String?,
                        val displayname: String?,
                        val email:String?,
                        val photoURL :String?,
                    )
                    var newuser = Newuser(displayname = email,email = email,uid = null, photoURL = null)
                    db.collection("users")
                        .add(newuser)
                        .addOnSuccessListener { documentReference ->
                            newuser.uid = documentReference.id
                            db.collection("users").document(documentReference.id)
                                .update("uid",newuser.uid)
                            Log.d(TAG, "User added with id: ${documentReference.id}")
                        }
                        .addOnFailureListener { e ->
                            Log.w(TAG,"Error adding user",e)
                        }
                    binding.signUpButton.setOnClickListener {
                        findNavController().navigate(R.id.action_signUpFragment_to_infoFragment)
                    }
                }

            }

        }
        binding.googlesignup.setOnClickListener{
            oneTapClient.beginSignIn(signUpRequest)
                .addOnSuccessListener{ result ->
                    try {
                        startIntentSenderForResult(
                            result.pendingIntent.intentSender,REQ_ONE_TAP,
                            null,0,0,0,Bundle())
                    }catch (e:IntentSender.SendIntentException){
                        Log.e(TAG,"couldnt start one tap ui:${e.localizedMessage}")
                    }
                }
        }
        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)
            when(requestCode){
                REQ_ONE_TAP ->{
                    try{
                        val credential = oneTapClient.getSignInCredentialFromIntent(data)
                        val idToken = credential.googleIdToken
                        when{
                            idToken != null -> {
                                Log.d(TAG,"Got ID token")
                            }
                            else -> {
                                Log.d(TAG,"No ID token!")
                            }
                        }
                    }catch (e:ApiException){
                        when(e.statusCode){
                            CommonStatusCodes.CANCELED -> {
                                Log.d(TAG,"One-tap was closed")
                                showOneTapUI = false
                            }
                            CommonStatusCodes.NETWORK_ERROR -> {
                                Log.d(TAG,"One tap got network error")
                            }
                            else -> {
                                Log.d(TAG,"Couldnt get credentials" + "(${e.localizedMessage})")
                            }
                        }
                    }
                }
            }
        }

        binding.signInRedirect.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
        }



    }

}