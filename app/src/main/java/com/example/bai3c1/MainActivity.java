package com.example.bai3c1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.adapter.NhaHangAdapter;
import com.example.modl.NhaHang;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvNhaHang;
    NhaHangAdapter nhaHangAdapter;
    NhaHang selectedNhaHang = null;
    ArrayList<NhaHang> dsNguon = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menuCall)
        {
            XuLyGoiDien();
        }
        else if(item.getItemId()==R.id.menuSms)
        {
            XuLyNhanTin();
        }
        return super.onContextItemSelected(item);
    }

    private void XuLyNhanTin() {
        SmsManager smsManager = SmsManager.getDefault();
        Intent msgSent = new Intent("ACTION_MSG_SENT");
        PendingIntent pendingIntentmessage = PendingIntent.getBroadcast(MainActivity.this,0,msgSent,0);
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int kq = getResultCode();
                if(kq == Activity.RESULT_OK)
                {
                    Toast.makeText(MainActivity.this,"Da gui toi "+selectedNhaHang.getSdt(),Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Gui that bai",Toast.LENGTH_SHORT).show();
                }
            }
        },new IntentFilter("ACTION_MSG_SENT"));
        smsManager.sendTextMessage(selectedNhaHang.getSdt(),null,"Toi muon goi mon",pendingIntentmessage,null);
    }

    private void XuLyGoiDien() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri uri = Uri.parse("tel:"+selectedNhaHang.getSdt());
        intent.setData(uri);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        MenuItem menuSearch = menu.findItem(R.id.menuSearch);
        SearchView searchView = (SearchView) menuSearch.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.isEmpty())
                {
                    nhaHangAdapter.clear();
                    nhaHangAdapter.addAll(dsNguon);
                }
                else
                {
                    ArrayList<NhaHang> dsTim = new ArrayList<>();
                    for(NhaHang nh : dsNguon)
                    {
                        if (nh.getTen().contains(newText) || nh.getSdt().contains(newText))
                        {
                            dsTim.add(nh);
                        }
                    }
                    nhaHangAdapter.clear();
                    nhaHangAdapter.addAll(dsTim);
                }
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void addEvents() {
        lvNhaHang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedNhaHang = nhaHangAdapter.getItem(position);
            }
        });
        lvNhaHang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                selectedNhaHang = nhaHangAdapter.getItem(position);
                return false;
            }
        });
    }

    private void addControls() {
        lvNhaHang = findViewById(R.id.lvNhaHang);
        nhaHangAdapter = new NhaHangAdapter(MainActivity.this,R.layout.item);
        lvNhaHang.setAdapter(nhaHangAdapter);
        nhaHangAdapter.add(new NhaHang(R.drawable.n1,"Nhà Hàng Ngon"," 02439336133"));
        nhaHangAdapter.add(new NhaHang(R.drawable.n2,"Quán An Số 2 An Trạch","0939145678"));
        nhaHangAdapter.add(new NhaHang(R.drawable.n3,"Chic Restaurant","0983588686"));
        nhaHangAdapter.add(new NhaHang(R.drawable.n4,"Nhà hàng MAISON Hoàng Cầu"," 0899349797"));
        nhaHangAdapter.add(new NhaHang(R.drawable.n5,"Quán Ăn Ngon","0902126963"));
        nhaHangAdapter.add(new NhaHang(R.drawable.n6,"Nhà Hàng Buffet Việt",""));
        nhaHangAdapter.add(new NhaHang(R.drawable.n7,"Cuốn N Roll - Hoàng Đạo Thúy","02462512181"));
        nhaHangAdapter.add(new NhaHang(R.drawable.n8,"Nhà Hàng Nhật Bản Hải Ngư","02437664040"));
        nhaHangAdapter.add(new NhaHang(R.drawable.n9,"Thu Mua Rượu Ngoại","0943667801"));
        nhaHangAdapter.add(new NhaHang(R.drawable.n10,"Nhà hàng Hatoyama - Số 8 Vạn Phúc","0917992288"));
        dsNguon.clear();
        for(int i=0;i<nhaHangAdapter.getCount();i++)
        {
            dsNguon.add(nhaHangAdapter.getItem(i));
        }
        registerForContextMenu(lvNhaHang);
    }
}