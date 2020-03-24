package app.sante.covid19_test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.badoualy.stepperindicator.StepperIndicator;

import java.util.ArrayList;
import java.util.List;

import app.sante.covid19_test.adapter.IReponsesPossibleItemClickListener;
import app.sante.covid19_test.adapter.ReponsesPossibleAdapter;
import app.sante.covid19_test.entity.Question;
import app.sante.covid19_test.entity.ReponsesPossible;

public class MainActivity extends AppCompatActivity implements IReponsesPossibleItemClickListener {
    private RecyclerView recyclerView;
    private Button btnSuivant;
    private TextView txt_current_question, numero;

    private static int currentProgression;
    private static int index = 0;

    private StepperIndicator stepperIndicatorQuestion;

    ArrayList<ReponsesPossible> reponsesPossibleArrayList;
    ArrayList<Question> questionArrayList;
    private ReponsesPossibleAdapter reponsesPossibleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();

        btnSuivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index++;
                if (index > questionArrayList.size()) {
                     Intent intent = new Intent(MainActivity.this, EvaluationActivity.class);
                startActivity(intent);

                }
                if (currentProgression >= 2) {
                    Toast.makeText(MainActivity.this, "Job ajouté à votre favoris avec succès"+ questionArrayList.get(index).getLibelle(), Toast.LENGTH_SHORT).show();
                    txt_current_question.setText("");
                    txt_current_question.setText(questionArrayList.get(index).getLibelle());
                    updateList((ArrayList<ReponsesPossible>) questionArrayList.get(index).getReponsesPossibles());
                    numero.setText(" " );
                    numero.setText("" + index + 1);
                }

                stepperIndicatorQuestion.setCurrentStep(index + 1);
                currentProgression++;

            }
        });
        populateResponse();
    }

    public void bindViews() {
        currentProgression = 2;
        txt_current_question = findViewById(R.id.txt_current_question);
        numero = findViewById(R.id.numero);
        recyclerView = findViewById(R.id.recyclerview_reponses);
        btnSuivant = findViewById(R.id.btn_suivant);

        stepperIndicatorQuestion = findViewById(R.id.stepperIndicatorQuestion);
        stepperIndicatorQuestion.showLabels(false);
    }


    public void populateResponse() {


        // Q1
        Question question = new Question();
        question.setLibelle("Avez-vous un d ces symptômes ?");
        question.setUid("q1");

        ReponsesPossible r1 = new ReponsesPossible(1, 1, "r1", "Fièvre?", false, false);
        ReponsesPossible r2 = new ReponsesPossible(1, 1, "r2", "Toux continue?", false, false);
        ReponsesPossible r3 = new ReponsesPossible(1, 1, "r3", "Difficulté respiratoire?", false, false);
        ReponsesPossible r4 = new ReponsesPossible(1, 1, "r4", "Inconfort général?", false, false);

        reponsesPossibleArrayList = new ArrayList<>();
        reponsesPossibleArrayList.add(r1);
        reponsesPossibleArrayList.add(r2);
        reponsesPossibleArrayList.add(r3);
        reponsesPossibleArrayList.add(r4);
        question.setReponsesPossibles(reponsesPossibleArrayList);


        questionArrayList = new ArrayList<>();
        questionArrayList.add(question);
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
        question1.setLibelle("Avez-vous un d ces symptômes?");
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


        updateList(reponsesPossibleArrayList);

    }

    private void updateList(ArrayList<ReponsesPossible> reponses) {
        int step;
        if (questionArrayList.size() == 1)
            step = 2;
        else
            step = questionArrayList.size();

        stepperIndicatorQuestion.setStepCount(step);
        if (reponses != null && !reponses.isEmpty() && reponses.size() > 0) {
            reponsesPossibleAdapter = new ReponsesPossibleAdapter(reponses, MainActivity.this, this);
            recyclerView.setNestedScrollingEnabled(false);
            recyclerView.setAdapter(reponsesPossibleAdapter);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(layoutManager);
            reponsesPossibleAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onReponseItemClickListener(int position, ReponsesPossible reponsesPossible) {
        for (Question question : questionArrayList) {
            if (question.getReponsesPossibles() != null) {
                for (ReponsesPossible myreponse : question.getReponsesPossibles()) {
                    if (reponsesPossible.getNom().equalsIgnoreCase(myreponse.getNom().toLowerCase())) {
                        myreponse.setSelected(!myreponse.isSelected());
                        myreponse.setChosed(!myreponse.isChosed());
                    }
                }
            }
        }
        reponsesPossibleAdapter.notifyDataSetChanged();

    }

    @Override
    public void onBtnSuivantClickListener(int position, ReponsesPossible reponsesPossible) {
        for (ReponsesPossible myreponse : reponsesPossibleArrayList) {
            if (reponsesPossible.getNom().equalsIgnoreCase(myreponse.getNom().toLowerCase())) {
                myreponse.setSelected(!myreponse.isSelected());
                myreponse.setChosed(!myreponse.isChosed());
            }
        }
        reponsesPossibleAdapter.notifyDataSetChanged();
    }
}
