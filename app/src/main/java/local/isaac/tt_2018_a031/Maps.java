package local.isaac.tt_2018_a031;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import local.isaac.tt_2018_a031.PDO.AlertaPDO;
import local.isaac.tt_2018_a031.PDO.AlertaRegistro;
import local.isaac.tt_2018_a031.PDO.ZonaRoja;
import local.isaac.tt_2018_a031.PDO.ZonasRojasPDO;
import local.isaac.tt_2018_a031.controller.ParadasCercanasAdapter;
import local.isaac.tt_2018_a031.model.Parada;
import local.isaac.tt_2018_a031.viewmodel.AlertaViewModel;
import local.isaac.tt_2018_a031.viewmodel.MapsViewModel;

public class Maps extends AppCompatActivity implements OnMapReadyCallback,NavigationView.OnNavigationItemSelectedListener {

    private volatile boolean exit = true;
    private HashMap<Marker, Integer> markers = new HashMap<Marker, Integer>();
    private GoogleMap mMap;
    //ParadaRepository paradaRepository= new ParadaRepository(this);
    private MiThread2 hilo = new MiThread2();
    private AlertaViewModel alertaViewModel;
    ArrayList<String> latitudes = new ArrayList<>();
    ArrayList<String> longitudes = new ArrayList<String>();

    ArrayList<String> tipos_alertas = new ArrayList<String>();
    ArrayList<String> placas = new ArrayList<String>();
    ArrayList<String> nombres = new ArrayList<String>();
    ArrayList<String> id_trolebuses = new ArrayList<String>();
    ArrayList<String> fechas = new ArrayList<String>();
    ArrayList<String> id_alertas = new ArrayList<String>();
    private Marker marker = null;
    private Bitmap imageBitmap, resizedBitmap;

    public static final String preferencias = "MyPrefs" ;
    private int activar=0;
    SharedPreferences sharedpreferences;
    private int indice=0;
    private int contadorMarkers = 0;
    private List<Circle> circleList = new ArrayList<>();
    private MapsViewModel mapsViewModel;



    private ActionBarDrawerToggle actionBarDrawerToggle;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private SwitchCompat switchZonasRojas;
    private TextView nombreUsuario;
    private TextView emailUsuario;


    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerParadasCercanas;
    private ParadasCercanasAdapter adapterParadasCercanas;
    private List<Parada> paradasCercanas = new ArrayList<>();
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal_eki);
        //startService(new Intent(this,ServiceAlarmas.class));
        startService(new Intent(this,LocationService.class));

        sharedpreferences = getSharedPreferences(preferencias, Context.MODE_PRIVATE);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawerLayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView = findViewById(R.id.nav_view);

        SharedPreferences pref = getSharedPreferences(Inicio.preferencias, Context.MODE_PRIVATE);
        String nombre = pref.getString("nombre", null);
        String mail = pref.getString("expediente", null);

        View hView = navigationView.getHeaderView(0);
        nombreUsuario = hView.findViewById(R.id.nameTxt);
        emailUsuario = hView.findViewById(R.id.emailTxt);
        nombreUsuario.setText(nombre);
        emailUsuario.setText(mail);

        navigationView.setNavigationItemSelectedListener(this);

        switchZonasRojas = (SwitchCompat) navigationView.getMenu().findItem(R.id.switch_zonas).getActionView();

        switchZonasRojas.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if(isChecked){
                obtenerZonasRojas();
            }else{
                for (Circle circle : circleList)
                    circle.remove();
                circleList.clear();
            }
        });

        alertaViewModel = ViewModelProviders.of(this).get(AlertaViewModel.class);
        mapsViewModel = ViewModelProviders.of(this).get(MapsViewModel.class);
        //recyclerParadasCercanas = (RecyclerView) findViewById(R.id.recyclerview_paradas);
        linearLayoutManager = new LinearLayoutManager(this);
        //adapterParadasCercanas = new ParadasCercanasAdapter(this,paradasCercanas);
        //recyclerParadasCercanas.setLayoutManager(linearLayoutManager);
        //recyclerParadasCercanas.setAdapter(adapterParadasCercanas);

        verificarPermisos();



    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawerLayout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item){
        int id = item.getItemId();

        switch(id){
            case R.id.registros:
                Intent intent = new Intent(Maps.this,Registros.class);
                startActivity(intent);
                break;
            case R.id.switch_zonas:
                return false;
            case R.id.cerrar_sesion:
                stopService(new Intent(Maps.this,ServiceAlarmas.class));
                Toast.makeText(this, "Sesión terminada", Toast.LENGTH_SHORT).show();
                SharedPreferences pref = getSharedPreferences(Inicio.preferencias, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.clear();
                editor.apply();
                Intent intent1 = new Intent(Maps.this,SplashActivity.class);
                startActivity(intent1);
                break;
            default:
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawerLayout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }


    public void permisosBien() {
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (status == ConnectionResult.SUCCESS) {

            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        } else {
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, (Activity) getApplicationContext(), 10);
        }
        //cargarLista();
    }

    public void verificarPermisos() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) && ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION) && ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {

            } else {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.SEND_SMS},
                        0);
            }
        }
        else{
            permisosBien();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 0: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    permisosBien();

                } else {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.SEND_SMS}, 0);
                }
                return;
            }
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setRotateGesturesEnabled(false);
        activar=1;


        //hilo = new MiThread2();
        if(hilo.getState() == Thread.State.NEW)
            hilo.start();
        /*
        try {
            miUbicacion();
        }
        catch (Exception e){
            Toast toast = Toast.makeText(getApplicationContext(), R.string.activar_ubicacion, Toast.LENGTH_SHORT);
            toast.show();
            finish();
        }*/
        dibujarRuta();
        int a = 0;

        /*
        final List<Parada> parad = paradaRepository.obtenerParadasPorIdParadas();
        for (Parada parada : parad){
            LatLng paradas = new LatLng(parada.getUbicacionLatitud(), parada.getUbicacionLongitud());
            mMap.addMarker(new MarkerOptions().position(paradas).title(parada.getNombre()).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_parada)));
            /*distanciaEntreDosPuntos[a][0] = Double.valueOf(a + 1);
            distanciaEntreDosPuntos[a][1] = Math.pow(parada.getUbicacionLatitud() - lat, 2) + Math.pow(parada.getUbicacionLongitud() - lat, 2);
            a++;
        }*/








        LatLng coordenada = new LatLng(19.501391, -99.142640);



        /*Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(),getResources().getIdentifier("descarga", "drawable", getPackageName()));
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, 80, 80, false);

        Marker m= mMap.addMarker(new MarkerOptions().position(coordenada).title("Alarma de prueba").icon(BitmapDescriptorFactory.fromBitmap(resizedBitmap)));
        markers.put(m,0);*/
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if(markers.get(marker) != null) {



                    //Toast.makeText(getApplicationContext(), "Id del marcador: " + markers.get(marker), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Maps.this, Ver_Alarma.class);

                    intent.putExtra("fecha",fechas.get(markers.get(marker)));
                    intent.putExtra("placas",placas.get(markers.get(marker)));
                    intent.putExtra("id_alerta",id_alertas.get(markers.get(marker)));
                    intent.putExtra("tipo_alerta",tipos_alertas.get(markers.get(marker)));
                    intent.putExtra("id_trolebus",id_trolebuses.get(markers.get(marker)));
                    intent.putExtra("nombre",nombres.get(markers.get(marker)));

                    fechas.remove(markers.get(marker));
                    placas.remove(markers.get(marker));
                    id_alertas.remove(markers.get(marker));
                    tipos_alertas.remove(markers.get(marker));
                    id_trolebuses.remove(markers.get(marker));
                    nombres.remove(markers.get(marker));
                    latitudes.remove(markers.get(marker));
                    longitudes.remove(markers.get(marker));

                    markers.remove(marker);
                    marker.remove();
                    startActivityForResult(intent, 0);


                }
                return false;
            }
        });

        //googleMap.addMarker(new MarkerOptions() .icon(BitmapDescriptorFactory.fromBitmap(resizedBitmap)));
        CameraUpdate miUbicacion = CameraUpdateFactory.newLatLngZoom(coordenada, 15);
        mMap.animateCamera(miUbicacion);
    }

    private void obtenerZonasRojas() {
        mapsViewModel.getZonaRojaResponse().observe(Maps.this, (ZonasRojasPDO zonasRojasResponse) -> {
            if (zonasRojasResponse.getZonaRojaResponse() != null) {
                if (zonasRojasResponse.getZonaRojaResponse().getZonaRojaList().size() > 0) {
                    List<ZonaRoja> zonaRojaList = new ArrayList<>();
                    zonaRojaList.addAll(zonasRojasResponse.getZonaRojaResponse().getZonaRojaList());
                    mapsViewModel.setZonaRojaResponse(null);
                    calcularZonasRojas(zonaRojaList);
                } else {
                    Toast toast1 = Toast.makeText(getApplicationContext(), getResources().getString(R.string.sin_zonas_rojas), Toast.LENGTH_SHORT);
                    toast1.show();
                    mapsViewModel.setZonaRojaResponse(null);
                    return;
                }
            } else if (zonasRojasResponse.getError().getText().startsWith("Fallo en la conexión")) {
                Toast fail = Toast.makeText(Maps.this, zonasRojasResponse.getError().getText(), Toast.LENGTH_SHORT);
                fail.show();
                mapsViewModel.setZonaRojaResponse(null);
                return;
            } else {
                Toast httpError = Toast.makeText(Maps.this, zonasRojasResponse.getError().getText(), Toast.LENGTH_SHORT);
                httpError.show();
                mapsViewModel.setZonaRojaResponse(null);
                return;
            }
        });
    }

    public void calcularZonasRojas(List<ZonaRoja> zonaRojaList){

        List<List<ZonaRoja>> zonasRojas = new ArrayList<>();

        if(zonaRojaList.size() > 1){
            for(int i = 0; i < zonaRojaList.size() - 1; i++){
                List<ZonaRoja> zonaRojaList1 = new ArrayList<>();
                zonaRojaList1.add(zonaRojaList.get(i));
                Location locationA = new Location("Punto A");
                locationA.setLatitude(Double.valueOf(zonaRojaList.get(i).getLatitud()));
                locationA.setLongitude(Double.valueOf(zonaRojaList.get(i).getLongitud()));

                for(int j = i + 1; j < zonaRojaList.size(); j++){
                    Location locationB = new Location("Punto B");
                    locationB.setLatitude(Double.valueOf(zonaRojaList.get(j).getLatitud()));
                    locationB.setLongitude(Double.valueOf(zonaRojaList.get(j).getLongitud()));
                    float distanceTo = locationA.distanceTo(locationB);

                    if(distanceTo <= 100)
                        zonaRojaList1.add(zonaRojaList.get(j));
                }
                zonasRojas.add(zonaRojaList1);
            }

            dibujarZonasRojas(zonasRojas);

        }else if(zonaRojaList.size() == 1){
            List<ZonaRoja> zonaRojaList1 = new ArrayList<>();
            zonaRojaList1.add(zonaRojaList.get(0));
            zonasRojas.add(zonaRojaList1);

            dibujarZonasRojas(zonasRojas);
        }
    }

    public void dibujarZonasRojas(List<List<ZonaRoja>> zonasRojas){

        for(int i = 0; i < zonasRojas.size(); i++){

            int j = zonasRojas.get(i).size();
            double latitud = Double.valueOf(zonasRojas.get(i).get(0).getLatitud());
            double longitud = Double.valueOf(zonasRojas.get(i).get(0).getLongitud());

            CircleOptions circleOptions = new CircleOptions();
            circleOptions.center(new LatLng(latitud,longitud));
            circleOptions.radius(100);

            if(j >= 1 && j <= 3 ){
                circleOptions.fillColor(Color.parseColor("#A8FFD200"));
                circleOptions.strokeColor(Color.parseColor("#A8FFD200"));
            }else if(j >= 4 && j <= 6){
                circleOptions.fillColor(Color.parseColor("#A8FFA500"));
                circleOptions.strokeColor(Color.parseColor("#A8FFA500"));
            }else if(j >= 7 && j <= 9) {
                circleOptions.fillColor(Color.parseColor("#A8FF7800"));
                circleOptions.strokeColor(Color.parseColor("#A8FF7800"));
            }else if(j >= 10 && j <= 12){
                circleOptions.fillColor(Color.parseColor("#A8FF4B00"));
                circleOptions.strokeColor(Color.parseColor("#A8FF4B00"));
            }else if(j >= 13){
                circleOptions.fillColor(Color.parseColor("#A8FF1E00"));
                circleOptions.strokeColor(Color.parseColor("#A8FF1E00"));
            }

            Circle circle = mMap.addCircle(circleOptions);
            circleList.add(circle);

        }

    }

    private void dibujarRuta(){

        ArrayList puntos = new ArrayList();
        PolylineOptions polylineOptions = new PolylineOptions();


        double [][] lat_lon = {{19.495308, -99.136121},{19.495356, -99.136335},{19.495306, -99.136558},{19.495351, -99.136762},
                {19.495460, -99.136834},{19.495554, -99.136869},{19.495895, -99.136805},{19.496186, -99.136794},
                {19.496756, -99.136687},{19.500438, -99.135961},{19.500654, -99.135915},{19.503001, -99.135471},
                {19.503219, -99.135435},{19.503296, -99.135399},{19.503470, -99.135253},{19.503371, -99.134661},
                {19.503429, -99.134604},{19.503489, -99.134584},{19.503560, -99.134592},{19.503626, -99.134616},
                {19.504357, -99.138863},{19.504359, -99.138940},{19.504351, -99.139004},{19.504382, -99.139043},
                {19.504404, -99.139100},{19.504400, -99.139161},{19.504398, -99.139204},{19.504431, -99.139281},
                {19.504952, -99.142259},{19.505149, -99.143418},{19.505140, -99.143600},{19.505272, -99.144400},
                {19.505317, -99.144606},{19.505461, -99.144997},{19.505567, -99.145207},{19.506300, -99.146268},
                {19.506528, -99.146735},{19.506594, -99.147060},{19.506607, -99.147464},{19.506601, -99.147601},
                {19.505174, -99.151361},{19.505154, -99.151376},{19.505126, -99.151374},{19.493800, -99.146883},
                {19.493737, -99.146734},{19.493691, -99.146652},{19.493659, -99.146549},{19.493659, -99.146443},
                {19.493678, -99.146381},{19.493720, -99.146325},{19.493801, -99.146230},{19.493930, -99.146152},
                {19.493957, -99.146146},{19.493970, -99.146149},{19.495165, -99.146644},{19.495690, -99.146800},
                {19.505011, -99.150491},{19.505162, -99.150545},{19.505186, -99.150543},{19.505522, -99.149645},
                {19.505549, -99.149333},{19.505574, -99.149208},{19.506377, -99.147114},{19.506313, -99.146728},
                {19.506268, -99.146569},{19.506177, -99.146383},{19.505552, -99.145475},{19.505480, -99.145370},
                {19.505356, -99.145143},{19.505255, -99.144916},{19.505170, -99.144658},{19.504994, -99.143745},
                {19.504887, -99.143352},{19.504191, -99.139382},{19.504190, -99.139322},{19.504200, -99.139255},
                {19.504197, -99.139233},{19.504169, -99.139198},{19.504148, -99.139144},{19.504150, -99.139086},
                {19.504166, -99.139039},{19.504114, -99.138911},{19.503547, -99.135705},{19.503475, -99.135664},
                {19.503420, -99.135643},{19.503338, -99.135625},{19.503263, -99.135632},{19.502937, -99.135691},
                {19.502730, -99.135691},{19.496323, -99.136923},{19.495984, -99.137034},{19.495642, -99.137120},
                {19.495582, -99.137165},{19.495525, -99.137239},{19.495474, -99.137280},{19.495465, -99.137318},
                {19.496095, -99.139939},{19.497657, -99.143353},{19.498015, -99.144108},{19.499131, -99.146847},
                {19.499109, -99.146894},{19.498939, -99.146989},{19.498897, -99.146972},{19.497793, -99.144289},
                {19.495890, -99.140134},{19.495684, -99.139409},{19.494588, -99.134659},{19.494628, -99.134567},
                {19.494688, -99.134520},{19.494747, -99.134520},{19.494837, -99.134590},{19.495093, -99.135681},
                {19.495179, -99.135726},{19.495265, -99.135844},{19.495308, -99.136121}};

        for(int x=0; x< lat_lon.length;x++) {
            puntos.add(new LatLng(lat_lon[x][0], lat_lon[x][1]));
        }

        polylineOptions.addAll(puntos);
        polylineOptions.width(15);
        polylineOptions.color(Color.argb(255,144,12,63));
        polylineOptions.geodesic(true);


        mMap.addPolyline(polylineOptions);
    }


/*    private MyBroadcastReceiver myBroadcastReceiver;

    @Override
    public void onResume(){
        myBroadcastReceiver = new MyBroadcastReceiver();
        final IntentFilter intentFilter = new IntentFilter("enviar");
        LocalBroadcastManager.getInstance(this).registerReceiver(myBroadcastReceiver,intentFilter);
    }

    @Override
    public void onPause(){
        if(myBroadcastReceiver !=null)
            LocalBroadcastManager.getInstance(this).unregisterReceiver(myBroadcastReceiver);
        myBroadcastReceiver = null;
    }

    public class MyBroadcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle b = intent.getExtras();
            double some = b.getDouble("doblesito");
            Toast.makeText(Maps.this,String.valueOf(some),Toast.LENGTH_SHORT).show();
        }
    }
*/

    public void onPause(){
        super.onPause();
        exit = false;
        startService(new Intent(Maps.this,ServiceAlarmas.class));
    }

    public void onStop(){
        super.onStop();
        exit = false;
        //if(hilo.isAlive())
        //if(hilo != null)
            //hilo.interrupt();
        //startService(new Intent(this,ServiceAlarmas.class));
    }

    public void onDestroy(){
        super.onDestroy();
        //if(hilo.isAlive())
        exit = false;
        //startService(new Intent(this,ServiceAlarmas.class));
    }


    public void onResume(){
        super.onResume();
        exit = true;
        if(activar == 1) {
            if (hilo.isAlive()) {
                hilo.interrupt();
            }
            hilo = new MiThread2();
            hilo.start();
        }
        stopService(new Intent(Maps.this,ServiceAlarmas.class));
    }



    class MiThread2 extends Thread {
        @Override
        public void run() {

            //stopService(new Intent(Maps.this,ServiceAlarmas.class));
            while(exit) {
                //System.out.println("Esto es el hilo");
                alertaViewModel.setAlertaResponse(null);
                alertaViewModel.getAlertaResponse().observe(Maps.this, (AlertaPDO alertaResponse) -> {
                    procesarRespuestaAlerta(alertaResponse);
                });
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }
    }

    public void procesarRespuestaAlerta(AlertaPDO alertaResponse){
        if(alertaResponse.getAlertaResponse() != null){
            //Marker marker;
            List<AlertaRegistro> alertas = alertaResponse.getAlertaResponse().getListaAlerta();
            if(alertas != null) {
                for (AlertaRegistro alerta : alertas){

                    //if(alerta.getTipo_alerta().equals("Emergencia"))
                      //  icon = BitmapFactory.decodeResource(getResources(), R.drawable);

                    //if(!latitudes.isEmpty()) {


                        if (!id_alertas.contains(alerta.getId_alerta())) {
                                saveDataAlertas(alerta.getLatitud(), alerta.getLongitud(),alerta.getTipo_alerta(),alerta.getNombre(),alerta.getPlaca(),alerta.getFecha(),alerta.getId_trolebus(),alerta.getId_alerta());

                                if(alerta.getTipo_alerta().equals("Emergencia"))
                                    imageBitmap = BitmapFactory.decodeResource(getResources(),getResources().getIdentifier("emergencia", "drawable", getPackageName()));
                                else if(alerta.getTipo_alerta().equals("Panico"))
                                    imageBitmap = BitmapFactory.decodeResource(getResources(),getResources().getIdentifier("panico", "drawable", getPackageName()));
                                else if(alerta.getTipo_alerta().equals("Vial"))
                                    imageBitmap = BitmapFactory.decodeResource(getResources(),getResources().getIdentifier("vial", "drawable", getPackageName()));
                                else
                                    imageBitmap = BitmapFactory.decodeResource(getResources(),getResources().getIdentifier("averia", "drawable", getPackageName()));

                                resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, 80, 80, false);

                                LatLng coordenada = new LatLng(Double.parseDouble(alerta.getLatitud()), Double.parseDouble(alerta.getLongitud()));
                                marker = mMap.addMarker(new MarkerOptions().position(coordenada).title(alerta.getTipo_alerta()).icon(BitmapDescriptorFactory.fromBitmap(resizedBitmap)));
                                //marker = mMap.addMarker(new MarkerOptions().position(coordenada).title(alerta.getTipo_alerta()).icon(BitmapDescriptorFactory.fromResource(R.drawable.emergencia)));
                                markers.put(marker, contadorMarkers);
                                contadorMarkers++;
                        }
                    //}
                }
            }
            else {
                contadorMarkers = 0;
                Toast.makeText(this, "No hay registros", Toast.LENGTH_SHORT).show();
                latitudes.clear();
                longitudes.clear();
                id_trolebuses.clear();
                fechas.clear();
                nombres.clear();
                placas.clear();
                tipos_alertas.clear();
                id_alertas.clear();
            }
        }
        else{
            Toast httpError = Toast.makeText(this, "Fallo al recibir alertas", Toast.LENGTH_SHORT);
            httpError.show();
            alertaViewModel.setAlertaResponse(null);
        }
    }

    public void saveDataAlertas(String latitud,String longitud,String tipo_alerta, String nombre, String placa, String fecha,String id_trolebus,String id_alerta){

        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putString("latitud", latitud);
        editor.putString("longitud",longitud);
        editor.putBoolean("activity_executed", true);
        editor.commit();
        latitudes.add(latitud);
        longitudes.add(longitud);
        tipos_alertas.add(tipo_alerta);
        nombres.add(nombre);
        placas.add(placa);
        fechas.add(fecha);
        id_trolebuses.add(id_trolebus);
        id_alertas.add(id_alerta);


    }


}
