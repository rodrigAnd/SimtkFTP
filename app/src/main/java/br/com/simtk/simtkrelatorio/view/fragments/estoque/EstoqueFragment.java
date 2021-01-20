package br.com.simtk.simtkrelatorio.view.fragments.estoque;

import android.app.DatePickerDialog;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.example.flatdialoglibrary.dialog.FlatDialog;
import br.com.simtk.simtkrelatorio.R;
import br.com.simtk.simtkrelatorio.util.DadosPreference;
import br.com.simtk.simtkrelatorio.view.fragments.dialog.DataDialog;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EstoqueFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EstoqueFragment extends Fragment {

    //variavel botao continuar
    private Button btn_continuar, btn_confirmar;
    private EditText ed_data_inicial, ed_data_final;
    private AlertDialog alertDialog, alertDialog1;
    private String[] items = {"DATA", "GERAL"};
    private String solicitacao = "rodrigo";
    DatePickerDialog.OnDateSetListener dataPicker;
    private RecyclerView rc_estoque;
    private View btn_reposicao;


    public EstoqueFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment EstoqueFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EstoqueFragment newInstance() {
        EstoqueFragment fragment = new EstoqueFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_estoque, container, false);

        btn_reposicao = view.findViewById(R.id.btn_reposicao);

          btn_reposicao.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  DadosPreference dados = new DadosPreference();
                  dados.dadosOp(getContext(), "OP2", "01");
                  final FlatDialog flatDialog = new FlatDialog(getActivity());
                  flatDialog.setBackgroundColor(android.R.color.background_light);
                  flatDialog.setTitle("REPOSIÇÃO")
                          .setFirstButtonText("DATA")
                          .setSecondButtonText("GERAL")
                          .setThirdButtonText("CANCELAR")
                          .withFirstButtonListner(new View.OnClickListener() {
                              @Override
                              public void onClick(View view) {

                                  Fragment data = DataDialog.newInstance();
                                  openFragment(data);
                                  DadosPreference dados = new DadosPreference();
                                  dados.dadosOp(getContext(), "OP3", "01");
                                  flatDialog.dismiss();

                              }
                          })
                          .withSecondButtonListner(new View.OnClickListener() {
                              @Override
                              public void onClick(View view) {
                                  solicitacao = "02";
                                  Log.d("dados solicitação ", solicitacao.toString());

                                  flatDialog.dismiss();

                              }
                          })
                          .withThirdButtonListner(new View.OnClickListener() {
                              @Override
                              public void onClick(View v) {
                                  flatDialog.dismiss();
                              }
                          }) .show();
              }
          });

      // Inflate the layout for this fragment
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
