package com.example.fecapcalculadoraimc;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CalculoIMCActivity extends AppCompatActivity {

    EditText etnPesoKgs;
    EditText etnAlturaMetros;
    Button btnFechar;
    Button btnLimpar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculo_imc_activity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Instantiate views
        etnPesoKgs = findViewById(R.id.etnKg);
        etnAlturaMetros = findViewById(R.id.etnMetros);
        btnFechar = findViewById(R.id.btnFecharIMC);
        btnLimpar = findViewById(R.id.btnLimparIMC);

        Button btnCalcular = findViewById(R.id.btnCalcularIMC);
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double pesoKgs = Double.parseDouble(etnPesoKgs.getText().toString());
                double alturaMetros = Double.parseDouble(etnAlturaMetros.getText().toString());

                double resultIMC = pesoKgs / (alturaMetros * alturaMetros);

                Class nextActivity;

                if (resultIMC >= 40) {
                    nextActivity = ObesidadeGrau3.class;
                } else if (resultIMC >= 35) {
                    nextActivity = ObesidadeGrau2.class;
                } else if (resultIMC >= 30) {
                    nextActivity = ObesidadeGrau1.class;
                } else if (resultIMC >= 25) {
                    nextActivity = Sobrepeso.class;
                } else if (resultIMC >= 18.5) {
                    nextActivity = PesoNormal.class;
                } else {
                    nextActivity = AbaixoDoPeso.class;
                }

                Intent intent = new Intent(CalculoIMCActivity.this, nextActivity);

                Bundle bundle = new Bundle();
                bundle.putDouble("PESOKG", pesoKgs);
                bundle.putDouble("ALTURAMETROS", alturaMetros);
                bundle.putDouble("RESULTADOIMC", resultIMC);

                intent.putExtras(bundle);

                startActivity(intent);

                Log.d("CALCULADORA IMC", "Peso em Kgs: " + pesoKgs + ", Altura em m: " + alturaMetros + ", Resultado: " + resultIMC);
            }
        });
    }
}