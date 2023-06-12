package com.otarbakh.motogp.ui.register


import android.view.View
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.otarbakh.motogp.common.BaseFragment
import com.otarbakh.motogp.databinding.FragmentRegisterBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext


class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {


    private lateinit var auth: FirebaseAuth


    override fun viewCreated() {
        auth = Firebase.auth
        checkVisibility()

    }

    override fun listeners() {
        binding.buttonRegister.setOnClickListener {
            registerUser1()

        }
        goToLogin()
    }


    private fun checkVisibility() {
        binding.editTextEmail.doOnTextChanged { text, start, before, count ->
            binding.buttonRegister.visibility = View.VISIBLE
        }
        binding.buttonRegister.setOnClickListener {
            findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToMainFragment())
        }
    }

    private fun registerUser1() {
        val email = binding.editTextEmail.text.toString()
        val password = binding.editTextPassword.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty()) {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    auth.createUserWithEmailAndPassword(email, password).await()
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            requireContext(),
                            "registered user: ${auth.currentUser?.email}",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToMainFragment())
                    }
                } catch (e: Exception) {
                }
            }
        } else {
            Toast.makeText(requireContext(), "not correct e-mail format!", Toast.LENGTH_SHORT)
                .show()
        }
    }

    fun goToLogin() {
        binding.btnSingIn.setOnClickListener {
            findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
        }
    }


}