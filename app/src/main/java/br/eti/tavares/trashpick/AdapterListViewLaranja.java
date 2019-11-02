package br.eti.tavares.trashpick;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.ImageView;
import android.view.View;

import java.util.List;

public class AdapterListViewLaranja extends BaseAdapter {

    Context context;
    private LayoutInflater mInflater;
    private List<ItemBiblioteca> itemLaranja;

    public AdapterListViewLaranja(Context context, List<ItemBiblioteca> itemLaranja) {
        //Itens do listview
        this.itemLaranja = itemLaranja;

        //Objeto responsável por pegar o Layout do item.
        mInflater = LayoutInflater.from(context);
    }

    public int getCount() {

        return itemLaranja.size();
    }

    public ItemBiblioteca getItem(int position) {

        return itemLaranja.get(position);
    }

    public long getItemId(int position) {

        return position;
    }

    public View getView(int position, View view, ViewGroup parent) {

        if (mInflater == null) {
            mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        ItemSuporte itemHolder;

        //se a view estiver nula (nunca criada), inflamos o layout nela.
        if (view == null) {
            //infla o layout para podermos pegar as views
            view = mInflater.inflate(R.layout.listview_item_laranja, null);


            //cria um item de suporte para não precisarmos sempre
            //inflar as mesmas informacoes
            itemHolder = new ItemSuporte();
            itemHolder.nome = ((TextView) view.findViewById(R.id.txtLixoLaranja));
            itemHolder.imagem = ((ImageView) view.findViewById(R.id.imgLixoLaranja));

            //define os itens na view;
            view.setTag(itemHolder);
        } else {
            //se a view já existe pega os itens.
            itemHolder = (ItemSuporte) view.getTag();
        }

        //pega os dados da lista    "@tools:sample/avatars[" + Integer.toString(item.getFoto()) + "]"
        //e define os valores nos itens.
        ItemBiblioteca itemBiblioteca = itemLaranja.get(position);

        itemHolder.nome.setText(itemBiblioteca.getNome());
        itemHolder.imagem.setImageResource(itemBiblioteca.getImagem());

        //retorna a view com as informações
        return view;
    }

    /**
     * Classe de suporte para os itens do layout.
     */
    private class ItemSuporte {

        ImageView imagem;
        TextView nome;
    }
}