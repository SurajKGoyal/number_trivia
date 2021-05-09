package com.heliushouse.numbertrivia.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.heliushouse.numbertrivia.R
import com.heliushouse.numbertrivia.databinding.FragmentNumberBinding
import com.heliushouse.numbertrivia.utils.NumberResource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NumberFragment : Fragment() {

    lateinit var binding: FragmentNumberBinding
    private val viewModel: NumberViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_number, container, false)
        binding.showProgress = false
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addObservers()
    }

    private fun addObservers() {
        viewModel.response.observe(requireActivity()){
            when(it){
                is NumberResource.Loading-> showDialog()
                is NumberResource.Success-> showTrivia(it.trivia)
                is NumberResource.Error-> showError(it.error)
            }
        }
    }

    private fun showDialog(){
        binding.showProgress = true
    }
    private fun hideDialog(){
        binding.showProgress = false
    }
    private fun showTrivia(trivia: String){
        hideDialog()
    }
    private fun showError(error: String){
        hideDialog()
        Snackbar.make(binding.root, error, Snackbar.LENGTH_SHORT).apply {
            animationMode = Snackbar.ANIMATION_MODE_SLIDE
            show()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = NumberFragment()
    }
}