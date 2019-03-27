package local.isaac.tt_2018_a031;

import android.content.Context;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;

public class CustomClusterRenderer extends DefaultClusterRenderer<paradaItem> {
    private final Context mContext;

    public CustomClusterRenderer(Context context, GoogleMap map, ClusterManager<paradaItem> clusterManager) {
        super(context, map, clusterManager);
        mContext = context;
    }

    @Override protected void onBeforeClusterItemRendered(paradaItem item, MarkerOptions markerOptions) {
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_parada));
    }

    protected void onBeforeClusterRendered(Cluster<paradaItem> paradaItemCluster, MarkerOptions markerOptions){
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_parada));
    }
}



