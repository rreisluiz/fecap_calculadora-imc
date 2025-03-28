package com.example.fecapcalculadoraimc;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ObesidadeGrau2 extends AppCompatActivity {

    private TextView pesoKgView;
    private TextView alturaMetrosView;
    private TextView resultadoIMCView;
    private Button btnFechar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_obesidade_grau2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        pesoKgView = findViewById(R.id.txtPesoKg);
        alturaMetrosView = findViewById(R.id.txtAlturaMetros);
        resultadoIMCView = findViewById(R.id.txtResultadoIMC);

        btnFechar = findViewById(R.id.btnFechar);

        Intent intent = getIntent();

        if (intent != null && intent.getExtras() != null) {
            Bundle extras = intent.getExtras();

            double pesoKg = extras.getDouble("PESOKG");
            double alturaMetros = extras.getDouble("ALTURAMETROS");
            double resultadoIMC = extras.getDouble("RESULTADOIMC");

            pesoKgView.setText(String.format("%.2f Kg",pesoKg));
            alturaMetrosView.setText(String.format("%.2f m",alturaMetros));
            resultadoIMCView.setText(String.format("IMC: %.2f",resultadoIMC));
        }

        btnFechar.setOnClickListener(v -> {
            finish();
        });
    }
}