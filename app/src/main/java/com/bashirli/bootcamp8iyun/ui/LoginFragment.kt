package com.bashirli.bootcamp8iyun.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.bashirli.bootcamp8iyun.R
import com.bashirli.bootcamp8iyun.databinding.FragmentLoginBinding
import com.bashirli.bootcamp8iyun.databinding.FragmentTwiceBinding
import com.bashirli.bootcamp8iyun.util.Constants.SP_BASE
import com.bashirli.bootcamp8iyun.util.Constants.TOKEN
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding?=null
    private val binding get()=_binding!!

    private lateinit var sp:SharedPreferences
    private lateinit var auth:FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            setup()
    }

    private fun setup(){
        auth=FirebaseAuth.getInstance()
        sp=requireActivity().getSharedPreferences(SP_BASE, Context.MODE_PRIVATE)
        with(binding){
            buttonGoBack.setOnClickListener {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToTwiceFragment())
            }

            buttonSignIn.setOnClickListener {
                login()
            }

        }
    }

    private fun login(){
        val email=binding.editEmail.text.toString()
        val pass=binding.editPass.text.toString()

        if(errorCheck(email.trim(),pass.trim())){
            return
        }

        auth.signInWithEmailAndPassword(email,pass)
            .addOnSuccessListener {

                setUserData()

        }.addOnFailureListener {
            Toast.makeText(requireContext(),it.localizedMessage,Toast.LENGTH_SHORT).show()
        }

    }

    private fun setUserData(){
        val currentUser=auth.currentUser
        if(currentUser!=null){
            val token=currentUser.uid
            sp.edit().putString(TOKEN,token).apply()
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
        }else{
            Toast.makeText(requireContext(),"Error",Toast.LENGTH_SHORT).show()
        }


    }

    private fun errorCheck(email:String,password:String):Boolean{
        if(email.isEmpty()||password.isEmpty()){
            Toast.makeText(requireContext(),"Fill the gaps",Toast.LENGTH_SHORT).show()
            return true
        }

        if(password.length<6){
            Toast.makeText(requireContext(),"Password is too short.",Toast.LENGTH_SHORT).show()
            return true
        }

        return false
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}