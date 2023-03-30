package com.example.healthcare.fragments.navFragment
import com.google.firebase.firestore.IgnoreExtraProperties;
import com.google.firebase.firestore.PropertyName

@IgnoreExtraProperties
data class patient (
    var uid:String?,
    val display_name: String?,
    val email:String?,
    val photoURL :String?,
    var ailments: String?,
    var age: String?,
    var phone_no:String?,
    ){
    @PropertyName("Age")
    fun getAge(Age:String): patient {
        this.age = Age
        return this
    }
    @PropertyName("Ailments")
    fun getAilments(Ailments:String):patient{
        this.ailments = Ailments
        return this
    }
    @PropertyName("Phone_no")
    fun getphoneno(Phone_no:String):patient{
        this.phone_no = Phone_no
        return this
    }
}