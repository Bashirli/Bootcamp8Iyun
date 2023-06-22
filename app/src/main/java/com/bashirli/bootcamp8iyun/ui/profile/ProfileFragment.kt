package com.bashirli.bootcamp8iyun.ui.profile

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bashirli.bootcamp8iyun.R
import com.bashirli.bootcamp8iyun.databinding.FragmentFavoritesBinding
import com.bashirli.bootcamp8iyun.databinding.FragmentProfileBinding
import com.bashirli.bootcamp8iyun.util.Constants.SP_BASE
import com.bashirli.bootcamp8iyun.util.Constants.TOKEN

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding?=null
    private val binding get()=_binding!!

    private lateinit var sp:SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentProfileBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    private fun setup(){
        sp=requireActivity().getSharedPreferences(SP_BASE,Context.MODE_PRIVATE)
        checkUser()

    }


    private fun checkUser(){
        val token=sp.getString(TOKEN,null)

        if(token==null){
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToTwiceFragment())
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}