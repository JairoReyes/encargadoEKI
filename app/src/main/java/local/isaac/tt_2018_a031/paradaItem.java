package local.isaac.tt_2018_a031;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class paradaItem implements ClusterItem {
    private final LatLng mPosition;
    private final String mTitle;
    private String mSnippet;

    public paradaItem(LatLng position, String Title){
        mPosition = position;
        mTitle = Title;
    }

    public paradaItem(LatLng position, String Title, String Snippet) {
        mPosition = position;
        mTitle = Title;
        mSnippet = Snippet;
    }


    @Override
    public LatLng getPosition() {
        return mPosition;
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    @Override
    public String getSnippet() {
        return mSnippet;
    }
}
