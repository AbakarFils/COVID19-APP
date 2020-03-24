package app.sante.covid19_test.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import app.sante.covid19_test.R;
import app.sante.covid19_test.entity.ReponsesPossible;


public class ReponsesPossibleAdapter extends RecyclerView.Adapter<ReponsesPossibleAdapter.RecyclerViewHolder> {

    private Context context;
    private ArrayList<ReponsesPossible> reponsesPossibleArrayList;
    private IReponsesPossibleItemClickListener IReponsesPossibleItemClickListener;


    public ReponsesPossibleAdapter(ArrayList<ReponsesPossible> reponsesPossibles, Context context, IReponsesPossibleItemClickListener IReponsesPossibleItemClickListener) {
        this.context = context;
        this.reponsesPossibleArrayList = reponsesPossibles;
        this.IReponsesPossibleItemClickListener = IReponsesPossibleItemClickListener;

    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;

        if (viewType == 1) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_choix, parent, false);
        } else if (viewType == 2) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_choix_selected, parent, false);
        }

        return new RecyclerViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewHolder holder, final int position) {

        if (!reponsesPossibleArrayList.isEmpty()) {
            final ReponsesPossible reponse = reponsesPossibleArrayList.get(position);

            if (reponse != null) {
                boolean selected = reponse.isSelected();
                String nom = reponse.getNom();
                holder.nom_response.setText(nom);
                /*if (selected) {
                    holder.block_reponse.setBackground(context.getResources().getDrawable(R.drawable.shape_round_selected));

                } else {

                    holder.block_reponse.setBackground(context.getResources().getDrawable(R.drawable.ic_choice_unselected));

                }*/

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        IReponsesPossibleItemClickListener.onReponseItemClickListener(position,reponse);
                    }
                });
                holder.block_reponse.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        IReponsesPossibleItemClickListener.onReponseItemClickListener(position,reponse);
                    }
                });

            }
        }

    }


    @Override
    public int getItemCount() {
        return reponsesPossibleArrayList.size();
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {
        Context context;
        TextView nom_response;
        View block_reponse;

        RecyclerViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            this.context = context;
            nom_response = itemView.findViewById(R.id.titre);
            block_reponse = itemView.findViewById(R.id.block_reponse);

        }
    }

    @Override
    public int getItemViewType(int position) {

        boolean isSelected = reponsesPossibleArrayList.get(position).isSelected();

        //  reponse selected
        if (isSelected) {
            return 2;
        } else {
            return 1;
        }


    }
}
