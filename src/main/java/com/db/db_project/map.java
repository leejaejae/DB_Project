package com.db.db_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.skt.Tmap.TMapGpsManager;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapView;

import java.util.ArrayList;
import java.util.Map;

public class map extends AppCompatActivity {

    TMapView tmapview = null; //티맵 함수 정의
    TMapMarkerItem markerItem1 = new TMapMarkerItem();
    TMapPoint tMapPoint1 = new TMapPoint(35.8470514, 127.1344179);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);

        // 홈으로 돌아가기 버튼
        Button tohome = (Button) findViewById(R.id.tohome);
        tohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        // 위치 상세보기 버튼
        Button showdetail = (Button) findViewById(R.id.showdetail);
        showdetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), createCSV.class);
                Intent intent = new Intent(getApplicationContext(), ApiExplorer.class);
                startActivity(intent);
            }
        });

        // tmap
        LinearLayout linearLayoutTmap = (LinearLayout) findViewById(R.id.tmap);
        tmapview = new TMapView(this);  // 생성자 함수 정의
        linearLayoutTmap.addView(tmapview);  //레이아웃에 티맵 불러오기

        tmapview.setSKTMapApiKey("l7xxfc2f6ede42b7455aa65a07be71113ec4");  // 발급받은 키

        // 현재 위치로 표시되는 좌표의 위도, 경도
        final LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(map.this, new String[]{
                    android.Manifest.permission.ACCESS_FINE_LOCATION}, 0);
        }

        Location location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//        tmapview.setLocationPoint(127.1344179, 35.8470514); // 35.8470514, 127.1344179 공대 6호관
        tmapview.setCenterPoint(127.1344179, 35.8470514); // 35.8470514, 127.1344179 공대 6호관
        if (location != null) {
            double latitude = location.getLatitude(); // 위도
            double longitude = location.getLongitude();  // 경도
            tmapview.setCenterPoint(longitude, latitude, true);
       }

        // 마커 띄우기
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.poi_dot);
        bitmap = Bitmap.createScaledBitmap(bitmap, 50, 50,false);
        markerItem1.setIcon(bitmap);
        markerItem1.setPosition(0.5f, 1.0f); // 마커의 중심점을 중앙, 하단으로 설정
        markerItem1.setTMapPoint(tMapPoint1);
        tmapview.addMarkerItem("markerItem1" + 1, markerItem1);
        tmapview.MapZoomIn(); // 지도 한 단계 확대
    }
}
