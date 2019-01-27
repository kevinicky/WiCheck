package id.rendesvouz.wicheckapps;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;

public class FragmentHome extends Fragment {
    View view;
    ImageButton iBtn_PeeDetector;
    FrameLayout FL_PeeDetector;
    public FragmentHome() {

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home,container,false);

        iBtn_PeeDetector = view.findViewById(R.id.iBtn_PeeDetector);
        FL_PeeDetector = view.findViewById(R.id.FL_PeeDetector);

        iBtn_PeeDetector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment PeeDetector_Fragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.FL_PeeDetector);
                if(PeeDetector_Fragment != null){
                    getActivity().getSupportFragmentManager().beginTransaction().remove(PeeDetector_Fragment).commit();
                    FL_PeeDetector.setVisibility(View.GONE);
                }
                else{
                    FL_PeeDetector.setVisibility(View.VISIBLE);
                    getActivity().getSupportFragmentManager().beginTransaction().add(R.id.FL_PeeDetector, new PeeDetector()).commit();
                }
            }
        });

        return view;
    }
}