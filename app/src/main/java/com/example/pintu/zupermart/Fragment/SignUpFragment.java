package com.example.pintu.zupermart.Fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {


    public SignUpFragment() {
        // Required empty public constructor
    }
    public EditText fullNameEt,EmailEt,PasswordEt,ConfirmPasswordEt;
    public TextView signInTv;
    public Button signUpBtn;
    public ImageButton closeBtn;

    public FrameLayout parentFrameLayout;
    ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;


   private String password;
   private static final String TAG = "Sign Up fragment";
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";

    private FirebaseFirestore firebaseFirestore;








    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_sign_up,container,false);
        InitViews(view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        signInTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setFragment(new SignInFragment());
            }
        });

       fullNameEt.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {

           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {

               chekFields();

           }

           @Override
           public void afterTextChanged(Editable s) {

           }
       });
       EmailEt.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {

           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {
               chekFields();

           }

           @Override
           public void afterTextChanged(Editable s) {

           }
       });
       PasswordEt.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {

           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {
               chekFields();

           }

           @Override
           public void afterTextChanged(Editable s) {

           }
       });
       ConfirmPasswordEt.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {

           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {
               chekFields();

           }

           @Override
           public void afterTextChanged(Editable s) {

           }
       });

       signUpBtn.setOnClickListener(new View.OnClickListener() {
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









    }


    private void setFragment(Fragment fragment){
        FragmentManager fragmentManager;
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();

        ft.replace(parentFrameLayout.getId(),fragment);
        ft.commit();
    }
    private void InitViews(View view){

        fullNameEt = view.findViewById(R.id.register_fullName);
        EmailEt = view.findViewById(R.id.register_emailId);
        PasswordEt = view.findViewById(R.id.register_password);
        ConfirmPasswordEt = view.findViewById(R.id.register_confirmPassword);
        signInTv = view.findViewById(R.id.already_have_anAccount);
        signUpBtn = view.findViewById(R.id.sign_up_btn);

        closeBtn = view.findViewById(R.id.close_btn);

        firebaseAuth = firebaseAuth.getInstance();

        parentFrameLayout =getActivity().findViewById(R.id.register_frameLayout);

        firebaseFirestore = FirebaseFirestore.getInstance();

        Context context;
        progressDialog = new ProgressDialog(getActivity());

    }
    private void chekFields(){

        if (!TextUtils.isEmpty(fullNameEt.getText().toString())){
            if (!TextUtils.isEmpty(EmailEt.getText().toString())){
                if (!TextUtils.isEmpty(PasswordEt.getText().toString()) && PasswordEt.length() >=8){
                    if (!TextUtils.isEmpty(ConfirmPasswordEt.getText().toString())){

                        signUpBtn.setEnabled(true);


                    } else { signUpBtn.setEnabled(false);}


                } else {signUpBtn.setEnabled(false); }

            } else {signUpBtn.setEnabled(false); }

        } else { signUpBtn.setEnabled(false); }

    }
    private void checkEmailAndPassword(){

        String email = EmailEt.getText().toString();

        if (EmailEt.getText().toString().matches(emailPattern)){
            if (PasswordEt.getText().toString().matches(ConfirmPasswordEt.getText().toString())){


                signUpBtn.setEnabled(false);

                progressDialog.setMessage("Registering user please wait");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);


                firebaseAuth.createUserWithEmailAndPassword(EmailEt.getText().toString(),PasswordEt.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                               if(task.isSuccessful()){

                                   Map<Object,String> userData = new HashMap<>();
                                   userData.put("full name",fullNameEt.getText().toString());

                                   firebaseFirestore.collection("Users").add(userData)
                                           .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                               @Override
                                               public void onComplete(@NonNull Task<DocumentReference> task) {

                                                   if (task.isSuccessful()){

                                                       Intent mainIntent = new Intent(getActivity(),MainActivity.class);
                                                       startActivity(mainIntent);
                                                       getActivity().finish();




                                                   } else {progressDialog.dismiss();
                                                       progressDialog.setCancelable(true);

                                                       Toast.makeText(getActivity(),task.getException().getMessage(),Toast.LENGTH_SHORT).show(); }

                                               }
                                           });
                                   String action;

                                   Toast.makeText(getActivity(),"User registered Successfully",Toast.LENGTH_SHORT).show();
                                   Intent mainIntent = new Intent(getActivity(),MainActivity.class);
                                   startActivity(mainIntent);
                                   getActivity().finish();

                               } else {

                                   progressDialog.dismiss();
                                   progressDialog.setCancelable(true);

                                   Toast.makeText(getActivity(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                               }
                            }
                        });
                progressDialog.dismiss();

            } else { ConfirmPasswordEt.setError("Password doesn't match"); }

        } else { EmailEt.setError("Invalid email");}


    }

    private void runProgressDialog(){

        Context context;
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Registering User");
        progressDialog.setMessage("Please wait...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        progressDialog.setCancelable(false);

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {

                    Thread.sleep(10000);

                }
                catch (Exception e){

                    e.printStackTrace();
                }
                progressDialog.dismiss();
            }

        }).start();
    }

    private void mainIntent(){

        Intent mainIntent = new Intent(getActivity(),MainActivity.class);
        startActivity(mainIntent);
        getActivity().finish();

    }



}
