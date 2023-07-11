package com.hqsoft.esales.doancuoiky.left_menu;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hqsoft.esales.doancuoiky.Course.Course;
import com.hqsoft.esales.doancuoiky.Course.CourseAdapter;
import com.hqsoft.esales.doancuoiky.R;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class fragment_home_index extends Fragment {
    private RecyclerView rcv_course;
    EditText tvSearch;
    SearchView searchView;
    List<Course> mList = new ArrayList<>();
    List<Course> mListFill = new ArrayList<>();
    CourseAdapter courseAdapter = new CourseAdapter(getActivity(), R.layout.fragment_home_index, mList);
    CourseAdapter adapterCourse ;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_index, container, false);
        tvSearch = view.findViewById(R.id.tvSearch);
        rcv_course = view.findViewById(R.id.rcv_course);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        rcv_course.setLayoutManager(linearLayoutManager);
        getListCourse();
        courseAdapter.setData(mList);
        rcv_course.setAdapter(courseAdapter);


        addChange();

        return view;

    }

    public void addChange() {
        tvSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mListFill.clear();
                if(s.toString().isEmpty()){
                    courseAdapter.setData(mList);
                    rcv_course.setAdapter(courseAdapter);
                } else {
                    Filter(s.toString());
                }
            }
        });
    }

    private void Filter(String edtext){
        for (Course cus : mList){
            edtext = exChange(edtext).toLowerCase();

            if(exChange(cus.getNameCourse().toLowerCase()).contains(edtext) ||  exChange(cus.getDescriptionCourse().toLowerCase()).contains(edtext) ){
                mListFill.add(cus);
            }
        }
        courseAdapter.setData(mListFill);
        rcv_course.setAdapter(courseAdapter);
    }
    private String exChange(String s){
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }

    private void getListCourse() {

//        List<Course> list = new ArrayList<>();
        mList.add(new Course("Bài 1: Khoá học Javascript","0SJE9dYdpps","Javascript có thể làm được gì? Học lập trình Javascript cơ bản"));
        mList.add(new Course("Bài 2: Khoá học Javascript","efI98nT8Ffo","Cài đặt môi trường"));
        mList.add(new Course("Bài 3: Khoá học Javascript","W0vEUmyvthQ","Sử dụng JS trong file HTML"));
        mList.add(new Course("Bài 4: Khoá học Javascript","CLbx37dqYEI","Khai báo biến"));
        mList.add(new Course("Bài 5: Khoá học Javascript","xRpXBEq6TOY","Comments"));
        mList.add(new Course("Bài 6: Khoá học Javascript","SZb-N7TfPlw","Làm quen với toán tử"));
        mList.add(new Course("Bài 7: Khoá học Javascript","m_h7-dgKnMU","Toán tử số học"));
        mList.add(new Course("Bài 8: Khoá học Javascript","aM-DUx6Qnc8","Toán tử ++ -- với tiền tố & hậu tố"));
        mList.add(new Course("Bài 9: Khoá học Javascript","ncRmjazgsE8","Toán tử gán"));
        mList.add(new Course("Bài 10: Khoá học Javascript","QCLVU6cZU_E","Toán tử số chuỗi"));
        mList.add(new Course("Bài 11: Khoá học Javascript","rWM2lXtS-d8","Toán tử so sánh trong Javascript phần 1"));
        mList.add(new Course("Bài 12: Khoá học Javascript","9cZEG1SSSQc","Kiểu dữ liệu Boolean"));
        mList.add(new Course("Bài 13: Khoá học Javascript","9MpHrdWBdxg","Câu lệnh điều kiện If"));

    }



}
