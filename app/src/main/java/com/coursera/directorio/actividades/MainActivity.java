package com.coursera.directorio.actividades;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Calendar;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.coursera.directorio.R;
import com.coursera.directorio.utils.ViewUtil;

public class MainActivity extends AppCompatActivity {

    EditText nombreCompleto;
    DatePicker fechaNacimiento;
    EditText telefono;
    EditText email;
    EditText descripcion;

    private DatePicker dpResult;
    private Button btnCancelar;
    private Button btnSiguiente;

    private int year;
    private int month;
    private int day;

    static final int DATE_DIALOG_ID = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setInitialValues();
        setUpLayout();
        setUpUIInteraction();
    }

    /**Metodo que agrega los valores a los componentes
     * @return void
     * */
    public void setInitialValues() {
        Bundle parametros = getIntent().getExtras();
        if (parametros != null && parametros.size() > 0) {
            inicializaComponentes();

            nombreCompleto.setText(parametros.getString(getResources().getString(R.string.pNombre)));
            String[] fechas = parametros.getString(getResources().getString(R.string.pFecha)).split("-");
            day = Integer.parseInt(fechas[0]);
            month = Integer.parseInt(fechas[1]);
            year = Integer.parseInt(fechas[2]);
            dpResult = (DatePicker) findViewById(R.id.datePicker);
            dpResult.init(year, month, day, null);
            telefono.setText(parametros.getString(getResources().getString(R.string.pPhone)));
            email.setText(parametros.getString(getResources().getString(R.string.pEmail)));
            descripcion.setText(parametros.getString(getResources().getString(R.string.pDescription)));
        }else {
            inicializaPicket();
        }
    }

    public void inicializaComponentes() {
        nombreCompleto = (EditText) findViewById(R.id.etNombreCompleto);
        fechaNacimiento = (DatePicker) findViewById(R.id.datePicker);
        telefono = (EditText) findViewById(R.id.etTelefono);
        email = (EditText) findViewById(R.id.etEmail);
        descripcion = (EditText) findViewById(R.id.etDescricion);
    }

    public void inicializaPicket() {
        dpResult = (DatePicker) findViewById(R.id.datePicker);
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        dpResult.init(year, month, day, null);
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
        btnCancelar = (Button) findViewById(R.id.btnOk);
        btnCancelar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                inicializaPicket();
            }
        });

        btnSiguiente = (Button) findViewById(R.id.btnSiguiente);
        btnSiguiente.setOnClickListener(new OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                if (verificaFosdrmulario()) {
                    Integer day  = fechaNacimiento.getDayOfMonth();
                    Integer month= fechaNacimiento.getMonth();
                    Integer year = fechaNacimiento.getYear();

                    Intent intent = new Intent(MainActivity.this, Confirmacion.class);
                    intent.putExtra(getResources().getString(R.string.pNombre), nombreCompleto.getText().toString());
                    intent.putExtra(getResources().getString(R.string.pFechaDia), day);
                    intent.putExtra(getResources().getString(R.string.pFechaMes), month);
                    intent.putExtra(getResources().getString(R.string.pFechaAnio), year);
                    intent.putExtra(getResources().getString(R.string.pPhone), telefono.getText().toString());
                    intent.putExtra(getResources().getString(R.string.pEmail), email.getText().toString());
                    intent.putExtra(getResources().getString(R.string.pDescription), descripcion.getText().toString());
                    startActivity(intent);

                    finish();
                } else
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.msg_error), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean verificaFosdrmulario() {
        inicializaComponentes();
        if (null == nombreCompleto.getText() || "".equals(nombreCompleto.getText())) {
            return false;
        }
        if (null == telefono.getText() || "".equals(telefono.getText())) {
            return false;
        }
        if (!ViewUtil.isValidMobile(telefono.getText().toString())) {
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.error_phone), Toast.LENGTH_SHORT).show();
            return false;
        }
        if (null == email.getText() || "".equals(email.getText())) {
            return false;
        }
        boolean valido = ViewUtil.validateEmail(email.getText().toString());
        if (!valido) {
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.error_email), Toast.LENGTH_SHORT).show();
            return false;
        }
        if (null == descripcion.getText() || "".equals(descripcion.getText())) {
            return false;
        }
        return true;
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                DatePickerDialog dialog = new DatePickerDialog(this, android.R.style.Theme_Holo_Dialog, datePickerListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.show();
                return dialog;
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;
            dpResult.init(year, month, day, null);
        }
    };
}
