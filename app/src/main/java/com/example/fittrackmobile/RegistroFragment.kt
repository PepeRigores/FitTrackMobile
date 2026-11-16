package com.example.fittrackmobile

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fittrackmobile.databinding.FragmentRegistroBinding

class RegistroFragment : Fragment() {

    private var _binding: FragmentRegistroBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegistrar.setOnClickListener {
            if (validarFormulario()) {
                val nombre = binding.etNombre.text?.toString()?.trim().orEmpty()
                Toast.makeText(
                    requireContext(),
                    "Registro exitoso para $nombre",
                    Toast.LENGTH_SHORT
                ).show()
                findNavController().navigate(R.id.action_registroFragment_to_loginFragment)
            }
        }

        binding.btnGoogleSignUp.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "Iniciando registro con Google...",
                Toast.LENGTH_SHORT
            ).show()
            // Aquí se conecta con Credential Manager / Firebase Google Sign-In
        }

        binding.tvIrALogin.setOnClickListener {
            findNavController().navigate(R.id.action_registroFragment_to_loginFragment)
        }
    }

    private fun validarFormulario(): Boolean {
        var esValido = true

        val nombre = binding.etNombre.text?.toString()?.trim().orEmpty()
        val email = binding.etEmail.text?.toString()?.trim().orEmpty()
        val password = binding.etPassword.text?.toString()?.trim().orEmpty()
        val confirmPassword = binding.etConfirmarPassword.text?.toString()?.trim().orEmpty()

        if (nombre.isEmpty()) {
            binding.tilNombre.error = getString(R.string.error_nombre_requerido)
            esValido = false
        } else {
            binding.tilNombre.error = null
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tilEmail.error = getString(R.string.error_email_requerido)
            esValido = false
        } else {
            binding.tilEmail.error = null
        }

        if (password.isEmpty() || password.length < 6) {
            binding.tilPassword.error = getString(R.string.error_password_requerida)
            esValido = false
        } else {
            binding.tilPassword.error = null
        }

        if (confirmPassword != password) {
            binding.tilConfirmarPassword.error = getString(R.string.error_passwords_no_coinciden)
            esValido = false
        } else {
            binding.tilConfirmarPassword.error = null
        }

        return esValido
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}