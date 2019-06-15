package com.example.pintu.zupermart.Activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.FrameLayout;

import com.example.pintu.zupermart.R;
import com.example.pintu.zupermart.Fragment.SignInFragment;

public class RegisterActivity extends AppCompatActivity {
    private FrameLayout frameLayout;
    public static boolean onResetPasswordFragment =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        frameLayout = findViewById(R.id.register_frameLayout);


        setDefaultFragment(new SignInFragment());

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode ==event.KEYCODE_BACK){
            if (onResetPasswordFragment) {

                setFragment(new SignInFragment());

                onResetPasswordFragment = false;


                return false;
            }

        } else { }

        return super.onKeyDown(keyCode, event);
    }

    private void setDefaultFragment(Fragment fragment){

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(frameLayout.getId(),fragment);
        ft.commit();
    }
    private void setFragment(Fragment fragment){
        FragmentManager fragmentManager;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        ft.replace(frameLayout.getId(),fragment);
        ft.commit();
    }
}
