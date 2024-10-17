package com.cscomer.freechat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.cscomer.freechat.databinding.FragmentHomeBinding
import com.cscomer.freechat.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth


class HomeFragment : Fragment() {
    lateinit var binding:FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentHomeBinding.inflate(layoutInflater,container,false)

        binding.logIV.setOnClickListener{

        val auth=FirebaseAuth.getInstance()
            auth.signOut().apply {

                findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
            }

        }


        return binding.root


    }


}