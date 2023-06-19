package com.otarbakh.motogp.ui.login

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.otarbakh.motogp.common.BaseFragment
import com.otarbakh.motogp.databinding.FragmentLoginBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private lateinit var auth: FirebaseAuth

    override fun viewCreated() {


        auth = Firebase.auth

        checkLoggedInState()

        binding.etEmailImpl.doOnTextChanged { text, start, before, count ->
            binding.btnSignIn.visibility = View.VISIBLE
        }
    }

    override fun listeners() {
        binding.btnSignIn.setOnClickListener {
            loginWithUser()
        }

        binding.btnCreateAccount.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }

    }
    private fun loginWithUser(){
        val email = binding.etEmailImpl.text.toString()
        val password = binding.etPasswordImpl.text.toString()

        if(email.isNotEmpty() && password.isNotEmpty() && isValidEmail()){
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    auth.signInWithEmailAndPassword(email,password).await()
                    withContext(Dispatchers.Main){
                        checkLoggedInState()
                        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRiderFragment())
                    }
                }catch (e:Exception){
                    withContext(Dispatchers.Main){
                        Toast.makeText(requireContext(),"wrong", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun isValidEmail(): Boolean =
        android.util.Patterns.EMAIL_ADDRESS.matcher(binding?.etEmailImpl?.text.toString()).matches()


    private fun checkLoggedInState() {
        val user = auth.currentUser
        if (user == null) {
            Log.d("mcici","not logged in")
        } else {
            Log.d("mcici","logged in")
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRiderFragment())
        }
    }







}