package br.com.simtk.simtkrelatorio.view.fragments.dialog;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.io.File;

import br.com.simtk.simtkrelatorio.R;
import br.com.simtk.simtkrelatorio.model.ModelDados;
import br.com.simtk.simtkrelatorio.util.CriarArquivo;
import br.com.simtk.simtkrelatorio.util.DadosPreference;
import br.com.simtk.simtkrelatorio.util.DataAtual;
import br.com.simtk.simtkrelatorio.view.fragments.mostrar_arquivo.LerArquivoFragment;

public class OpcaoTodosDialog extends DialogFragment {

    private CheckBox cb_min, cb_sel_vend, cb_cod;
    private EditText ed_num_vend;
    private RadioButton rb_cod_barra, rb_sub_cod;
    private RadioGroup rg_geral;
    private Button btn_gerar_relatorio;
    private String TAG = "RESULT";
    private boolean checkMin = false;
    private boolean checkVend = false;
    private boolean checkCod = false;
    private boolean rbCodBar = false;
    private boolean rbCodProd = false;
    DadosPreference dados;
    ModelDados modelDados;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_opcoes, container, false);

        cb_min = (CheckBox) view.findViewById(R.id.cb_min);
        cb_sel_vend = (CheckBox) view.findViewById(R.id.cb_sel_vend);
        cb_cod = (CheckBox) view.findViewById(R.id.cb_cod);
        ed_num_vend = (EditText) view.findViewById(R.id.ed_num_vend);
        rb_cod_barra = (RadioButton) view.findViewById(R.id.rb_cod_barra);
        rb_sub_cod = (RadioButton) view.findViewById(R.id.rb_sub_cod);
        rg_geral = (RadioGroup) view.findViewById(R.id.rg_geral);
        btn_gerar_relatorio = (Button) view.findViewById(R.id.btn_gerar_relatorio);

        cb_min.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkMin = true;
                }else{
                    checkMin = false;
                }
            }
        });
        cb_sel_vend.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkVend = true;
                    ed_num_vend.setVisibility(View.VISIBLE);

                    if (ed_num_vend.getText().toString().equals("")) {
                        Toast.makeText(getContext(), "Digite o numero do vendedor", Toast.LENGTH_LONG).show();
                    } else {
                        String num = ed_num_vend.getText().toString();
                        dados = new DadosPreference();
                        dados.dadosOp(getContext(), "OP7", num);
                        Log.d(TAG, "onCheckedChanged: "+ dados.dadosPreferenceRecuperar(getContext(), "OP7"));
                    }

                } else {
                    checkVend = false;
                    ed_num_vend.setText("");
                    ed_num_vend.setVisibility(View.GONE);
                }
            }
        });

        cb_cod.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkCod = true;
                    rg_geral.setVisibility(View.VISIBLE);
                    rb_cod_barra.setVisibility(View.VISIBLE);
                    rb_sub_cod.setVisibility(View.VISIBLE);
                    if (rb_cod_barra.isChecked()) {

                    } else if (rb_sub_cod.isChecked()) {

                    }

                } else {
                    checkCod = false;
                    rb_cod_barra.setVisibility(View.INVISIBLE);
                    rb_sub_cod.setVisibility(View.INVISIBLE);
                    rg_geral.clearCheck();
                    rg_geral.setVisibility(View.GONE);
                }
            }
        });
        btn_gerar_relatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkMin == false){
                    DadosPreference dados = new DadosPreference();
                    dados.dadosOp(getContext(), "OP7", "01");
                }else{
                    DadosPreference dados = new DadosPreference();
                    dados.dadosOp(getContext(), "OP7", "02");
                }

                if(checkVend == false){
                    DadosPreference dados = new DadosPreference();
                    dados.dadosOp(getContext(), "OP8", "01");
                }else{
                    DadosPreference dados = new DadosPreference();
                    dados.dadosOp(getContext(), "OP8", "01");
                }

                DataAtual data = new DataAtual();
                String dataAtual = data.getDateTime();
                CriarArquivo file = new  CriarArquivo();
                DadosPreference dados = new DadosPreference();

                file.salvarArquivo(dataAtual +dados.dadosPreferenceRecuperar(getContext(), "senhaVend")+" "+
                                                                dados.dadosPreferenceRecuperar(getContext(),"INI")+" "+
                                                                dados.dadosPreferenceRecuperar(getContext(), "FIM")+" "+
                                                                "01"+" "+ dados.dadosPreferenceRecuperar(getContext(), "OP1")+" "
                                                                +dados.dadosPreferenceRecuperar(getContext(), "OP2")+" "
                                                                +dados.dadosPreferenceRecuperar(getContext(), "OP3")+" "
                                                                +dados.dadosPreferenceRecuperar(getContext(), "OP4")+" "
                                                                +"00"+" "+"00"+" "  // VALORES DO INTERVALO
                                                                +dados.dadosPreferenceRecuperar(getContext(), "OP7")+" " +"00"+" "+"00",getContext());



                Fragment lerArquivoFragment = LerArquivoFragment.newInstance();
                openFragment(lerArquivoFragment);

                onStop();
            }
        });

        return view;
    }

    private void openFragment(Fragment fragment) {

        if (fragment != null) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}
