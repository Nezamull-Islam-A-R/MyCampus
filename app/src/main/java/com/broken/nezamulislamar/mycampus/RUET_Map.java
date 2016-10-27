package com.broken.nezamulislamar.mycampus;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import Modules.DirectionFinder;
import Modules.DirectionFinderListener;
import Modules.Route;

public class RUET_Map extends FragmentActivity implements OnMapReadyCallback, DirectionFinderListener {

    private GoogleMap mMap;
    private Button btnFindPath;
    private EditText etOrigin;
    private EditText etDestination;
    private List<Marker> originMarkers = new ArrayList<>();
    private List<Marker> destinationMarkers = new ArrayList<>();
    private List<Polyline> polylinePaths = new ArrayList<>();
    private ProgressDialog progressDialog;
    Button hy,sat,ter;
    String Orname,Destname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ruet__map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        btnFindPath = (Button) findViewById(R.id.btnFindPath);
        etOrigin = (EditText) findViewById(R.id.etOrigin);
        etDestination = (EditText) findViewById(R.id.etDestination);
        //hy = (Button)findViewById(R.id.hybrid);
        //ter = (Button)findViewById(R.id.terrain);
        //sat = (Button)findViewById(R.id.sat);
        btnFindPath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequest();
            }
        });
    }


    private void sendRequest() {
        Orname = etOrigin.getText().toString();;
        Destname = etDestination.getText().toString();

        String origin = getLatLongOrgin(Orname);
        String destination = getLatLongDest(Destname);

        //modify to string as latLong value
        if (origin.isEmpty()) {
            Toast.makeText(this, "Please enter origin address!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (destination.isEmpty()) {
            Toast.makeText(this, "Please enter destination address!", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            new DirectionFinder(this, origin, destination).execute();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private String getLatLongOrgin(String orname) {
        String DlatLong = "24.363451, 88.628544";
        switch (orname){
            case "ruet":
                DlatLong = "24.363571,88.628375";
                break;
            case "civil":
                DlatLong = "24.363789,88.627111";
                break;
            case "cse":
                DlatLong = "24.364113,88.629780";
                break;
            case "zia":
                DlatLong = "24.365357,88.626476";
                break;
            case "tin":
                DlatLong = "24.366773,88.626951";
                break;
            case "gym":
                DlatLong = "24.366570,88.627369";
                break;
            case "selim":
                DlatLong = "24.368288,88.626441";
                break;
            case "bongo":
                DlatLong = "24.367580,88.626677";
                break;
            case "addbuiling":
                DlatLong = "24.363589,88.628506";
                break;
            case "auditorium":
                DlatLong = "24.363565,88.629563";
                break;
            case "cafe":
                DlatLong = "24.363262,88.629738";
                break;
            case "lh":
                DlatLong = "24.370989,88.624984";
                break;
            case "lecture's area":
                DlatLong = "24.370259,88.626907";
                break;
            case "canteen":
                DlatLong = "24.368269,88.626801";
                break;
            case "bank":
                DlatLong = "24.362988,88.627750";
                break;
            case "booth":
                DlatLong = "24.362625,88.628903";
                break;
            case "play ground":
                DlatLong = "24.366980,88.627090";
                break;
            default:
                DlatLong = "24.363451,88.628544";
                break;

        }
        return (DlatLong);
    }

    private String getLatLongDest(String destname) {
        String latLong = "24.364365, 88.629853";
        switch (destname){
            case "ruet":
                latLong = "24.363571,88.628375";
                break;
            case "civil":
                latLong = "24.363789,88.627111";
                break;
            case "cse":
                latLong = "24.364113,88.629780";
                break;
            case "zia":
                latLong = "24.365357,88.626476";
                break;
            case "tin":
                latLong = "24.366773,88.626951";
                break;
            case "gym":
                latLong = "24.366570,88.627369";
                break;
            case "selim":
                latLong = "24.368288,88.626441";
                break;
            case "bongobondhu":
                latLong = "24.367580,88.626677";
                break;
            case "addbuiling":
                latLong = "24.363589,88.628506";
                break;
            case "auditorium":
                latLong = "24.363565,88.629563";
                break;
            case "cafe":
                latLong = "24.363262,88.629738";
                break;
            case "lh":
                latLong = "24.370989,88.624984";
                break;
            case "lecture's area":
                latLong = "24.370259,88.626907";
                break;
            case "canteen":
                latLong = "24.368269,88.626801";
                break;
            case "bank":
                latLong = "24.362988,88.627750";
                break;
            case "booth":
                latLong = "24.362625,88.628903";
                break;
            case "play ground":
                latLong = "24.366980,88.627090";
                break;
            default:
                latLong = "24.364365,88.629853";
                break;

        }
        return (latLong);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //button change view

        //--------

        LatLng civil = new LatLng(24.363789,88.627111);
        mMap.addMarker(new MarkerOptions().position(civil).title("Civil Building")).setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
        //civil 24.363789, 88.627111
        LatLng cse = new LatLng(24.364113,88.629780);
        mMap.addMarker(new MarkerOptions().position(cse).title("CSE Building")).setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
        //cse 24.364365, 88.629853
        LatLng adbuil = new LatLng(24.363589,88.628506);
        mMap.addMarker(new MarkerOptions().position(adbuil).title("Administration Building")).showInfoWindow();
        //adbuild 24.363589, 88.628506
        LatLng audi = new LatLng(24.363565,88.629563);
        mMap.addMarker(new MarkerOptions().position(audi).title("Auditorium RUET")).showInfoWindow();
        LatLng selim = new LatLng(24.368288,88.626441);
        mMap.addMarker(new MarkerOptions().position(selim).title("Selim Hall")).setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        //selim 24.368288, 88.626441
        LatLng bongo = new LatLng(24.367580,88.626677);
        mMap.addMarker(new MarkerOptions().position(bongo).title("Bongobondhu Hall")).setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        //bongo 24.367580, 88.626677
        LatLng shahid = new LatLng(24.366925,88.626055);
        mMap.addMarker(new MarkerOptions().position(shahid).title("Shahidul Islam Hall")).setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        //shahid 24.366925, 88.626055
        LatLng tin = new LatLng(24.366773,88.626951);
        mMap.addMarker(new MarkerOptions().position(tin).title("Tin-shed Hall")).setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        //tin 24.366773, 88.626951
        LatLng hamid = new LatLng(24.366138,88.626227);
        mMap.addMarker(new MarkerOptions().position(hamid).title("Hamid Hall")).setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        //hamid 24.366138, 88.626227
        LatLng zia = new LatLng(24.365357,88.626476);
        mMap.addMarker(new MarkerOptions().position(zia).title("Zia Hall")).setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        //zia 24.366138, 88.626227
        LatLng cafe = new LatLng(24.363262,88.629738);
        mMap.addMarker(new MarkerOptions().position(cafe).title("Cafe RUET")).showInfoWindow();
        LatLng lh = new LatLng(24.370989,88.624984);
        mMap.addMarker(new MarkerOptions().position(lh).title("Ladis Hostel RUET")).showInfoWindow();

        LatLng lect = new LatLng(24.370259,88.626907);
        mMap.addMarker(new MarkerOptions().position(lect).title("Lecturar's area")).showInfoWindow();
        LatLng canteen = new LatLng(24.368269,88.626801);
        mMap.addMarker(new MarkerOptions().position(canteen).title("Canteen")).showInfoWindow();
        LatLng booth = new LatLng(24.362625,88.628903);
        mMap.addMarker(new MarkerOptions().position(booth).title("Rupali Bank booth")).showInfoWindow();
        LatLng bank = new LatLng(24.362988,88.627750);
        mMap.addMarker(new MarkerOptions().position(bank).title("Rupali Bank")).showInfoWindow();
        LatLng play = new LatLng(24.366980,88.627090);
        mMap.addMarker(new MarkerOptions().position(play).title("Play Ground")).showInfoWindow();

        //----------
        LatLng ruet = new LatLng(24.363571, 88.628375);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ruet, 15));
        originMarkers.add(mMap.addMarker(new MarkerOptions()
                .title("RUET")
                .position(ruet)));

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
    }


    @Override
    public void onDirectionFinderStart() {
        progressDialog = ProgressDialog.show(this, "Please wait.",
                "Finding direction..!", true);

        if (originMarkers != null) {
            for (Marker marker : originMarkers) {
                marker.remove();
            }
        }

        if (destinationMarkers != null) {
            for (Marker marker : destinationMarkers) {
                marker.remove();
            }
        }

        if (polylinePaths != null) {
            for (Polyline polyline:polylinePaths ) {
                polyline.remove();
            }
        }
    }

    @Override
    public void onDirectionFinderSuccess(List<Route> routes) {
        progressDialog.dismiss();
        polylinePaths = new ArrayList<>();
        originMarkers = new ArrayList<>();
        destinationMarkers = new ArrayList<>();

        for (Route route : routes) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(route.startLocation, 16));
            ((TextView) findViewById(R.id.tvDuration)).setText(route.duration.text);
            ((TextView) findViewById(R.id.tvDistance)).setText(route.distance.text);
            originMarkers.add(mMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA))
                    .title(Orname)
                    .position(route.startLocation)));
            destinationMarkers.add(mMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                    .title(Destname)
                    .position(route.endLocation)));

            /*originMarkers.add(mMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.start_blue))
                    .title(route.startAddress)
                    .position(route.startLocation)));
            destinationMarkers.add(mMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.end_green))
                    .title(route.endAddress)
                    .position(route.endLocation)));
           */
            PolylineOptions polylineOptions = new PolylineOptions().
                    geodesic(true).
                    color(Color.BLUE).
                    width(10);

            for (int i = 0; i < route.points.size(); i++)
                polylineOptions.add(route.points.get(i));

            polylinePaths.add(mMap.addPolyline(polylineOptions));
        }
    }
}