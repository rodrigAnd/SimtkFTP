package br.com.simtk.simtkrelatorio.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;

import br.com.simtk.simtkrelatorio.model.ModelDados;




public class DadosPreference {
    private static String PREF_NOME = "App_SIMTK_RELATORIO";
    private SharedPreferences sharedPreferences;
    private String TAG = "RESULT";
    private SharedPreferences.Editor dados;


    public String dadosPreferenceGravar(String codCliente, String numVend, String senhaVend, Context context){

        try {


            sharedPreferences = context.getSharedPreferences(PREF_NOME, Context.MODE_PRIVATE);
            dados = sharedPreferences.edit();
            dados.putString("codCli", codCliente);

            ContadorString func = new ContadorString();
            String novoVend = func.contadorString(numVend);
            dados.putString("numVend", novoVend);
            dados.putString("senhaVend", senhaVend);
            dados.apply();

        }catch (Exception e){
            e.printStackTrace();
        }

       return "Cadastro ok";
   }
   public String dadosPreferenceRecuperar(Context context, String chave){


        try{
            sharedPreferences = context.getSharedPreferences(PREF_NOME, Context.MODE_PRIVATE);
            return sharedPreferences.getString(chave, "");


        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
   }

   public void dadosOp(Context context, String chave, String valor){
       try {
           sharedPreferences = context.getSharedPreferences(PREF_NOME, Context.MODE_PRIVATE);
           dados = sharedPreferences.edit();
           dados.putString(chave, valor);
           dados.apply();
       }catch (Exception e){
           e.printStackTrace();
       }
    }
   public void removerDados(Context context, String chave){
       sharedPreferences = context.getSharedPreferences(PREF_NOME, Context.MODE_PRIVATE);
       dados.remove(chave);
   }

}
