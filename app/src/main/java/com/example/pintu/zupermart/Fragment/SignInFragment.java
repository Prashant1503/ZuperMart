package com.example.pintu.zupermart.Fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pintu.zupermart.Activity.MainActivity;
import com.example.pintu.zupermart.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

import static com.example.pintu.zupermart.Activity.RegisterActivity.onResetPasswordFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignInFragment extends Fragment {


    public SignInFragment() {
        // Required empty public constructor
    }
    public EditText loginEmailEt,loginPasswordEt;
    public TextView forgotPasswordTv,SignUpTv;
    public Button signInBtn;
    private ImageButton closeBtn;

    private FirebaseAuth firebaseAuth;

    public FrameLayout parentFrameLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_sign_in,container,false);

        InitViews(view);




        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SignUpTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setFragment(new SignUpFragment());
            }
        });
        loginEmailEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkFields();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        loginPasswordEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkFields();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkEmailAndPassword();

            }
        });

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mainIntent();

            }
        });

        forgotPasswordTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onResetPasswordFragment = true;

                setFragment(new ResetPasswordFragment());
            }
        });

    }



    public void setFragment(Fragment fragment){

        FragmentTransaction ft = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();

        ft.replace(parentFrameLayout.getId(),fragment);
        ft.commit();

    }

    private void InitViews(View view){

        loginEmailEt = view.findViewById(R.id.login_emailId);
        loginPasswordEt = view.findViewById(R.id.login_password);

        forgotPasswordTv = view.findViewById(R.id.forgot_password);
        SignUpTv = view.findViewById(R.id.dontHaveAnAccountTv);
        signInBtn = view.findViewById(R.id.sign_in_btn);

        closeBtn = view.findViewById(R.id.close_btn);

        firebaseAuth = firebaseAuth.getInstance();


        parentFrameLayout =getActivity().findViewById(R.id.register_frameLayout);


    }

    private void checkFields(){

        if (!TextUtils.isEmpty(loginEmailEt.getText().toString())){
            if (!TextUtils.isEmpty(loginPasswordEt.getText().toString())){

                signInBtn.setEnabled(true);


            } else {signInBtn.setEnabled(false); }


        } else {signInBtn.setEnabled(false);
        }

    }

    private void checkEmailAndPassword(){

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";


        if (loginEmailEt.getText().toString().matches(emailPattern)){
            if (loginPasswordEt.getText().toString().length() >=8){



                firebaseAuth.signInWithEmailAndPassword(loginEmailEt.getText().toString(),loginPasswordEt.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()){

                                    Toast.makeText(getActivity(),"Login Successfully",Toast.LENGTH_SHORT).show();

                                    String action;
                                    Intent mainIntent = new Intent(getActivity(),MainActivity.class);
                                    startActivity(mainIntent);
                                    getActivity().finish();


                                } else {
                                    signInBtn.setEnabled(false);
                                    Toast.makeText(getActivity(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();}
                            }
                        });


            } else {
                Toast.makeText(getActivity(),"Incorrect Email",Toast.LENGTH_SHORT).show();
            }


        } else { Toast.makeText(getActivity(),"Incorrect Email",Toast.LENGTH_SHORT).show(); }
    }
    private void mainIntent(){

        Intent mainIntent = new Intent(getActivity(),MainActivity.class);
        startActivity(mainIntent);
        getActivity().finish();

    }

}
