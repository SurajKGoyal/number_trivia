package com.heliushouse.numbertrivia.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.heliushouse.numbertrivia.R
import com.heliushouse.numbertrivia.callbacks.RecyclerItemClickListener
import com.heliushouse.numbertrivia.databinding.TypeSelectionBottomSheetBinding
import com.heliushouse.numbertrivia.ui.number.NumberViewModel

class NumberTypeSelection : BottomSheetDialogFragment(), RecyclerItemClickListener {

    private val viewModel: NumberViewModel by viewModels()
    lateinit var binding: TypeSelectionBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.type_selection_bottom_sheet,
            container,
            false
        )
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun getTheme(): Int {
        return R.style.RoundedCornerBottomSheetDialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
        addObservers()
    }

    private fun setListener(){}

    private fun addObservers(){

    }

    override fun onClick(data: String) {
        viewModel.type.value = data
    }
}