package br.eti.tavares.trashpick.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.view.View;

import java.util.List;

import br.eti.tavares.trashpick.services.Imagens;
import br.eti.tavares.trashpick.R;
import br.eti.tavares.trashpick.model.Inventario;

public class AdapterGridViewInventario extends BaseAdapter {

    Context context;
    private LayoutInflater mInflater;
    private List<Inventario> lixoInventario;

    public AdapterGridViewInventario(Context context, List<Inventario> lixoInventario) {
        //Itens do listview
        this.lixoInventario = lixoInventario;

        //Objeto responsável por pegar o Layout do item.
        mInflater = LayoutInflater.from(context);
    }

    public int getCount() {

        return lixoInventario.size();
    }

    public Inventario getItem(int position) {

        return lixoInventario.get(position);
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
            view = mInflater.inflate(R.layout.gridview_item_inventario, null);


            //cria um item de suporte para não precisarmos sempre
            //inflar as mesmas informacoes
            itemHolder = new ItemSuporte();

            itemHolder.imagem = ((ImageView) view.findViewById(R.id.imgLixoInventario));

            //define os itens na view;
            view.setTag(itemHolder);
        } else {
            //se a view já existe pega os itens.
            itemHolder = (ItemSuporte) view.getTag();
        }

        //pega os dados da lista    "@tools:sample/avatars[" + Integer.toString(item.getFoto()) + "]"
        //e define os valores nos itens.
        Inventario inventario = lixoInventario.get(position);

        itemHolder.imagem.setImageResource(Imagens.getDrawable(inventario.getLixo().getImagem()));

        //retorna a view com as informações
        return view;
    }

    /**
     * Classe de suporte para os itens do layout.
     */
    private class ItemSuporte {

        ImageView imagem;

    }
}