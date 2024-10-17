package com.cscomer.freechat

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.cscomer.freechat.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import java.util.zip.Inflater

class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)

        binding.loginBt.setOnClickListener {

            val email = binding.EmailId.text.toString().trim()
            val password = binding.passwordId.text.toString().trim()

            if (isValidEmail(email) && isValidPassword(password)) {

                loginUser(email, password)
            } else {

                Toast.makeText(requireActivity(), "invalid Email and Password", Toast.LENGTH_SHORT)
                    .show()
            }


        }

        binding.createId.setOnClickListener{

          findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }


        return binding.root
    }

    private fun loginUser(email: String, password: String) {
        val auth=FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener {task->

                if (task.isSuccessful){
                    val user=auth.currentUser
                    Toast.makeText(requireActivity(), "login successful ${user?.email}", Toast.LENGTH_SHORT).show()
                     findNavController().navigate(R.id.action_loginFragment_to_homeFragment)

                }else{

                    Toast.makeText(requireActivity(), "${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }

        }



    }

    fun isValidEmail(email: String): Boolean {

    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isValidPassword(password:String):Boolean{

        val passRegex=Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,10}\$")
        return password.matches(passRegex)
    }

}