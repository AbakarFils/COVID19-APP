package app.sante.covid19_test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import java.util.ArrayList;

import app.sante.covid19_test.adapter.IReponsesPossibleItemClickListener;
import app.sante.covid19_test.adapter.ReponsesPossibleAdapter;
import app.sante.covid19_test.entity.ReponsesPossible;

public class MainActivity extends AppCompatActivity implements IReponsesPossibleItemClickListener {
    private RecyclerView recyclerView;
    ArrayList<ReponsesPossible> reponsesPossibleArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview_reponses);
        populateResponse();
    }


    public void populateResponse() {
        ReponsesPossible r1 = new ReponsesPossible(1, 1,"r1", "Fièvre?", false,false);
        ReponsesPossible r2 = new ReponsesPossible(1, 1,"r2", "Toux continue?", false,false);
        ReponsesPossible r3 = new ReponsesPossible(1, 1,"r3", "Difficulté respiratoire?", false,false);
        ReponsesPossible r4 = new ReponsesPossible(1, 1,"r4", "Inconfort général?", false,false);

        reponsesPossibleArrayList = new ArrayList<>();
        reponsesPossibleArrayList.add(r1);
        reponsesPossibleArrayList.add(r2);
        reponsesPossibleArrayList.add(r3);
        reponsesPossibleArrayList.add(r4);

        updateList(reponsesPossibleArrayList);

    }

    private void updateList(ArrayList<ReponsesPossible> reponses) {
        //int resId3 = R.anim.layout_animation_slide_bottom;
        //LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(MainActivity.this, resId3);
       // recyclerView.setLayoutAnimation(animation);
        if (reponses != null && !reponses.isEmpty() && reponses.size() > 0) {
            ReponsesPossibleAdapter jobAdapter = new ReponsesPossibleAdapter(reponses, MainActivity.this, this);
            recyclerView.setNestedScrollingEnabled(false);
            recyclerView.setAdapter(jobAdapter);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(layoutManager);
            jobAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onReponseItemClickListener(int position, ReponsesPossible reponsesPossible) {

    }

    @Override
    public void onBtnSuivantClickListener(int position, ReponsesPossible reponsesPossible) {

    }
}
