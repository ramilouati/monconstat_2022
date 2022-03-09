package recycleviewc;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.monconstat.Accueil;
import com.example.monconstat.R;

import java.util.List;

import viewinfoR.Rv_infoP;

public class myAdapter extends RecyclerView.Adapter<myAdapter.ViewHolder> {
    private Context context;
    private List<Listitem> listitems;

    public myAdapter(Context context , List listitem) {
        this.context=context;
        this.listitems=listitem;
    }

    @NonNull
    @Override
    public myAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_contrat,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myAdapter.ViewHolder holder, int position) {
        Listitem item = listitems.get(position);
        holder.dateMesconstat.setText(item.getDatemesconstat());
        holder.mesRcode.setText(item.getCin2());
        holder.sts.setText(item.getSts());

    }

    @Override
    public int getItemCount() {
        return listitems.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView dateMesconstat;
        private TextView mesRcode;
        private TextView sts;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            dateMesconstat=itemView.findViewById(R.id.dateMesconstat);
            mesRcode=itemView.findViewById(R.id.mesRcode);
            sts=itemView.findViewById(R.id.textView139);
        }

        @Override
        public void onClick(View view) {
            int postion=getAdapterPosition();
            Listitem item = listitems.get(postion);
            //Toast.makeText(context, item.getMesRcode(), Toast.LENGTH_SHORT).show();
            Intent intent2 = new Intent(context, Rv_infoP.class);
            intent2.putExtra("code",item.getMesRcode());
            context.startActivity(intent2);


        }
    }
}
