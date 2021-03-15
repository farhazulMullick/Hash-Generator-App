package com.example.hashgenerator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hashgenerator.databinding.FragmentSuccessBinding


class SuccessFragment : Fragment() {
    private var _binding: FragmentSuccessBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<SuccessFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSuccessBinding.inflate(inflater, container, false)

        binding.algoNameTextView.text = args.algoName
        binding.hashCodeTextView.text = args.hashCode

        binding.newBtn.setOnClickListener {
            navigateToHome()
        }

        return binding.root
    }

    private fun navigateToHome(){
        findNavController().navigate(R.id.action_successFragment_to_homeFragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}