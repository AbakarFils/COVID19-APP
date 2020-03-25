package app.sante.covid19_test.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import app.sante.covid19_test.R;
import app.sante.covid19_test.entity.Question;
import app.sante.covid19_test.entity.Reponse;


public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.RecyclerViewHolder> {

    private Context context;
    private ArrayList<Question> questionArrayList;
    private IQuestionInterViewItemClickListener questionInterViewItemClickListener;
    private ReponsesPossibleAdapter reponsesPossibleAdapter;


    public QuestionAdapter(ArrayList<Question> questionArrayList, Context context, IQuestionInterViewItemClickListener iQuestionInterViewItemClickListener) {
        this.context = context;
        this.questionArrayList = questionArrayList;
        this.questionInterViewItemClickListener = iQuestionInterViewItemClickListener;

    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_question, parent, false);
        return new RecyclerViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewHolder holder, final int position) {

        if (!questionArrayList.isEmpty()) {

            final Question question = questionArrayList.get(position);
            if (question != null) {

                holder.libelle.setText(question.getLibelle());
                holder.numero.setText(question.getUid());

                if (question.getReponses() != null && !question.getReponses().isEmpty() && question.getReponses().size() > 0) {
                    for (Reponse reponse : question.getReponses()) {
                        Button btnResponse = new Button(context);
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                R.dimen.m70
                        );
                        params.setMargins(5, R.dimen.m10, 5, R.dimen.m10);
                        //params.setMarginStart(10);
                        //params.setMarginEnd(10);
                        btnResponse.setLayoutParams(params);
                        btnResponse.setAllCaps(false);
                        btnResponse.setPadding(10, 0, 10, 0);
                        btnResponse.setTextColor(context.getResources().getColor(R.color.app_color));
                        btnResponse.setTextSize(13);
                        btnResponse.setText(reponse.getNom());
                        holder.linearBtnResponse.addView(btnResponse);
                        questionInterViewItemClickListener.onMultipleReponseItemClick(position, question, reponse, btnResponse);

                    }
                }

            }
        }

    }

    @Override
    public int getItemCount() {
        return questionArrayList.size();
    }


    class RecyclerViewHolder extends RecyclerView.ViewHolder {
        Context context;
        TextView libelle, numero;
        View block_reponse;
        LinearLayout linearBtnResponse;


        RecyclerViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            this.context = context;
            libelle = itemView.findViewById(R.id.txt_current_question);
            numero = itemView.findViewById(R.id.numero);
            block_reponse = itemView.findViewById(R.id.block_reponse);
            linearBtnResponse = itemView.findViewById(R.id.linear_btn_response);

        }
    }

}
