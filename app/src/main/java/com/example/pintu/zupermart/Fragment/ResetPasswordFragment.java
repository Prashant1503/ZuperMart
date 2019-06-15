package com.example.pintu.zupermart.Fragment;


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
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pintu.zupermart.Activity.MainActivity;
import com.example.pintu.zupermart.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResetPasswordFragment extends Fragment {




    public ResetPasswordFragment() {
        // Required empty public constructor
    }

    private  EditText registeredEmailEdt;
    private  Button resetPaswordBtn;
    private  TextView goBackTv;
    private FrameLayout parentFrameLayout;
    private FirebaseAuth firebaseAuth;

//    Forgot password...


    private ViewGroup emailIconContainer;
    private ImageView emailIcon;
    private TextView emailIconText;
    private ProgressBar progressBar;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_reset_password,container,false);

        registeredEmailEdt = view.findViewById(R.id.reset_password_edt);
        resetPaswordBtn = view.findViewById(R.id.reset_password_btn);
        goBackTv = view.findViewById(R.id.go_back_tv);

        emailIconContainer = view.findViewById(R.id.ForgotPassword_email_icon_container);
        emailIconText = view.findViewById(R.id.forgot_password_email_icon_text);
        progressBar = view.findViewById(R.id.forgot_password_progressBar);
        emailIcon = view.findViewById(R.id.red_email_icon);

        parentFrameLayout = getActivity().findViewById(R.id.register_frameLayout);
        firebaseAuth = firebaseAuth.getInstance();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        registeredEmailEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkField();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        goBackTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setFragment(new SignInFragment());
            }
        });

        resetPaswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TransitionManager.beginDelayedTransition(emailIconContainer);
                emailIconText.setVisibility(View.GONE);

                TransitionManager.beginDelayedTransition(emailIconContainer);
                emailIcon.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.VISIBLE);



                resetPaswordBtn.setEnabled(false);

                firebaseAuth.sendPasswordResetEmail(registeredEmailEdt.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if (task.isSuccessful()){

                                    ScaleAnimation scaleAnimation = new ScaleAnimation(1, 0, 1, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                                    scaleAnimation.setDuration(1000);
                                    scaleAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                                    scaleAnimation.setRepeatMode(Animation.REVERSE);
                                    scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {


                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {

                                        }
                                    });

                                    emailIcon.startAnimation(scaleAnimation);

                                    Toast.makeText(getActivity(),"Email Sent Successfully",Toast.LENGTH_SHORT).show();

                                    String action;
                                    Intent mainIntent = new Intent(getActivity(),MainActivity.class);
                                    startActivity(mainIntent);
                                    getActivity().finish();


                                } else {

                                    String error = task.getException().getMessage();

                                    emailIconText.setText(error);
                                    emailIconText.setTextColor(getResources().getColor(R.color.btnRed));
                                    TransitionManager.beginDelayedTransition(emailIconContainer);

                                    emailIconText.setVisibility(View.VISIBLE);

                                }
                                    progressBar.setVisibility(View.GONE);
                                    resetPaswordBtn.setEnabled(true);

                            }
                        });


            }
        });





    }
    private void checkField(){

        if (!TextUtils.isEmpty(registeredEmailEdt.getText().toString())){

            resetPaswordBtn.setEnabled(true);

        } else {resetPaswordBtn.setEnabled(false);
        }
    }
    private void setFragment(Fragment fragment){
        FragmentManager fragmentManager;
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();

        ft.replace(parentFrameLayout.getId(),fragment);
        ft.commit();
    }


    }


