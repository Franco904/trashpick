package br.eti.tavares.trashpick;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class CustomInfoWindowGoogleMap implements GoogleMap.InfoWindowAdapter {

    private Context context;

    public CustomInfoWindowGoogleMap(Context ctx){
        context = ctx;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        View view = ((Activity)context).getLayoutInflater()
                .inflate(R.layout.info_window_maps, null);

        TextView nome_lixo = view.findViewById(R.id.txtLixo);
        TextView detalhes_lixo = view.findViewById(R.id.txtDetalhes);
        ImageView imagem_lixo = view.findViewById(R.id.imgLixo);

        nome_lixo.setText(marker.getSnippet());
        detalhes_lixo.setText(marker.getTitle());

        InfoWindowData infoWindowData = (InfoWindowData) marker.getTag();

        int imageId = Imagens.getDrawable(infoWindowData.getImagem());

                context.getResources().getIdentifier(infoWindowData.getImagem().toLowerCase(),
                "drawable", context.getPackageName());
        imagem_lixo.setImageResource(imageId);

//        hotel_tv.setText(infoWindowData.getHotel());
//        food_tv.setText(infoWindowData.getFood());
//        transport_tv.setText(infoWindowData.getTransport());

        return view;
    }

}