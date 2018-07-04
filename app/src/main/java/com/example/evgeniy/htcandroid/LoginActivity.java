package com.example.evgeniy.htcandroid;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {


    private UserLoginTask mAuthTask = null;

    // UI references.
    private EditText mPhoneView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mPhoneView = findViewById(R.id.phone);
        mPhoneView.setSelection(mPhoneView.getText().length());
        // Set up the login form.
        mPhoneView.addTextChangedListener(new TextWatcher() {
            int length_before = 0;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                length_before = charSequence.length();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() < 5 && !s.toString().equals("+7 (")) {
                    s.clear();
                }
                if (s.length() == 0) {
                    s.append("+7 (");
                }
                if (s.length() > 4 && !s.toString().substring(0, 4).equals("+7 (")) {
                    s.clear();
                }

                if (length_before < s.length()) {
                    if (s.length() == 7) {
                        s.append(") ");
                    }
                    if (s.length() == 8) {
                        s.insert(7, ") ");
                    }
                    if (s.length() == 12 || s.length() == 15) {
                        s.append("-");
                    }
                    if (s.length() == 13 && s.charAt(12) != '-') {
                        s.insert(12, "-");
                    }
                    if (s.length() == 16 && s.charAt(15) != '-') {
                        s.insert(15, "-");
                    }
                } else {
                    if (s.length() == 16) {
                        s.delete(15, 16);
                    }
                    if (s.length() == 13) {
                        s.delete(12, 13);
                    }
                    if (s.length() == 9) {
                        s.delete(7, 9);
                    }
                }
            }
        });
        mPasswordView = findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }


    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mPhoneView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String phone = mPhoneView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password) || isPasswordShort(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password_short));
            focusView = mPasswordView;
            cancel = true;
        }
        if (!cancel && isPasswordLong(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password_long));
            focusView = mPasswordView;
            cancel = true;
        }
        if (!cancel && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid phone
        if (TextUtils.isEmpty(phone)) {
            mPhoneView.setError(getString(R.string.error_field_required));
            focusView = mPhoneView;
            cancel = true;
        } else {
            if (!isPhoneValid(phone)) {
                mPhoneView.setError(getString(R.string.error_invalid_phone));
                focusView = mPhoneView;
                cancel = true;
            }
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            showProgress(true);
            mAuthTask = new UserLoginTask(password, password);
            mAuthTask.execute((Void) null);
        }
    }


    private boolean isPasswordShort(String password) {
        return password.length() < 6;
    }

    private boolean isPasswordLong(String password) {
        return password.length() > 16;
    }

    private boolean isPasswordValid(String password) {
        return (hasLower(password) && hasUpper(password) && hasDigits(password));
    }

    private boolean hasDigits(String password) {
        for (int i = 0; i < password.length(); i++) {
            if (Character.isDigit(password.charAt(i)))
                return true;
        }
        return false;
    }

    private boolean hasUpper(String password) {
        return !password.equals(password.toLowerCase());
    }

    private boolean hasLower(String password) {
        return !password.equals(password.toUpperCase());
    }

    private boolean isPhoneValid(String phone) {
        phone = phone.replaceAll("[\\+\\-\\(\\) ]", "");
        if (phone.length() == 11) {
            if (hasOnlyDigits(phone)) {
                return true;
            }
        }
        return false;
    }


    private boolean hasOnlyDigits(String phone) {
        for (int i = 0; i < phone.length(); i++) {
            if (!Character.isDigit(phone.charAt(i)))
                return false;
        }
        return true;
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

        mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            }
        });

        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        mProgressView.animate().setDuration(shortAnimTime).alpha(
                show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        });
    }


    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (success) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
}

