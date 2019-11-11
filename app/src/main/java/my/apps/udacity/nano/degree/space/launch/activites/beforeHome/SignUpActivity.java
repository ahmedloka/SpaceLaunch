package my.apps.udacity.nano.degree.space.launch.activites.beforeHome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import my.apps.udacity.nano.degree.space.launch.R;
import my.apps.udacity.nano.degree.space.launch.activites.home.HomeActivity;
import my.apps.udacity.nano.degree.space.launch.utilites.CairoBoldButton;
import my.apps.udacity.nano.degree.space.launch.utilites.CairoRegularTextView;
import my.apps.udacity.nano.degree.space.launch.utilites.Constant;
import my.apps.udacity.nano.degree.space.launch.utilites.Validation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ProgressBar;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private ProgressBar progressBar;
    private TextInputEditText etName;
    private TextInputEditText etMail;
    private TextInputEditText etPassword;
    private TextInputEditText etConfirmPassword;
    private CairoBoldButton btnRegister;
    private CairoRegularTextView txtLogin;

    private FirebaseAuth mFireBaseAuth;
    private FirebaseDatabase mFireBaseDB;
    private DatabaseReference mDBReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initViews();
    }

    private void initViews() {

        etName = findViewById(R.id.et_name);
        etMail = findViewById(R.id.et_mail);
        etPassword = findViewById(R.id.et_password);
        etConfirmPassword = findViewById(R.id.et_confirm_password);
        btnRegister = findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(this);

        txtLogin = findViewById(R.id.txt_login);
        txtLogin.setOnClickListener(v -> {
            startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
            Animatoo.animateSwipeRight(SignUpActivity.this);
        });
        progressBar = findViewById(R.id.progress_bar);


        mFireBaseAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
                if (Validation.isConnected(this)) {
                    registerUser();
                } else {
                    Constant.showErrorDialog(this, getString(R.string.pls_check_connection));
                }
                break;
        }
    }

    private void registerUser() {

        setUpProgressVisibility(true, View.VISIBLE);

        final String userName = etName.getText().toString().trim();
        final String email = etMail.getText().toString().trim();
        final String password = etPassword.getText().toString().trim();
        final String confirmPassword = etConfirmPassword.getText().toString().trim();

        if (setUpEditTexts(userName, email, password, confirmPassword)) return;

        mFireBaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            setUpProgressVisibility(true, View.GONE);
                            Constant.clearStackIntent(SignUpActivity.this, HomeActivity.class);
                            Animatoo.animateSplit(SignUpActivity.this);
                            finish();
                        } else {
                            setUpProgressVisibility(true, View.GONE);
                            Snackbar.make(etMail, R.string.error_register, Snackbar.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private boolean setUpEditTexts(String userName, String email, String password, String confirmPassword) {

        if (userName.isEmpty()) {
            etName.setError(getString(R.string.username_error));
            etName.requestFocus();
            return true;
        }

        if (email.isEmpty()) {
            etMail.setError(getString(R.string.enter_email_empty));
            etMail.requestFocus();
            return true;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etMail.setError(getString(R.string.enter_email_error));
            etMail.requestFocus();
            return true;
        }

        if (password.isEmpty()) {
            etPassword.setError(getString(R.string.password_error_register));
            etPassword.requestFocus();
            return true;
        }

        if (password.length() < 8) {
            etPassword.setError(getString(R.string.error_password_length_min));
            etPassword.requestFocus();
            return true;
        }

        if (password.length() > 24) {
            etPassword.setError(getString(R.string.error_password_length_max));
            etPassword.requestFocus();
            return true;
        }

        if (confirmPassword.isEmpty()) {
            etConfirmPassword.setError(getString(R.string.confirm_password_error));
            etConfirmPassword.requestFocus();
            return true;
        }

        if (!password.equals(confirmPassword)) {
            etConfirmPassword.setError(getString(R.string.confirm_password_did_not_match));
            etConfirmPassword.requestFocus();
            return true;
        }

        setUpProgressVisibility(false, View.VISIBLE);
        return false;
    }

    private void setUpProgressVisibility(boolean b, int visibility) {
        progressBar.setVisibility(visibility);


        etConfirmPassword.setEnabled(b);
        etMail.setEnabled(b);
        etName.setEnabled(b);
        etConfirmPassword.setEnabled(b);
        btnRegister.setEnabled(b);
        txtLogin.setEnabled(false);

    }
}
