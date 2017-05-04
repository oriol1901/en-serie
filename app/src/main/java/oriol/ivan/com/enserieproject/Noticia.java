package oriol.ivan.com.enserieproject;

/**
 * Created by AMS2-19 on 02/05/2017.
 */

public class Noticia {
    String titulo;
    String desc;
    //String foto;
    String url;

    public Noticia(String titulo, String desc, String url) {
        this.titulo = titulo;
        this.desc = desc;
        this.url = url;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    /*public void setFoto(String foto) {
        this.foto = foto;
    }*/

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getUrl() {
        return url;
    }

    public String getDesc() {
        return desc;
    }

    /*public String getFoto() {
        return foto;
    }*/
}
