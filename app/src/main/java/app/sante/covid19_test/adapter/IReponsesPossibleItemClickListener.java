package app.sante.covid19_test.adapter;

import app.sante.covid19_test.entity.Reponse;

public interface IReponsesPossibleItemClickListener {
    void onReponseItemClickListener(int position, Reponse reponse);
    void onBtnSuivantClickListener(int position, Reponse reponse);
}
