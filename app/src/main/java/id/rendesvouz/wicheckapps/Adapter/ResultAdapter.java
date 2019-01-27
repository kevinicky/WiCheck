package id.rendesvouz.wicheckapps.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import id.rendesvouz.wicheckapps.Model.Results;
import id.rendesvouz.wicheckapps.R;


public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder>{

    Context context;
    ArrayList<Results> listResult;

    public ResultAdapter(Context context, ArrayList<Results> listResult){
        this.context = context;
        this.listResult = listResult;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_result,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bind(listResult.get(i));
    }

    @Override
    public int getItemCount() {
        return listResult.size();
    }

    public void setData(ArrayList<Results> listPeople){
        this.listResult=listPeople;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
    TextView tvStatus,tvTime,tvColor;
        public ViewHolder(View itemView){
            super(itemView);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvColor = itemView.findViewById(R.id.tvColor);
        }
        public void bind(Results result){
            tvStatus.setText(result.getStatus());
            tvTime.setText(result.getTime());
            String R = result.getR_Result();
            String G = result.getG_Result();
            String B = result.getB_Result();

            tvColor.setBackgroundColor(Color.rgb(Integer.parseInt(R),Integer.parseInt(G),Integer.parseInt(B)));


        }
    }
}

