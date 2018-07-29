package com.example.shrihari.greessaviours;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class AdminFragment extends Fragment {

    View v ;
    TextView tv ;
    EditText mEditText ;
    String mPassword ;
    Button mButton ;

    public AdminFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         v = inflater.inflate(R.layout.fragment_admin, container, false);
         getValues();
         buttonClicked();


         return v;
    }

    private void buttonClicked() {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPassword = mEditText.getText().toString().trim();
                Log.d("chk","mPassword :"+mPassword);
                if(mPassword.equals("newadmin")){
                    Intent intent = new Intent(getActivity() , adminDetails.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(getActivity(),"Wrong Password" , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getValues() {
        mEditText = v.findViewById(R.id.adminedittextpass);
        mButton = v.findViewById(R.id.buttonadminlogin);
    }

}
