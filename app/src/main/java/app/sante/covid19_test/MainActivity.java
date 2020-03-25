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

    private static int currentProgression;
    private static int index = 0;

    private StepperIndicator stepperIndicatorQuestion;

    ArrayList<Reponse> reponseArrayList;
    ArrayList<Question> questionArrayList;
    private ReponsesPossibleAdapter reponsesPossibleAdapter;


    private MediaPlayer mPlayer;
    private static int oTime = 0, sTime = 0, eTime = 0, fTime = 5000, bTime = 5000;
    private Handler hdlr = new Handler();

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
                    Toast.makeText(MainActivity.this, "Job ajouté à votre favoris avec succès" + questionArrayList.get(index).getLibelle(), Toast.LENGTH_SHORT).show();
                    txt_current_question.setText("");
                    txt_current_question.setText(questionArrayList.get(index).getLibelle());
                    updateList((ArrayList<Reponse>) questionArrayList.get(index).getReponses());
                    numero.setText(" ");
                    numero.setText("" + index + 1);
                }

                stepperIndicatorQuestion.setCurrentStep(index + 1);
                currentProgression++;

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
                //pausebtn.setEnabled(true);
                //playbtn.setEnabled(false);
            }
        });
        populateResponse();
    }

    private Runnable UpdateSongTime = new Runnable() {
        @Override
        public void run() {
            sTime = mPlayer.getCurrentPosition();
            hdlr.postDelayed(this, 100);
        }
    };

    public void bindViews() {
        currentProgression = 2;
        playbtn = findViewById(R.id.playbtn);
        mPlayer = MediaPlayer.create(this,R.raw.tes);


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


        questionArrayList = new ArrayList<>();
        questionArrayList.add(question);
        // Q1
        Question question1 = new Question();
        question1.setLibelle("Sexe ?");
        question1.setUid("q1");

        Reponse qr1 = new Reponse(1, 1, "r1", "Homme", false, false);
        Reponse qr2 = new Reponse(1, 1, "r2", "Femme", false, false);

        List<Reponse> reponseList1 = new ArrayList<>();
        reponseList1.add(qr1);
        reponseList1.add(qr2);
        question1.setReponses(reponseList1);

        //Q2
        Question question2 = new Question();
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
        Question question3 = new Question();
        question1.setLibelle("Avez-vous un d ces symptômes?");
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


        updateList(reponseArrayList);

    }

    private void updateList(ArrayList<Reponse> reponses) {
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
