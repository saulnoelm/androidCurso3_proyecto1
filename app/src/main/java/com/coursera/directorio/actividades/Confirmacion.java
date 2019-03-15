package com.coursera.directorio.actividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.coursera.directorio.R;

public class Confirmacion extends AppCompatActivity {

    private TextView tvNombre;
    private TextView tvFecha;
    private TextView tvTelefono;
    private TextView tvEmail;
    private TextView tvDescripcion;

    private Button tbnEditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacion);

        setInitialValues();
        setUpLayout();
        setUpUIInteraction();
    }

    /**Metodo que agrega los valores a los componentes
     * @return void
     * */
    public void setInitialValues() {
        Bundle parametros = getIntent().getExtras();

        tvNombre = (TextView) findViewById(R.id.tvConf_valor_nombre);
        tvFecha = (TextView) findViewById(R.id.tvConf_valor_fecha);
        tvTelefono = (TextView) findViewById(R.id.tvConf_valor_telefono);
        tvEmail = (TextView) findViewById(R.id.tvConf_valor_email);
        tvDescripcion = (TextView) findViewById(R.id.tvConf_valor_descripcion);

        tvNombre.setText(parametros.getString(getResources().getString(R.string.pNombre)));
        tvFecha.setText(parametros.getInt(getResources().getString(R.string.pFechaDia))+"-"+
                parametros.getInt(getResources().getString(R.string.pFechaMes))+"-"+
                parametros.getInt(getResources().getString(R.string.pFechaAnio)));
        tvTelefono.setText(parametros.getString(getResources().getString(R.string.pPhone)));
        tvEmail.setText(parametros.getString(getResources().getString(R.string.pEmail)));
        tvDescripcion.setText(parametros.getString(getResources().getString(R.string.pDescription)));
    }

    /**Metodo que inicializa los componentes
     * @return void
     * */
    public void setUpLayout() {

    }

    /**Metodo donde se definen los comportamientos de los componente con los eventos de usuario
     * @return void
     * */
    public void setUpUIInteraction() {
        tbnEditar = (Button) findViewById(R.id.btnConf);
        tbnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Confirmacion.this, MainActivity.class);
                intent.putExtra(getResources().getString(R.string.pNombre), tvNombre.getText().toString());
                intent.putExtra(getResources().getString(R.string.pFecha), tvFecha.getText().toString());
                intent.putExtra(getResources().getString(R.string.pPhone), tvTelefono.getText().toString());
                intent.putExtra(getResources().getString(R.string.pEmail), tvEmail.getText().toString());
                intent.putExtra(getResources().getString(R.string.pDescription), tvDescripcion.getText().toString());
                startActivity(intent);

                finish();
            }
        });
    }
}
