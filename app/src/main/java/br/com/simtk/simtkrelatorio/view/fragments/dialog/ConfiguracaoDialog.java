package br.com.simtk.simtkrelatorio.view.fragments.dialog;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import br.com.simtk.simtkrelatorio.R;
import br.com.simtk.simtkrelatorio.util.DadosPreference;

public class ConfiguracaoDialog extends DialogFragment {

    private Button btn_salvar_shared;
    private String TAG = "resultCod";

    private EditText ed_cod_cli;
    private EditText ed_num_vend;
    private EditText ed_senha_vend;

    private static String PREF_NOME = "App_SIMTK_RELATORIO";
    SharedPreferences sharedPreferences;

    private SharedPreferences.Editor dados;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_configuracao, container, false);
        btn_salvar_shared = (Button)view.findViewById(R.id.btn_salvar_shared);
        ed_cod_cli = (EditText)view.findViewById(R.id.ed_cod_cli);
        ed_num_vend = (EditText)view.findViewById(R.id.ed_num_vend);
        ed_senha_vend = (EditText)view.findViewById(R.id.ed_senha);


        sharedPreferences = getContext().getSharedPreferences(PREF_NOME, Context.MODE_PRIVATE);
        DadosPreference dadosPreference1 = new DadosPreference();
        String codCliRet = dadosPreference1.dadosPreferenceRecuperar(getContext(), "codCli");
        String numVendRet = dadosPreference1.dadosPreferenceRecuperar(getContext(), "numVend");
        String senhaVendRet = dadosPreference1.dadosPreferenceRecuperar(getContext(), "senhaVend");

        ed_cod_cli.setText(codCliRet);
        ed_num_vend.setText(numVendRet);
        ed_senha_vend.setText(senhaVendRet);




        btn_salvar_shared.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    String codCliente = ed_cod_cli.getText().toString();

                    String numVend = ed_num_vend.getText().toString();

                    String senhaVend = ed_senha_vend.getText().toString();
                    Log.i(TAG, "onClick: "+ codCliente + numVend + senhaVend);

                    DadosPreference dadosPreference = new DadosPreference();

                    if(codCliente.equals("")|| numVend.equals("")||senhaVend.equals("")){
                        Toast.makeText(getContext(), "Favor Digitar todos os Dados", Toast.LENGTH_LONG).show();

                    }else{

                        String result = dadosPreference.dadosPreferenceGravar(codCliente, numVend, senhaVend, getContext());
                        Toast.makeText(getContext(), result, Toast.LENGTH_LONG).show();
                        onStop();
                    }
            }
        });
        return view;
    }
}
