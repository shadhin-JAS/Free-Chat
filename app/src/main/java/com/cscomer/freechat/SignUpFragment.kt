package com.cscomer.freechat

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.cscomer.freechat.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth


class SignUpFragment : Fragment() {
        lateinit var binding: FragmentSignUpBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentSignUpBinding.inflate(layoutInflater, container, false)


        binding.singBt.setOnClickListener {

            val name=binding.nameId.text.toString().trim()
            val email=binding.EmailId.text.toString().trim()
            val password=binding.passwordId.text.toString().trim()


            if (isValidEmail(email)&&isValidPassword(password)){

                loginuser(email,password,name)
            }else{

                Toast.makeText(requireActivity(), "check information", Toast.LENGTH_SHORT).show()
            }



        }


        return binding.root
    }

    private fun loginuser(email: String, password: String, name: String) {

        val auth=FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(password,email).addOnCompleteListener {task ->

            if (task.isSuccessful) {
                Toast.makeText(requireActivity(), "Create successful", Toast.LENGTH_SHORT).show()
                 findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)

            }else{
                Toast.makeText(requireActivity(), "${task.exception?.message}", Toast.LENGTH_SHORT).show()
            }

        }

    }

    fun isValidEmail(email:String):Boolean{

        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    fun isValidPassword(password:String):Boolean{

        val passRegex=Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,10}\$")
        return password.matches(passRegex)
    }
}