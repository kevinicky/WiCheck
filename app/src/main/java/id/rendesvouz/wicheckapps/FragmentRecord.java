package id.rendesvouz.wicheckapps;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

import id.rendesvouz.wicheckapps.Adapter.ResultAdapter;
import id.rendesvouz.wicheckapps.Model.Results;

public class FragmentRecord extends Fragment {
    View view;

    RecyclerView rvResult;
    ArrayList<Results> listResult;
    Button btnStart;
    public FragmentRecord() {

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_record,container,false);
        listResult = new ArrayList<>();

        rvResult = (RecyclerView) view.findViewById(R.id.rv_Result);
        rvResult.setLayoutManager(new LinearLayoutManager(getActivity()));

        final ResultAdapter resultAdapter = new ResultAdapter(getActivity(),listResult);
        rvResult.setAdapter(resultAdapter);
        LinearLayout LayoutNoData = view.findViewById(R.id.layoutNoData);

        LayoutNoData.setVisibility(View.GONE);
        rvResult.setVisibility(View.GONE);

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getActivity().getApplicationContext());
        Cursor cursor = databaseAccess.ViewResult();

        btnStart = view.findViewById(R.id.btnStartTest);
        if(cursor.getCount()==0){
            LayoutNoData.setVisibility(View.VISIBLE);
            btnStart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(),MainActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            });
        }
        else{
            rvResult.setVisibility(View.VISIBLE);
            while (cursor.moveToNext()){
                Results result = new Results();
                result.setR_Result(cursor.getString(0));
                result.setG_Result(cursor.getString(1));
                result.setB_Result(cursor.getString(2));
                result.setTime(cursor.getString(3));
                result.setStatus(cursor.getString(4));
                listResult.add(result);
            }
            resultAdapter.notifyDataSetChanged();
        }

        return view;
    }
}
