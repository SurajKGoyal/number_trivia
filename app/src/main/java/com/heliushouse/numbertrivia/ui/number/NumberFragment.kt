package com.heliushouse.numbertrivia.ui.number

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.heliushouse.numbertrivia.R
import com.heliushouse.numbertrivia.databinding.FragmentNumberBinding
import com.heliushouse.numbertrivia.ui.dialog.NumberTypeSelection
import com.heliushouse.numbertrivia.utils.NumberResource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NumberFragment : Fragment() {

    lateinit var binding: FragmentNumberBinding
    private val viewModel: NumberViewModel by  activityViewModels()

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
        binding.showResult = false
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addObservers()
    }

    private fun addObservers() {
        binding.viewModel = viewModel
        viewModel.response.observe(requireActivity()) {
            when (it) {
                is NumberResource.Loading -> showDialog()
                is NumberResource.Success -> showTrivia(it.trivia)
                is NumberResource.Error -> showError(it.error)
            }
        }

        viewModel.typeString.observe(requireActivity()) {
            binding.type.text = it
        }

        viewModel.showSnackMessage.observe(requireActivity()) {
            it.getContentIfNotHandled()?.let { message ->
                showError(message)
            }
        }

        viewModel.reset.observe(requireActivity()) {
            it.getContentIfNotHandled()?.let { shouldReset ->
                if(shouldReset){
                    binding.showResult = false
                    binding.number.setText("")
                }
            }
        }

        binding.typeField.setOnClickListener {
            openBottomSheet()
        }
    }

    private fun showDialog() {
        binding.showProgress = true
    }

    private fun hideDialog() {
        binding.showProgress = false
    }

    private fun showTrivia(trivia: String) {
        hideDialog()
        binding.showResult = true
        binding.trivia.text = trivia
    }

    private fun showError(error: String) {
        hideDialog()
        Snackbar.make(binding.root, error, Snackbar.LENGTH_SHORT).apply {
            animationMode = Snackbar.ANIMATION_MODE_SLIDE
            show()
        }
    }

    private fun openBottomSheet(){
        val numberTypeSelection = NumberTypeSelection()
        numberTypeSelection.show(childFragmentManager, "trivia_type")
    }

    companion object {
        @JvmStatic
        fun newInstance() = NumberFragment()
    }
}