package br.eti.tavares.trashpick;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.ImageView;
import android.view.View;
import java.util.List;

public class AdapterListViewRank extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<PessoaRanking> itens;

    public AdapterListViewRank(Context context, List<PessoaRanking> itens) {
        //Itens do listview
        this.itens = itens;
        //Objeto responsável por pegar o Layout do item.
        mInflater = LayoutInflater.from(context);
    }
    public int getCount() {

        return itens.size();
    }
    public PessoaRanking getItem(int position) {

        return itens.get(position);
    }
    public long getItemId(int position) {

        return position;
    }

    public View getView(int position, View view, ViewGroup parent) {
        ItemSuporte itemHolder;
        //se a view estiver nula (nunca criada), inflamos o layout nela.
        if (view == null) {
            //infla o layout para podermos pegar as views
            view = mInflater.inflate(R.layout.listview_item, null);

            //cria um item de suporte para não precisarmos sempre
            //inflar as mesmas informacoes
            itemHolder = new ItemSuporte();
            itemHolder.nome = ((TextView) view.findViewById(R.id.txtNome));
            itemHolder.pontos = ((TextView) view.findViewById(R.id.textPontos));
            itemHolder.foto = ((ImageView) view.findViewById(R.id.imgPerfil));

            //define os itens na view;
            view.setTag(itemHolder);
        } else {
            //se a view já existe pega os itens.
            itemHolder = (ItemSuporte) view.getTag();
        }

        //pega os dados da lista    "@tools:sample/avatars[" + Integer.toString(item.getFoto()) + "]"
        //e define os valores nos itens.
        PessoaRanking item = itens.get(position);
        itemHolder.nome.setText(item.getNome());
        itemHolder.pontos.setText(Integer.toString(item.getPontos()) + " pontos");
        itemHolder.foto.setImageResource(R.drawable.ic_account_circle_black_24dp);

        //retorna a view com as informações
        return view;
    }

    /**
     * Classe de suporte para os itens do layout.
     */
    private class ItemSuporte {

        ImageView foto;
        TextView pontos;
        TextView nome;
    }
}