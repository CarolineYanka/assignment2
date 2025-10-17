package com.example.s8132684assignment2.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.s8132684assignment2.R
import kotlinx.coroutines.launch
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val usernameEditText = view.findViewById<EditText>(R.id.usernameEdit)
        val passwordEditText = view.findViewById<EditText>(R.id.passwordEdit)
        val loginButton = view.findViewById<Button>(R.id.loginButton)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (username.isNotBlank() && password.isNotBlank()) {
                viewModel.login(username, password)
            } else {
                Toast.makeText(requireContext(), "Please enter both username and password", Toast.LENGTH_SHORT).show()
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.loginState.collect { state ->
                when (state) {
                    is LoginUiState.Loading -> {
                        progressBar.visibility = View.VISIBLE
                    }
                    is LoginUiState.Success -> {
                        progressBar.visibility = View.GONE
                        val keypass = state.keypass
                        val action = LoginFragmentDirections
                            .actionLoginFragmentToDashboardFragment(keypass)
                        findNavController().navigate(action)
                    }
                    is LoginUiState.Error -> {
                        progressBar.visibility = View.GONE
                        Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                    }
                    else -> Unit
                }
            }
        }
    }
}
