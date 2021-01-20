package br.com.simtk.simtkrelatorio.view.fragments.mostrar_arquivo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;

import br.com.simtk.simtkrelatorio.R;
import br.com.simtk.simtkrelatorio.util.CriarArquivo;
import br.com.simtk.simtkrelatorio.util.DadosPreference;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LerArquivoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LerArquivoFragment extends Fragment {

    private TextView txtLido;
    private String TAG = "valor de I";
    private ProgressBar progressBar;


    public LerArquivoFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static LerArquivoFragment newInstance() {
        LerArquivoFragment fragment = new LerArquivoFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      final View view = inflater.inflate(R.layout.fragment_ler_arquivo, container, false);


        DadosPreference dados = new DadosPreference();
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        final String novoArquivo = dados.dadosPreferenceRecuperar(getContext(), "codCli")+"_"
                +dados.dadosPreferenceRecuperar(getContext(), "codCli")+"_"
                +dados.dadosPreferenceRecuperar(getContext(), "numVend")+".txt";


        File folder = new File(getContext().getFilesDir(), novoArquivo);
        final CriarArquivo leitura = new CriarArquivo();
        int i =2;
        do {
            if (!folder.exists()) {

                i++;
                Log.d(TAG, "Não localizado: " + i);
            }
            else{

                builder.setMessage("DOWNLOAD CONCLUIDO")
                        .setIcon(R.drawable.reposicao)
                        .setPositiveButton("ABRIR", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                txtLido = (view).findViewById(R.id.txt_lido);
                                txtLido.setText(leitura.lerArquivoTexto(getContext(), novoArquivo));
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                i=1;
            }




        }while (i!=1);


      return view;
    }

    public void loading(){
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        alertBuilder.setView(inflater.inflate(R.layout.custom_dialog_progress, null));
        alertBuilder.setCancelable(true);
        AlertDialog alertDialog = alertBuilder.create();
        alertDialog.show();
    }
}