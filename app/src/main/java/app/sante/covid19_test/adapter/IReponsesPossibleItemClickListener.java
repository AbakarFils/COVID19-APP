package app.sante.covid19_test.adapter;

import app.sante.covid19_test.entity.ReponsesPossible;

public interface IReponsesPossibleItemClickListener {
    void onReponseItemClickListener(int position, ReponsesPossible reponsesPossible);
    void onBtnSuivantClickListener(int position, ReponsesPossible reponsesPossible);
}
