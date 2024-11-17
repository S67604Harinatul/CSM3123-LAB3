package com.example.fragmentexample;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import android.content.Context;
import android.util.Log;
import android.widget.Button;

public class MyFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        Button button = view.findViewById(R.id.send_data_button);
        button.setOnClickListener(v -> {
            Fragment2 fragment2 = new Fragment2();
            Bundle args = new Bundle();
            args.putString("message", "Hello from MyFragment");
            fragment2.setArguments(args);

            if (getActivity() != null) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, fragment2)
                        .addToBackStack(null) // Optional: Adds to back stack to allow back navigation
                        .commit();
            }
        });
        return view;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("MyFragment", "onAttach called");
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MyFragment", "onCreate called");
    }
    @Override
    public void onResume() {
        super.onResume();
        Log.d("MyFragment", "onResume called");
    }
}
