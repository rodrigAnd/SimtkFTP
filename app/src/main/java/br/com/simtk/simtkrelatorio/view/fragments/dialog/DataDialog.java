package br.com.simtk.simtkrelatorio.view.fragments.dialog;

import android.app.AlertDialog;
import android.app.DatePickerDialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.com.simtk.simtkrelatorio.R;
import br.com.simtk.simtkrelatorio.util.DadosPreference;


public class DataDialog extends Fragment  {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private EditText dataInicial, dataFinal;
    private Button btnContinuar, btnCancelar;
    private RadioButton radioTodos,
            radioLab,
            radioMedic,
            radioPerf,
            radioDiver,
            radioVarej;
    private int dia, mes, ano;
    private AlertDialog alertDialog;
    private AlertDialog alertDialog1;
    private String op = null;
    private EditText numVend;
    private String resultFun;
    CharSequence[] charSequences = new CharSequence[]{"Considera minimo", "Seleciona Vendedor","Seleciona Codigoo"};
    final boolean[] checados = new boolean[charSequences.length];
    private androidx.appcompat.app.AlertDialog alerta;
    private final String TAG = "RESULT";
    DatePickerDialog.OnDateSetListener setListenerIni;
    DatePickerDialog.OnDateSetListener setListenerFim;





    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DataDialog() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static DataDialog newInstance() {
        DataDialog fragment = new DataDialog();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_date, container, false);
        dataInicial = view.findViewById(R.id.tc_data_inicial);
        dataFinal = view.findViewById(R.id.tc_data_final);
        btnContinuar = (Button) view.findViewById(R.id.btn_confirmar);
        btnCancelar = (Button) view.findViewById(R.id.btn_cancelar1);
        radioTodos = (RadioButton) view.findViewById(R.id.radioTodos);
        radioLab = (RadioButton) view.findViewById(R.id.radioLab);
        radioMedic = (RadioButton) view.findViewById(R.id.radioMedic);
        radioPerf = (RadioButton) view.findViewById(R.id.radioPerf);
        radioDiver = (RadioButton) view.findViewById(R.id.radioDiver);
        radioVarej = (RadioButton) view.findViewById(R.id.radioVarej);


        dataInicial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                dia = (calendar.get(Calendar.DAY_OF_MONTH));
                mes = (calendar.get(Calendar.MONTH) + 1);
                ano = (calendar.get(Calendar.YEAR));



                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth, setListenerIni, ano, mes, dia);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        dataNova();


        dataFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                dia = (calendar.get(Calendar.DAY_OF_MONTH));
                mes = (calendar.get(Calendar.MONTH) + 1);
                ano = (calendar.get(Calendar.YEAR));


                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth, setListenerFim, ano, mes, dia);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        dataNova1();

        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (radioTodos.isChecked()) {
                    DadosPreference dados = new DadosPreference();
                    dados.dadosOp(getContext(), "OP4", "01");
                    dados.dadosOp(getContext(), "INI", dataInicial.getText().toString());
                    dados.dadosOp(getContext(), "FIM", dataFinal.getText().toString());
                    showNoticeDialog();

                }

                if (radioLab.isChecked()) {
                    Toast.makeText(getContext(), "Cliclou", Toast.LENGTH_SHORT).show();
                }

                if (radioMedic.isChecked()) {
                    Toast.makeText(getContext(), "Cliclou", Toast.LENGTH_SHORT).show();
                }

                if (radioPerf.isChecked()) {
                    Toast.makeText(getContext(), "Cliclou", Toast.LENGTH_SHORT).show();
                }

                if (radioDiver.isChecked()) {
                    Toast.makeText(getContext(), "Cliclou", Toast.LENGTH_SHORT).show();
                }

                if (radioVarej.isChecked()) {
                    Toast.makeText(getContext(), "Cliclou", Toast.LENGTH_SHORT).show();
                }

            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }

    private void showNoticeDialog() {
        DialogFragment opcoes = new OpcaoTodosDialog();
        opcoes.show(getFragmentManager(), "minha fragent");
    }
    public  void dataNova(){
        setListenerIni = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                ano = year;
                mes = ++month;
                dia = dayOfMonth;
                if(mes<10){
                    String novoMes = "0"+mes;
                    mes = Integer.parseInt(novoMes);
                    Log.d(TAG, "novo mes " + mes);
                }
               dataInicial.setText(dia+"/"+mes+"/"+ano);
            }
        };
    }
    public  void dataNova1(){
        setListenerFim = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                ano = year;
                mes = ++month;
                dia = dayOfMonth;
                if(mes<10){
                    String novoMes = "0"+mes;
                    Log.d(TAG, "antes de mudar: "+ novoMes);
                    mes = Integer.parseInt(novoMes);
                    Log.d(TAG, "novo mes " + mes);
                }
                dataFinal.setText(dia+"/"+mes+"/"+ano);
            }
        };
    }
}


