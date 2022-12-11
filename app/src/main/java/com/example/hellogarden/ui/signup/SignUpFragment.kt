package com.example.hellogarden.ui.signup

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.hellogarden.MainViewModel
import com.example.hellogarden.R
import com.example.hellogarden.data.models.Member
import com.example.hellogarden.databinding.FragmentSignUpBinding


/**
 * SignUpFragment enthält das UI um einen neuen User zu registrieren
 */
class SignUpFragment : Fragment() {

    /**
     * ViewModel wird verknüpft
     */

    private val viewModel: MainViewModel by activityViewModels()

    private lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)

        return binding.root
    }

    /**
     * Layout wird vorbereitet zum Erstellen aufgeblasen
     */

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signinBtn.setOnClickListener {
            findNavController()
                .navigate(SignUpFragmentDirections.actionSignUpFragmentToLoginFragment())
        }

        /**
         * signup Button wird mit SignUp Methode versehen und diese wird bei jedem SignUp
         * durchgeführt
         */

        binding.signupBtn.setOnClickListener {
            signUp()
        }

        /**
         * Viewmodel überwacht mit dem Observe den Toast und wirft diesen bei Bedarf
         */

        viewModel.toast.observe(
            viewLifecycleOwner,
            Observer {
                if (it != null) {
//                    Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                    AlertDialog.Builder(requireContext())
                        .setMessage(it)
                        .create()
                        .show()
                }
            }
        )

        /**
         * Viewmodel überwacht mit dem Observe den currentUser und wirft diesen bei Bedarf
         */

        viewModel.currentUser.observe(
            viewLifecycleOwner,
            Observer {
                if (it != null) {
                    findNavController().navigate(R.id.mainFragment)
                }
            }
        )
    }

    /**
     * Funktion für den SignUp Prozess
     */


    private fun signUp() {
        val email = binding.signupMail.text.toString()
        val password = binding.signupPassword.text.toString()
        val name = binding.signupNickname.text.toString()

        /**
         * NullCheck wird durchgeführt
         * und Objekt erstellt
         */


        if (!email.isNullOrEmpty() && !password.isNullOrEmpty()) {
            val newMember = Member(
                name = name,

                )

            /**
             * viewmodel führt signUp() aus und übergibt email , password
             */
            viewModel.signUp(email, password, newMember)
        }
    }
}
