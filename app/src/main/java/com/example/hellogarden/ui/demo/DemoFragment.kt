package com.example.hellogarden.ui.demo

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.animation.AnimationUtils
import com.example.hellogarden.R
import com.example.hellogarden.databinding.CustomDialogBinding
import com.example.hellogarden.databinding.FragmentDemoBinding

class DemoFragment : Fragment() {

    // Step 1
    var dialog: Dialog? = null

    private var _binding: FragmentDemoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDemoBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.demo.setOnClickListener {
            customDialog()
        }

    }

    // Step 2 : inside onCreateView : dialog box using view binding inflate the xml
    private fun customDialog() {
        // Clear previous dialog box
        if (dialog != null) {
            dialog!!.dismiss()
        }

        dialog = Dialog(requireContext())
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val bind: CustomDialogBinding = CustomDialogBinding.inflate(layoutInflater)
        dialog!!.setContentView(bind.root)
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog!!.setCancelable(false)

        bind.btClose.setOnClickListener {
            dialog!!.dismiss()
        }

        // loading the animation of rotate_clockwise.xml file into a variable
        val clkRotate = AnimationUtils.loadAnimation(requireContext(), R.anim.rotate_clockwise)
        // assigning that animation to the image and start animation
        bind.image.startAnimation(clkRotate)

        // show dialog
        dialog!!.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        // Step 3 : Clear or close dialog box before leave this fragment or activity.
        if(dialog != null){
            dialog!!.dismiss()
        }

        _binding = null
    }
}