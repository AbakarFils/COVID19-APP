package app.sante.covid19_test;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;

import com.badoualy.stepperindicator.StepperIndicator;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import app.sante.covid19_test.adapter.IQuestionInterViewItemClickListener;
import app.sante.covid19_test.adapter.IReponsesPossibleItemClickListener;
import app.sante.covid19_test.adapter.QuestionAdapter;
import app.sante.covid19_test.adapter.ReponsesPossibleAdapter;
import app.sante.covid19_test.entity.Question;
import app.sante.covid19_test.entity.ReponsesPossible;

public class EvaluationActivity extends AppCompatActivity implements IQuestionInterViewItemClickListener {

    private RecyclerView recyclerView;

    ArrayList<Question> questionArrayList;
    private static int currentProgression;

    private StepperIndicator stepperIndicatorQuestion;

    private QuestionAdapter questionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation);
        bindViews();
        populate();



    }


    public void bindViews() {
        recyclerView = findViewById(R.id.recyclerview_question);
        stepperIndicatorQuestion = findViewById(R.id.stepperIndicatorQuestion);
        stepperIndicatorQuestion.showLabels(false);
    }

    public void populate() {


        stepperIndicatorQuestion.setStepCount(2);
        questionArrayList = new ArrayList<>();
        // Q1
        Question question1 = new Question();
        question1.setLibelle("Sexe ?");
        question1.setUid("q1");

        ReponsesPossible qr1 = new ReponsesPossible(1, 1, "r1", "Homme", false, false);
        ReponsesPossible qr2 = new ReponsesPossible(1, 1, "r2", "Femme", false, false);

        List<ReponsesPossible> reponsesPossibleList1 = new ArrayList<>();
        reponsesPossibleList1.add(qr1);
        reponsesPossibleList1.add(qr2);
        question1.setReponsesPossibles(reponsesPossibleList1);

        //Q2
        Question question2 = new Question();
        question1.setLibelle("Age ?");
        question1.setUid("q2");
        ReponsesPossible q2r1 = new ReponsesPossible(1, 1, "r1", "25-29", false, false);
        ReponsesPossible q2r2 = new ReponsesPossible(1, 1, "r2", "Plus que 30", false, false);
        ReponsesPossible q2r3 = new ReponsesPossible(1, 1, "r3", "15-19", false, false);
        ReponsesPossible q2r4 = new ReponsesPossible(1, 1, "r4", "20-24", false, false);

        List<ReponsesPossible> reponsesPossibleList2 = new ArrayList<>();
       reponsesPossibleList2.add(q2r1);
       reponsesPossibleList2.add(q2r2);
       reponsesPossibleList2.add(q2r3);
       reponsesPossibleList2.add(q2r4);
       question2.setReponsesPossibles(reponsesPossibleList2);



        //Q3
        Question question3 = new Question();
        question1.setLibelle("Age ?");
        question1.setUid("q3");
        ReponsesPossible q3r1 = new ReponsesPossible(1, 1, "r1", "Dyspné (noyade)", false, false);
        ReponsesPossible q3r2 = new ReponsesPossible(1, 1, "r2", "Hémoptysie (crasher du sang)", false, false);
        ReponsesPossible q3r3 = new ReponsesPossible(1, 1, "r3", "Douleur sur le côté", false, false);

        List<ReponsesPossible> reponsesPossibleList3 = new ArrayList<>();
        
        reponsesPossibleList3.add(q3r1);
        reponsesPossibleList3.add(q3r2);
        reponsesPossibleList3.add(q3r3);

        questionArrayList.add(question1);
        questionArrayList.add(question2);
        questionArrayList.add(question3);

        updateList(questionArrayList);

    }


    private void updateList(ArrayList<Question> questionArrayList) {
        if (questionArrayList != null && !questionArrayList.isEmpty() && questionArrayList.size() > 0) {
            questionAdapter = new QuestionAdapter(questionArrayList, EvaluationActivity.this, this);
            recyclerView.setNestedScrollingEnabled(false);
            recyclerView.setAdapter(questionAdapter);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(layoutManager);
            questionAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public void onQuestionSaisieReponse(Question questionnaireInterview, EditText edtResponse) {

    }

    @Override
    public void onUniqueReponseItemClick(int position, final Question question, final ReponsesPossible reponse, final Button button) {
        setImageIntoButton(button, reponse.isSelected());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reponse.setSelected(!reponse.isSelected());
                Date date = new Date(System.currentTimeMillis());
                questionAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onMultipleReponseItemClick(int position, Question question, final ReponsesPossible reponse, Button button) {

        setImageIntoButton(button, reponse.isSelected());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reponse.setSelected(!reponse.isSelected());
                questionAdapter.notifyDataSetChanged();
            }
        });
    }

    @SuppressLint("NewApi")
    private void setImageIntoButton(Button buttonView, boolean isSelected) {
        if (isSelected) {
            buttonView.setBackground(getResources().getDrawable(R.drawable.ic_choice_selected));
            buttonView.setTextColor(getResources().getColor(R.color.white));
        } else
            buttonView.setBackground(getResources().getDrawable(R.drawable.ic_choice_unselected));
            buttonView.setTextColor(getResources().getColor(R.color.app_color));
    }
}
