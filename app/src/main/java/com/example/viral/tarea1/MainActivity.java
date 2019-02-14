package com.example.viral.tarea1;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    AutoCompleteTextView autocomplete;
    protected Button limpiar;
    final Context context = this;
    Spinner spine;
    EditText name, phone;
    CheckBox checkBox;
    RadioButton radioButton;
    protected Toast guardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.nombrep);
        phone =findViewById(R.id.numerop);
        checkBox = findViewById(R.id.checkp);
        spine = findViewById(R.id.spin);
        radioButton = findViewById(R.id.male);

        String[] librotes = getResources().getStringArray(R.array.lobros);
        limpiar= (Button) findViewById(R.id.buttonsend);
        limpiar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                alerta();
            }
        });
        autocomplete = findViewById(R.id.main_activity_AutocompleteTV);
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this,android.R.layout.select_dialog_item,librotes);

        autocomplete.setThreshold(3);
        autocomplete.setAdapter(adapter);
    }

    public void onRadioButtonClicked(View view) {

    boolean checked = ((RadioButton) view).isChecked();
    switch (view.getId()) {
        case R.id.female:
            if (checked)
                radioButton = (RadioButton)findViewById(R.id.female);
            break;
        case R.id.male:
            if (checked)
                radioButton = (RadioButton)findViewById(R.id.male);
            break;
    }
    }

    public String toString() {
        return "Nombre: " + name.getText() +
                "\nTelefono: " + phone.getText() +
                "\nEscolaridad: " + spine.getSelectedItem().toString() +
                "\nGénero : " + radioButton.getText().toString() +
                (autocomplete.getText().length() > 0 ? "\nLibro Favorito:" + autocomplete.getText() : "\n") +
                "\nPractica Deporte: " + (checkBox.isChecked() ? "Si" : "No");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){
            case R.id.new_game:
                if (name.getText().length() >0 && phone.length() >0){
                    Toast.makeText(this, toString(), Toast.LENGTH_LONG).show();
                }else Toast.makeText(this,"Completa los campos",Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    public void setLimpiar(){
        autocomplete.setText("");
        spine.setSelection(0);
        name.setText("");
        phone.setText("");
        checkBox.setChecked(false);
        radioButton.setChecked(true);


    }


    public void alerta(){
        AlertDialog.Builder adb = new AlertDialog.Builder(context);

        adb.setMessage("Desea limpiar el contenido?");
        adb.setCancelable(false);
        adb.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                setLimpiar();
            }
        });
        adb.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alertDialog = adb.create();
        alertDialog.show();
    }
    public void buttclick(View view){
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                setLimpiar();
            }
        });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
    }


}
