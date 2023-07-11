package com.hqsoft.esales.doancuoiky;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.hqsoft.esales.doancuoiky.Course.CourseAdapter;
import com.hqsoft.esales.doancuoiky.left_menu.ViewPagerAdapter;
import com.hqsoft.esales.doancuoiky.left_menu.fragment_home;


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenumainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private static final int Fragment_Home =0;
    private static final int Fragment_setting =1;
    private int mCurrenFragment = Fragment_Home;
    GoogleSignInOptions gso;
    ArrayList<Account> arrayList  ;
    String[] a ={};
    String[] b ={};
    List<String> testList = new ArrayList<>(Arrays.asList(a));
    List<String> testList1 = new ArrayList<>(Arrays.asList(b));
    GoogleSignInClient gsc;
    private TabLayout mTapLayout;
    private ViewPager mViewPager;
    TextView name_show ,  gmail_show;
    private Button trypro;
    private RecyclerView rcv_course;
    private CourseAdapter courseAdapter;
    private  String value1;
    String DB_name = "csdl.db";
    private  String path ="/databases/";
    SQLiteDatabase database= null;
    database_Login database_esales;
    String userdata;
    String gmaildata;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menumain);
        mTapLayout = findViewById(R.id.tab_layout);
        mViewPager = findViewById(R.id.view_pager);
        name_show  = (TextView) findViewById(R.id.name_user_mn);
        gmail_show = findViewById(R.id.gamil_user_mn);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(viewPagerAdapter);
        mTapLayout.setupWithViewPager(mViewPager);
        xulyCoppyData();
        Toolbar toolbar = findViewById(R.id.menutoolbar_page);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.menu_drawlayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout ,toolbar , R.string.navigation_open , R.string.navigation_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.navigationview_pagemenu);
        navigationView.setNavigationItemSelectedListener(this);
        repalect_fragment(new fragment_home());
        navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
        //
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);
        trypro = findViewById(R.id.trypro);
        trypro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenumainActivity.this , PremiumActivity.class);
                startActivity(intent);
            }
        });
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            value1 = bundle.getString("gmail", "");
        }
        showdata();
        a = testList.toArray(new String[0]);
        b = testList.toArray(new String[0]);

//        name_show.setText('h');


    }
    private void showdata() {

        database = openOrCreateDatabase(DB_name, MODE_PRIVATE, null);
        Cursor cursor1 =database.rawQuery("SELECT * FROM Users where Email = '"+ value1 +"'", null);
        while (cursor1.moveToNext()){
            userdata =cursor1.getString(1);
            gmaildata = cursor1.getString(2);
            testList.add( userdata);
            testList1.add( gmaildata);

//
        }
        NavigationView navigationView1 = (NavigationView) findViewById(R.id.navigationview_pagemenu);
        View headerView = navigationView1.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.name_user_mn);
        TextView navGmail = (TextView) headerView.findViewById(R.id.gamil_user_mn);
        navUsername.setText(userdata);
        navGmail.setText(gmaildata);
        cursor1.close();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id =item.getItemId();
        if (id== R.id.nav_home){
            if (mCurrenFragment!=Fragment_Home){
                repalect_fragment(new fragment_home());
                mCurrenFragment = Fragment_Home;
            }
        }else if(id== R.id.nav_userprofile){
            Intent intent= new Intent(this, ProfileActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("gmail",value1);
            intent.putExtras(bundle);
            startActivity(intent);
        }else if(id== R.id.nav_FollowUs){
            Intent intent= new Intent(this, FollowusActivity.class);
            startActivity(intent);
        }else if(id== R.id.nav_logout){
            signOut();
        }
        else if(id== R.id.nav_Feedback){
            Intent intent= new Intent(this, FeedbackActivity.class);
            startActivity(intent);
        }
        else if(id== R.id.nav_setting){
            Intent intent= new Intent(this, SettingActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("gmail",value1);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }

    }

    private  void repalect_fragment(Fragment fragment){
        FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_fram_menu , fragment);
        transaction.commit();
    }
    void signOut(){
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {
                finish();
                startActivity(new Intent(MenumainActivity.this,LoginActivity.class));
            }
        });
    }
    private void  xulyCoppyData(){
        File dbFile = getDatabasePath(DB_name);
        if (!dbFile.exists()){
            copyData();
        }
    }

    private void copyData() {
        try {
            InputStream inputStream = getAssets().open(DB_name);
            String oufile = getApplicationInfo().dataDir+path+DB_name;
            File f = new File(getApplicationInfo().dataDir+path);
            if (!f.exists()){
                f.mkdir();
            }
            OutputStream myOutput = new FileOutputStream(oufile);
            byte[] bytes = new byte[1024];
            int len ;
            while ((len=inputStream.read(bytes))>0){
                myOutput.write(bytes, 0,len);
            }
            myOutput.flush();
            inputStream.close();
            myOutput.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}