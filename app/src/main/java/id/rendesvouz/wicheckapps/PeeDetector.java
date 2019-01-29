package id.rendesvouz.wicheckapps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import id.rendesvouz.wicheckapps.UrineColorPicker.MainCamera;

public class PeeDetector extends Fragment {
    View view;

    public PeeDetector() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_peedetector, container, false);

        ImageButton iBtnCamera ,iBtnColor;
        iBtnCamera = view.findViewById(R.id.iBtn_Camera);
        iBtnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), MainCamera.class);
                startActivity(intent);
            }
        });

        iBtnColor = view.findViewById(R.id.iBtn_ColorChart);

        iBtnColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(),SelectColor.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
