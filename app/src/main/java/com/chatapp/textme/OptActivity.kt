package com.chatapp.textme

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.chatapp.textme.databinding.ActivityOptBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthProvider

class OptActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOptBinding
    private lateinit var mAuth : FirebaseAuth
    private lateinit var phoneAuth : FirebaseAuthProvider
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opt)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_opt)



        binding.apply {
            verifyButton.setOnClickListener {
                binding.apply {
                    verifyButton.setOnClickListener {
                        if (pinView.text.toString().trim().isEmpty()

                        ) {
                            Toast.makeText(
                                this@OptActivity,
                                "Invalid Phone Number", Toast.LENGTH_SHORT).show()
                        } else {
                        }

                    }
                }
            }
        }
    }
}
