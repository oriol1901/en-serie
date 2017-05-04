package oriol.ivan.com.enserieproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
//import android.support.v7.widget.ViewBoundsCheck;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import oriol.ivan.com.enserieproject.RSS.RssFeed;
import oriol.ivan.com.enserieproject.RSS.RssItem;
import oriol.ivan.com.enserieproject.RSS.RssReader;

/**
 * Created by AMS2-19 on 28/04/2017.
 */

public class NoticiasFragment extends Fragment{
    private RecyclerView noticias_view;
    private ProgressBar carga_noticias;
    private List<Noticia> noticiasLst;

    private List<Integer> colores;
    int color_contador = 0;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.noticias_layout, container, false);

        carga_noticias = (ProgressBar) v.findViewById(R.id.progressBarCircular);
        noticias_view = (RecyclerView) v.findViewById(R.id.lst_noticias);
        noticias_view.setVisibility(View.GONE);
        noticias_view.setLayoutManager(new LinearLayoutManager(getActivity()));

        colores = new ArrayList<>();
        colores.add(0, Color.parseColor("#FFF176"));
        colores.add(1, Color.parseColor("#9FA8DA"));
        colores.add(2, Color.parseColor("#A5D6A7"));
        colores.add(3, Color.parseColor("#EF9A9A"));

        getNoticias();

        return v;
    }

    public void getNoticias() {
        Network_news tarea = new Network_news();
        tarea.execute();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private class NoticiaHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        private TextView titulo, desc;
        private CardView card_noticia;

        private Noticia noticia;

        public NoticiaHolder(View itemView) {
            super(itemView);
            titulo = (TextView) itemView.findViewById(R.id.txtview_titulo);
            desc = (TextView) itemView.findViewById(R.id.txtview_desc);
            card_noticia = (CardView) itemView.findViewById(R.id.card_noticia);

            card_noticia.setBackgroundColor(colores.get(color_contador));
            if (color_contador == 3) color_contador = 0;
            ++color_contador;

        }

        public void bindNoticia(Noticia noticia) {
            this.noticia = noticia;
            titulo.setText(noticia.getTitulo());

            String desc_final = noticia.getDesc().substring(0, noticia.getDesc().indexOf('<'));
            desc.setText(desc_final + "..");
            card_noticia.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent web = new Intent(Intent.ACTION_VIEW);
            web.setData(Uri.parse(noticia.getUrl()));
            startActivity(web);
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private class NoticiaAdapter extends RecyclerView.Adapter<NoticiaHolder> {
        private List<Noticia> noticias;

        public NoticiaAdapter(List<Noticia> noticias) {
            this.noticias = noticias;
        }

        @Override
        public NoticiaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.card_noticia, parent, false);
            return new NoticiaHolder(view);
        }

        @Override
        public void onBindViewHolder(NoticiaHolder holder, int position) {
            Noticia noticia = noticias.get(position);
            holder.bindNoticia(noticia);
        }

        @Override
        public int getItemCount() {
            return noticias.size();
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    class Network_news extends AsyncTask<Void,NoticiaAdapter, Void> {
        NoticiaAdapter a;
        int progreso = 0;
        protected Void doInBackground(Void... pa) {


            RssFeed rss_noticia = null;
            try {
                URL url = new URL("http://rss.sensacine.com/actualidad/tv");
                rss_noticia = RssReader.read(url);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            ArrayList<Noticia> noticias = new ArrayList<>();
            ArrayList<RssItem> itemsRSS = rss_noticia.getRssItems();

            for (RssItem itemRSS : itemsRSS) {
                noticias.add(new Noticia(itemRSS.getTitle(), itemRSS.getDescription(), itemRSS.getLink()));
            }

            Log.i("POLLA", noticias.get(0).getDesc());
            a = new NoticiaAdapter(noticias);


            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            carga_noticias.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            carga_noticias.setVisibility(View.GONE);
            noticias_view.setVisibility(View.VISIBLE);
            noticias_view.setAdapter(a);
            noticias_view.getAdapter().notifyDataSetChanged();
        }
    }
}
