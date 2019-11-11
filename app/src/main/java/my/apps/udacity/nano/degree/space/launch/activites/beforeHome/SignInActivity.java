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
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class SignInActivity extends AppCompatActivity {

    private TextInputEditText etMail;
    private TextInputEditText etPassword;
    private CairoBoldButton btnLogin;
    private CairoRegularTextView txtRegister;
    private ProgressBar progressBar;
    private FirebaseAuth mFireBaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        intiViews();

    }

    private void intiViews() {
        mFireBaseAuth = FirebaseAuth.getInstance();

        etMail = findViewById(R.id.et_mail);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        txtRegister = findViewById(R.id.txt_register);
        txtRegister.setOnClickListener(v -> {
            startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
            Animatoo.animateSwipeLeft(SignInActivity.this);
        });
        progressBar = findViewById(R.id.progress_bar);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Validation.isConnected(SignInActivity.this)) {
                    logIn();
                } else {
                    Constant.showErrorDialog(SignInActivity.this, getString(R.string.pls_check_connection));

                }
            }
        });

    }

    private void logIn() {
        String emailOrPhoneNumber = etMail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (emailOrPhoneNumber.isEmpty()) {
            etMail.setError(getString(R.string.error_email_or_phone_empty));
            etMail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            etPassword.setError(getString(R.string.password_error_register));
            etPassword.requestFocus();
            return;
        }

        setUpProgressVisibility(false, View.VISIBLE);

        mFireBaseAuth.signInWithEmailAndPassword(emailOrPhoneNumber, password)
                .addOnCompleteListener(Objects.requireNonNull(this), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        setUpProgressVisibility(true, View.INVISIBLE);
                        if (task.isSuccessful()) {
                            Constant.clearStackIntent(SignInActivity.this,HomeActivity.class);
                            Animatoo.animateSplit(SignInActivity.this);
                        } else {
                            Snackbar.make(etMail, getString(R.string.login_error), Snackbar.LENGTH_SHORT).show();
                        }
                    }
                });


    }


    private void setUpProgressVisibility(boolean b, int visibility) {
        progressBar.setVisibility(visibility);
    }


}
