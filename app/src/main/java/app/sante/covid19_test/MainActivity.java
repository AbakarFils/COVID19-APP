package app.sante.covid19_test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.badoualy.stepperindicator.StepperIndicator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import app.sante.covid19_test.adapter.IReponsesPossibleItemClickListener;
import app.sante.covid19_test.adapter.ReponsesPossibleAdapter;
import app.sante.covid19_test.entity.Question;
import app.sante.covid19_test.entity.Reponse;

public class MainActivity extends AppCompatActivity implements IReponsesPossibleItemClickListener {
    private RecyclerView recyclerView;
    private Button btnSuivant;
    private ImageView playbtn;
    private TextView txt_current_question, numero;

    private static int currentProgression = 1;
    private static int index = 0;

    private StepperIndicator stepperIndicatorQuestion;

    ArrayList<Reponse> reponseArrayList;
    final ArrayList<Question> questionArrayList = new ArrayList<>();
    private ReponsesPossibleAdapter reponsesPossibleAdapter;


    private MediaPlayer mPlayer;
    private static int oTime = 0, sTime = 0, eTime = 0, fTime = 5000, bTime = 5000;
    private Handler hdlr = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
        populateResponse();

        btnSuivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentProgression <= questionArrayList.size()) {
                    Toast.makeText(MainActivity.this, "Valeur " + questionArrayList.get(currentProgression).getLibelle(), Toast.LENGTH_SHORT).show();
                    txt_current_question.setText("");
                    txt_current_question.setText(questionArrayList.get(currentProgression).getLibelle());
                    updateList((ArrayList<Reponse>) questionArrayList.get(currentProgression).getReponses());
                    numero.setText(" ");
                    String num = " " + currentProgression;
                    numero.setText(num);
                } else {
                    Intent intent = new Intent(MainActivity.this, EvaluationActivity.class);
                    startActivity(intent);
                }
                currentProgression++;
                stepperIndicatorQuestion.setCurrentStep(currentProgression);


            }
        });

        playbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Playing Audio", Toast.LENGTH_SHORT).show();
                mPlayer.start();
                eTime = mPlayer.getDuration();
                sTime = mPlayer.getCurrentPosition();
                hdlr.postDelayed(UpdateSongTime, 100);
            }
        });

    }

    private Runnable UpdateSongTime = new Runnable() {
        @Override
        public void run() {
            sTime = mPlayer.getCurrentPosition();
            hdlr.postDelayed(this, 100);
        }
    };

    public void bindViews() {
        playbtn = findViewById(R.id.playbtn);
        mPlayer = MediaPlayer.create(this, R.raw.tes);
        txt_current_question = findViewById(R.id.txt_current_question);
        numero = findViewById(R.id.numero);
        recyclerView = findViewById(R.id.recyclerview_reponses);
        btnSuivant = findViewById(R.id.btn_suivant);

        stepperIndicatorQuestion = findViewById(R.id.stepperIndicatorQuestion);
        stepperIndicatorQuestion.showLabels(false);
    }


    public void populateResponse() {


        // Q1
        final Question question = new Question("q3", " Avez-vous un d ces symptômes ?", "", 1);

        Reponse r1 = new Reponse(1, 1, "r1", "Fièvre?", false, false);
        Reponse r2 = new Reponse(1, 1, "r2", "Toux continue?", false, false);
        Reponse r3 = new Reponse(1, 1, "r3", "Difficulté respiratoire?", false, false);
        Reponse r4 = new Reponse(1, 1, "r4", "Inconfort général?", false, false);

        reponseArrayList = new ArrayList<>();
        reponseArrayList.add(r1);
        reponseArrayList.add(r2);
        reponseArrayList.add(r3);
        reponseArrayList.add(r4);
        question.setReponses(reponseArrayList);


        // Q1
        Question question1 = new Question("q3", "Sexe ?", "", 1);
        Reponse qr1 = new Reponse(1, 1, "r1", "Homme", false, false);
        Reponse qr2 = new Reponse(1, 1, "r2", "Femme", false, false);

        List<Reponse> reponseList1 = new ArrayList<>();
        reponseList1.add(qr1);
        reponseList1.add(qr2);
        question1.setReponses(reponseList1);

        //Q2
        Question question2 = new Question("q3", "Age ?", "", 1);
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
        Question question3 = new Question("q3", "Avez-vous un d ces symptômes?", "", 1);
        Reponse q3r1 = new Reponse(1, 1, "r1", "Dyspné (noyade)", false, false);
        Reponse q3r2 = new Reponse(1, 1, "r2", "Hémoptysie (crasher du sang)", false, false);
        Reponse q3r3 = new Reponse(1, 1, "r3", "Douleur sur le côté", false, false);

        List<Reponse> reponseList3 = new ArrayList<>();

        reponseList3.add(q3r1);
        reponseList3.add(q3r2);
        reponseList3.add(q3r3);
        question3.setReponses(reponseList3);


        // Q 4
        Question question4 = new Question("q3", "Rencontrez-vous l’une de ces situations?", "", 1);
        Reponse q4r1 = new Reponse(1, 1, "r1", "Sous traitement avec de medicaments qui produisent une immunosuppression", false, false);
        Reponse q4r2 = new Reponse(1, 1, "r2", "Grossesse", false, false);
        Reponse q4r3 = new Reponse(1, 1, "r3", "Post-partum (< 6semaines)", false, false);
        Reponse q4r4 = new Reponse(1, 1, "r3", "Mère allaitantes", false, false);

        List<Reponse> reponseList4 = new ArrayList<>();

        reponseList4.add(q4r1);
        reponseList4.add(q4r2);
        reponseList4.add(q4r3);
        reponseList4.add(q4r4);
        question4.setReponses(reponseList4);


        Question question5 = new Question("q3", "Votre température ?", "", 1);
        Reponse q5r1 = new Reponse(1, 1, "r1", "36.5° ou moins", false, false);
        Reponse q5r2 = new Reponse(1, 1, "r2", "37.0°", false, false);
        Reponse q5r3 = new Reponse(1, 1, "r3", "37.5°", false, false);
        Reponse q5r4 = new Reponse(1, 1, "r3", "38.0°", false, false);
        Reponse q5r5 = new Reponse(1, 1, "r3", "38.5° ou plus", false, false);

        List<Reponse> reponseList5 = new ArrayList<>();

        reponseList5.add(q5r1);
        reponseList5.add(q5r2);
        reponseList5.add(q5r3);
        reponseList5.add(q5r4);
        reponseList5.add(q5r5);
        question5.setReponses(reponseList5);

        Question question6 = new Question("q3", "Inconfort général ", "", 1);


        Question question7 = new Question("q3", "Difficulté respiratoire ", "", 1);
        Question question8 = new Question("q3", "Votre état de santé actuel, vous permet-il de faire les activtés de base de la vie quotidienne ? ", "", 1);


        questionArrayList.add(question);
        questionArrayList.add(question1);
        questionArrayList.add(question2);
        questionArrayList.add(question3);
        questionArrayList.add(question4);
        questionArrayList.add(question5);


        updateList((ArrayList) questionArrayList.get(0).getReponses());

    }

    private void updateList(ArrayList<Reponse> reponses) {
        int step;
        if (questionArrayList.size() == 1)
            step = 2;
        else
            step = questionArrayList.size();

        stepperIndicatorQuestion.setStepCount(step);
        String q = questionArrayList.get(currentProgression).getLibelle();

        if (reponses != null && !reponses.isEmpty() && reponses.size() > 0) {

            Collections.sort(reponses, new Comparator<Reponse>() {
                @Override
                public int compare(Reponse q1, Reponse q2) {
                    int res = -1;
                    if (q1.getNom().compareToIgnoreCase(q2.getNom()) > 0) {
                        res = 1;
                    }
                    return res;
                }
            });
            reponsesPossibleAdapter = new ReponsesPossibleAdapter(reponses, MainActivity.this, this);
            recyclerView.setNestedScrollingEnabled(false);
            recyclerView.setAdapter(reponsesPossibleAdapter);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(layoutManager);
            reponsesPossibleAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onReponseItemClickListener(int position, Reponse reponse) {
        for (Question question : questionArrayList) {
            if (question.getReponses() != null) {
                for (Reponse myreponse : question.getReponses()) {
                    if (reponse.getNom().equalsIgnoreCase(myreponse.getNom().toLowerCase())) {
                        myreponse.setSelected(!myreponse.isSelected());
                        myreponse.setChosed(!myreponse.isChosed());
                    }
                }
            }
        }
        reponsesPossibleAdapter.notifyDataSetChanged();

    }

    @Override
    public void onBtnSuivantClickListener(int position, Reponse reponse) {
        for (Reponse myreponse : reponseArrayList) {
            if (reponse.getNom().equalsIgnoreCase(myreponse.getNom().toLowerCase())) {
                myreponse.setSelected(!myreponse.isSelected());
                myreponse.setChosed(!myreponse.isChosed());
            }
        }
        reponsesPossibleAdapter.notifyDataSetChanged();
    }
}
