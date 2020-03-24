package app.sante.covid19_test;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ConnectionActivity extends AppCompatActivity {
    private TextView telephone;
    private Button btnConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);

        telephone = findViewById(R.id.txtPhone);
        btnConnection = findViewById(R.id.btnConnection);

        btnConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogue();
            }
        });
    }


    private void showDialogue() {
        final Dialog dialog = new Dialog(ConnectionActivity.this);
        dialog.setContentView(R.layout.custom_popup_autorisation);
        Button btn_send = dialog.findViewById(R.id.btn_send);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(ConnectionActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        dialog.show();


    }
}
