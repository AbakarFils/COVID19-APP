package app.sante.covid19_test.adapter;

import android.widget.Button;
import android.widget.EditText;

import app.sante.covid19_test.entity.Question;
import app.sante.covid19_test.entity.Reponse;

public interface IQuestionInterViewItemClickListener {
    void onQuestionSaisieReponse(Question questionnaireInterview, EditText edtResponse);
    void onUniqueReponseItemClick(int position, Question question, Reponse reponse, Button button);
    void onMultipleReponseItemClick(int position, Question question, Reponse reponse, Button button);
}