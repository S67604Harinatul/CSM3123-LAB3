package com.example.fragmentexample;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class Fragment2 extends Fragment {
    private TextView textView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2, container, false);
        textView = view.findViewById(R.id.fragment2_text);

        // Retrieve the data passed in arguments
        if (getArguments() != null) {
            String message = getArguments().getString("message");
            if (message != null) {
                textView.setText(message);
            }
        }
        return view;
    }
    public void updateData(String message) {
        textView.setText(message);
    }
}
