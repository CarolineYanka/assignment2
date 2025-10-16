package com.example.s8132684assignment2.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.s8132684assignment2.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModels()

    private lateinit var usernameEdit: EditText
    private lateinit var passwordEdit: EditText
    private lateinit var loginButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        usernameEdit = view.findViewById(R.id.usernameEdit)
        passwordEdit = view.findViewById(R.id.passwordEdit)
        loginButton = view.findViewById(R.id.loginButton)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginButton.setOnClickListener {
            val username = usernameEdit.text.toString()
            val password = passwordEdit.text.toString()
            val location = "footscray" // change based on your class

            viewModel.login(location, username, password)
        }

        viewModel.keypass.observe(viewLifecycleOwner) { keypass ->
            val action = LoginFragmentDirections
                .actionLoginFragmentToDashboardFragment(keypass)
            findNavController().navigate(action)

        }

        viewModel.error.observe(viewLifecycleOwner) { errorMsg ->
            Toast.makeText(requireContext(), errorMsg, Toast.LENGTH_LONG).show()
        }
    }
}