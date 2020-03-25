package app.sante.covid19_test;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.badoualy.stepperindicator.StepperIndicator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import app.sante.covid19_test.adapter.IQuestionInterViewItemClickListener;
import app.sante.covid19_test.adapter.QuestionAdapter;
import app.sante.covid19_test.entity.Question;
import app.sante.covid19_test.entity.Reponse;

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
        final Question question1 = new Question();
        question1.setLibelle("Sexe ?");
        question1.setUid("q1");

        final  Reponse qr1 = new Reponse(1, 1, "r1", "Homme", false, false);
        final Reponse qr2 = new Reponse(1, 1, "r2", "Femme", false, false);

        List<Reponse> reponseList1 = new ArrayList<>();
        reponseList1.add(qr1);
        reponseList1.add(qr2);
        question1.setReponses(reponseList1);

        //Q2
        final Question question2 = new Question();
        question1.setLibelle("Age ?");
        question1.setUid("q2");
        Reponse q2r1 = new Reponse(1, 1, "r1", "25-29", false, false);
        Reponse q2r2 = new Reponse(1, 1, "r2", "Plus que 30", false, false);
        Reponse q2r3 = new Reponse(1, 1, "r3", "15-19", false, false);
        Reponse q2r4 = new Reponse(1, 1, "r4", "20-24", false, false);

        List<Reponse> reponseList2 = new ArrayList<>();
       reponseList2.add(q2r1);
       reponseList2.add(q2r2);
       reponseList2.add(q2r3);
       reponseList2.add(q2r4);
       question2.setReponses(reponseList2);



        //Q3
        final  Question question3 = new Question();
        question1.setLibelle(" Age ?");
        question1.setUid("q3");
        Reponse q3r1 = new Reponse(1, 1, "r1", "Dyspné (noyade)", false, false);
        Reponse q3r2 = new Reponse(1, 1, "r2", "Hémoptysie (crasher du sang)", false, false);
        Reponse q3r3 = new Reponse(1, 1, "r3", "Douleur sur le côté", false, false);

        List<Reponse> reponseList3 = new ArrayList<>();
        
        reponseList3.add(q3r1);
        reponseList3.add(q3r2);
        reponseList3.add(q3r3);

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
    public void onUniqueReponseItemClick(int position, final Question question, final Reponse reponse, final Button button) {
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
    public void onMultipleReponseItemClick(int position, Question question, final Reponse reponse, Button button) {

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
