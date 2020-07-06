package com.demo.receipt.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.demo.receipt.R
import com.demo.receipt.getPhotoURI
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    private val takePicture =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            success?.let {
                if (success) {
                    val action =
                        MainFragmentDirections.actionMainFragmentToReceiptDetailsFragment(viewModel.currentPhotoPath)
                    findNavController().navigate(action)
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addReceiptButton.setOnClickListener {
            getPhotoURI(requireContext())?.let {
                viewModel.currentPhotoPath = it
                takePicture.launch(it)
            }
        }
    }

}