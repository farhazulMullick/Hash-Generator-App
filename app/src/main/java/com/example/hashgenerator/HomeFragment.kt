package com.example.hashgenerator

import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.hashgenerator.com.example.hashgenerator.HomeViewModel
import com.example.hashgenerator.databinding.FragmentHomeBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

class HomeFragment : Fragment() {
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel by viewModels<HomeViewModel>()
    private lateinit var algorithm: String

    override fun onResume() {
        super.onResume()
        val hashAlgorithms = resources.getStringArray(R.array.hash_algo_name)
        val adapter = ArrayAdapter(requireContext(), R.layout.drop_down_item, hashAlgorithms)
        binding.autoCompleteTextView.setAdapter(adapter)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater,container,false)

        setHasOptionsMenu(true)




        binding.generateButton.setOnClickListener {
            lifecycleScope.launch {
               checkInputTextField()
            }

        }
        return binding.root
    }

    private fun getHashData(): String{
        val plainText = binding.plainTextEditText.text.toString()
        return homeViewModel.getHash(plainText, algorithm)
    }

    private suspend fun applyAnimations(){
        binding.generateButton.isClickable = false
        binding.titleTextView.animate().alpha(0F).duration = 300L
        binding.textInputLayout.animate().translationXBy(1200f).duration = 400L
        binding.plainTextEditText.animate().translationXBy(-1200f).duration = 400L
        binding.generateButton.animate().alpha(0f).duration = 300L
        binding.autoCompleteTextView.animate().alpha(0f).duration = 400L

        delay(300)

        binding.successBackground.animate().alpha(1f).duration = 600L
        binding.successBackground.animate().scaleXBy(900f).duration = 800L
        binding.successBackground.animate().scaleYBy(900f).duration = 800L
        binding.successBackground.animate().rotationBy(720f).duration = 500L

        delay(400)

        binding.checkImageView.animate().alpha(1f).duration = 1000L

        delay(1500)

        navigateToSuccessFragment()
    }

    private suspend fun  checkInputTextField(){
        if(binding.plainTextEditText.text.isEmpty()){
            val message = "Fields can't be empty"
            showSnackBar(message)
        }
        else{
            applyAnimations()
        }
    }

    private fun navigateToSuccessFragment(){
        algorithm = binding.autoCompleteTextView.text.toString()
        val action = HomeFragmentDirections.actionHomeFragmentToSuccessFragment(algorithm, getHashData())
        findNavController().navigate(action)
    }

    private fun showSnackBar(message: String){
        Snackbar.make(
                binding.coordinatorLayout,
                message,
                Snackbar.LENGTH_SHORT
        )
                .setAction("Okay"){}
                .show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.clear_menu, menu)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}