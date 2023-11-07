package social.spielapp.android.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import social.spielapp.android.R;
import social.spielapp.android.databinding.ActivityLoginBinding;
import social.spielapp.android.databinding.ActivityRegisterBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListeners();
    }

    private void setListeners() {
        binding.textNoAccount.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), RegisterActivity.class)));
    }
}