package social.spielapp.android.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import social.spielapp.android.databinding.ActivityRegisterBinding;
import social.spielapp.android.util.AuthManager;
import social.spielapp.android.util.ToastUtil;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListeners();
    }

    private void setListeners() {
        binding.textHaveAccount.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), LoginActivity.class)));
        binding.buttonRegister.setOnClickListener(v -> handleRegister());
    }

    private boolean checkValidity() {
        if (binding.inputUsername.getText().length() < 3 || binding.inputUsername.getText().length() > 20) {
            ToastUtil.showToast("Username must be between 3 and 20 characters long", getApplicationContext());
            return false;
        }

        if (!binding.inputPassword.getText().toString().equals(binding.inputConfirmPassword.getText().toString())) {
            ToastUtil.showToast("Passwords do not match", getApplicationContext());
            return false;
        }

        if (binding.inputPassword.getText().length() < 8) {
            ToastUtil.showToast("Password must be longer than 8 characters", getApplicationContext());
            return false;
        }

        return true;
    }

    private void handleRegister() {
        if (!checkValidity()) return;
        final String uname = binding.inputUsername.getText().toString();
        final String passwd = binding.inputPassword.getText().toString();
        int status = AuthManager.register(uname, passwd);
        if (status == 0) {
            // TODO: go to the next activity
        } else if (status == 1) {
            ToastUtil.showToast("Username already exists", getApplicationContext());
        } else if (status == 2) {
            ToastUtil.showToast("Failed to connect to the server", getApplicationContext());
        } else {
            ToastUtil.showToast("Error", getApplicationContext());
        }
    }
}