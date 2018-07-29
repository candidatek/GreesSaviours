package com.example.shrihari.greessaviours;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHomeTwo extends Fragment  implements View.OnClickListener{


    public FragmentHomeTwo() {
        // Required empty public constructor
    }
    View v ;
    TextView mDisplayNumber ;
    String mDisplayNumberData ;
    private int val = 1;
    private Button mAttendButton , mMinusButton , mPlusButton ;
    private CheckBox mCheckBox ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_fragment_home_two, container, false);
        getValues();

        return v;
    }

    private void getValues() {
        mDisplayNumber = v.findViewById(R.id.display_number_t);
        mDisplayNumberData = mDisplayNumber.getText().toString().trim();
        mAttendButton = v.findViewById(R.id.attendbutton);
        mMinusButton = v.findViewById(R.id.minusbutton);
        mPlusButton = v.findViewById(R.id.plusbutton);
        mPlusButton.setOnClickListener(this);
        mAttendButton.setOnClickListener(this);
        mMinusButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == mAttendButton)
            attendButtonClicked(v);
        else if(v == mMinusButton)
            minusButtonClicked(v);
        else if (v == mPlusButton)
            plusButtonClicked(v);
    }

    private void attendButtonClicked(View v) {
        Toast.makeText(getActivity(), "Attend Button Clicekd "+mDisplayNumberData,Toast.LENGTH_SHORT).show();


    }

    private void minusButtonClicked(View v) {
        Toast.makeText(getActivity(), "Minus --- Button Clicekd "+mDisplayNumberData,Toast.LENGTH_SHORT).show();
        val = Integer.parseInt(mDisplayNumberData);
        mDisplayNumberData = Integer.toString(--val);
        mDisplayNumber.setText(mDisplayNumberData);

    }

    private void plusButtonClicked(View v) {
        Toast.makeText(getActivity(), "plus +++ Button Clicekd ",Toast.LENGTH_SHORT).show();
        val = Integer.parseInt(mDisplayNumberData);
        mDisplayNumberData = Integer.toString(++val);
        mDisplayNumber.setText(mDisplayNumberData);

    }
}
