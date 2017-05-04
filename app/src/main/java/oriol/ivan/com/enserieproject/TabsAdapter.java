package oriol.ivan.com.enserieproject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


public class TabsAdapter extends FragmentStatePagerAdapter {

    int tabs;

    public TabsAdapter(FragmentManager fm, int tabs) {
        super(fm);
        this.tabs = tabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                PerfilFragment perfil = new PerfilFragment();
                return perfil;
            case 1:
                PrincipalFragment principal = new PrincipalFragment();
                return principal;
            case 2:
                SocialFragment social = new SocialFragment();
                return social;
            case 3:
                NoticiasFragment noticias = new NoticiasFragment();
                return noticias;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabs;
    }
}
