package com.bashirli.bootcamp8iyun.ui.twice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bashirli.bootcamp8iyun.databinding.FragmentProfileBinding
import com.bashirli.bootcamp8iyun.databinding.FragmentTwiceBinding

class TwiceFragment : Fragment() {
    private var _binding: FragmentTwiceBinding?=null
    private val binding get()=_binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentTwiceBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSignIn.setOnClickListener {
            findNavController().navigate(TwiceFragmentDirections.actionTwiceFragmentToLoginFragment())
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}