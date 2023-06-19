package com.otarbakh.motogp


import android.view.View
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
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
        binding.btnNext.setOnClickListener {
            registerUser()
        }
    }


    private fun checkVisibility(){
        binding.etEmailImpl.doOnTextChanged { text, start, before, count ->
            binding.btnNext.visibility = View.VISIBLE
        }
        binding.btnNext.setOnClickListener {
            findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToRiderFragment())
        }
    }

    private fun registerUser() {
        val email = binding.etEmailImpl.text.toString()
        val password = binding.etPasswordImpl.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty()) {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    auth.createUserWithEmailAndPassword(email, password).await()
                    withContext(Dispatchers.Main) {
                        Toast.makeText(requireContext(),
                            "registered user: ${auth.currentUser?.email}",
                            Toast.LENGTH_SHORT)
                            .show()
                        findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToRiderFragment())
                    }
                } catch (e: Exception) {
                }
            }
        } else {
            Toast.makeText(requireContext(), "not correct e-mail format!", Toast.LENGTH_SHORT)
                .show()
        }
    }
}