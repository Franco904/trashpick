package br.eti.tavares.trashpick;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class DisponivelFragment extends Fragment {

    private List<Objetivo> objetivos = new ArrayList<>();
    private OnListFragmentInteractionListener mListener;

    public DisponivelFragment() {
        // Required empty public constructor
    }

    private void GetObjetivosDisponiveis(){

        objetivos.add(0, new Objetivo("A Papelada", "Ache e recolha 10 folhas de papel - 20 pts", R.drawable.jornal));
        objetivos.add(1, new Objetivo("Notícias do Dia!", "Ache e recolha 1 jornal - 10 pts", R.drawable.folhapapel));
        objetivos.add(2, new Objetivo("Algodão Doce", "Ache e recolha 20 algodões - 30 pts", R.drawable.algodao));
        objetivos.add(3, new Objetivo("O Salvador Animal", "Ache e recolha 5 embalagens pet - 20 pts", R.drawable.embalagempet));
        objetivos.add(4, new Objetivo("Luvas de Ouro", "Ache e recolha 2 luvas de borracha - 50 pts", R.drawable.luvasborracha));
        objetivos.add(5, new Objetivo("O Robô", "Ache e recolha 5 lixos eletrônicos - 40 pts", R.drawable.lixoeletronico));
        objetivos.add(6, new Objetivo("Uma Parada para o Café", "Ache e recolha 2 copos de isopor - 30 pts", R.drawable.copoisopor));
        objetivos.add(7, new Objetivo("Rei Esponja","Ache e recolha 3 esponjas - 40 pts" , R.drawable.esponja));
        objetivos.add(8, new Objetivo("Senhor do Ferro Velho", "Ache e recolha 10 latas de aço - 30 pts", R.drawable.lataaco));
        objetivos.add(9, new Objetivo("Missão Olhos de Vidro", "Ache e recolha 2 garrafas de vidro - 35 pts", R.drawable.garrafavidro));
        objetivos.add(10, new Objetivo("Energizado", "Ache e recolha 4 pilhas ou baterias - 45 pts", R.drawable.pilhabateria));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.disponiveis_fragment, container, false);

        this.GetObjetivosDisponiveis();

        ListView objetivosDisponiveis = (ListView) rootView.findViewById(R.id.listDisponiveis);
        AdapterListViewObjetivos adapterobjetivos = new AdapterListViewObjetivos(this.getContext(), objetivos);
        objetivosDisponiveis.setAdapter(adapterobjetivos);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnListFragmentInteractionListener) {
//            mListener = (OnListFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnListFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    @Override
    public String toString() {
       return "Disponíveis";
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Objetivo item);
    }
}
