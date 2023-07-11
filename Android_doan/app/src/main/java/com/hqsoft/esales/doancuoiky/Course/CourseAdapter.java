package com.hqsoft.esales.doancuoiky.Course;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hqsoft.esales.doancuoiky.R;
import com.hqsoft.esales.doancuoiky.youtubeApi;

import java.util.ArrayList;
import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> implements Filterable {

    private Context mContext;
    private Button btnStart;
    private List<Course> mListCourse;
    private List<Course> mListCourseOld;

    public CourseAdapter(Context mContext, int fragment_home_index, List<Course> mList) {
        this.mContext = mContext;
    }

    public void setData(List<Course> list){
        this.mListCourse = list;
        this.mListCourseOld = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course, parent, false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Course course = mListCourse.get(position);

        if (course == null){
            return;
        }

        holder.name_course.setText(course.getNameCourse());
        holder.description_course.setText(course.getDescriptionCourse());
        //holder.time_course.setText(course.getTimeCourse());



//        btnStart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String b = (String) holder.name_course.getText();
//
//                if ( b == "Bài 1"){
//                }
//                Toast.makeText(mContext, "Kích vô Button nè!!" + b, Toast.LENGTH_SHORT).show();
//
//            }
//        });
//       holder .itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String a = (String) holder.name_course.getText();
//                Toast.makeText(mContext, "Kích vô item nè,Lấy được rồi nè!" + a, Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        if (mListCourse != null){
            return mListCourse.size();
        }
        return 0;
    }




    public class CourseViewHolder extends RecyclerView.ViewHolder{

        private TextView time_course, description_course, name_course;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);

            time_course = itemView.findViewById(R.id.time_course);
            description_course = itemView.findViewById(R.id.description_course);
            name_course = itemView.findViewById(R.id.name_course);
            btnStart = itemView.findViewById(R.id.btnStart);

            btnStart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    Context context = itemView.getContext();
                    Intent intent = new Intent(context, youtubeApi.class);
                    intent.putExtra("baihoc",mListCourse.get(pos).getNameCourse());
                    intent.putExtra("idVdeo",mListCourse.get(pos).getIdVdeo());
                    intent.putExtra("mota",mListCourse.get(pos).getDescriptionCourse());
                    context.startActivity(intent);
                }
            });
        }
    }


    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                if (strSearch.isEmpty()){
                    mListCourse = mListCourseOld;
                }else {
                    List<Course> list = new ArrayList<>();
                    for (Course course : mListCourseOld){
                        if (course.getNameCourse().toLowerCase().contains(strSearch.toLowerCase())){
                            list.add(course);
                        }
                    }

                    mListCourse = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mListCourse;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mListCourse =(List<Course>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
