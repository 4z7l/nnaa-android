package com.mashup.nnaa.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mashup.nnaa.R;
import com.mashup.nnaa.data.Choices;
import com.mashup.nnaa.network.RetrofitHelper;
import com.mashup.nnaa.network.model.NewQuestionDto;
import com.mashup.nnaa.network.model.bookmarkQuestionDto;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.LayoutInflater.from;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {

    private List<NewQuestionDto> questionList;
    private Context Qcontext;
    private String id = AccountManager.getInstance().getUserAuthHeaderInfo().getUserId();
    private String token = AccountManager.getInstance().getUserAuthHeaderInfo().getToken();

    public QuestionAdapter(Context context, List<NewQuestionDto> questionList) {
        this.Qcontext = context;
        this.questionList = questionList;
    }

    public void setQuestionList(List<NewQuestionDto> questionList) {
        this.questionList = questionList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public QuestionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = from(parent.getContext()).inflate(R.layout.question_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionAdapter.ViewHolder holder, int position) {
        holder.mName.setText(questionList.get(position).getContent());
        holder.qChoice.setChecked(questionList.get(position).isFlag());


        Bundle bundle = ((Activity) Qcontext).getIntent().getExtras();

        final String category = bundle.getString("category");

        holder.qChoice.setOnCheckedChangeListener(null);
        final NewQuestionDto questionDto = questionList.get(position);

        holder.qChoice.setOnCheckedChangeListener((buttonView, isChekced) -> {

            int pos = holder.getAdapterPosition();
            if (pos != RecyclerView.NO_POSITION) {
                NewQuestionDto item = questionList.get(pos);

                NewQuestionDto newQuestionDto = new NewQuestionDto(item.getId(), item.getContent(), category, item.getType(), item.getChoices());

                if (holder.qChoice.isChecked()) {
                    RetrofitHelper.getInstance().favoriteEnroll(id, token, newQuestionDto, new Callback<NewQuestionDto>() {
                        @Override
                        public void onResponse(Call<NewQuestionDto> call, Response<NewQuestionDto> response) {
                            newQuestionDto.setFlag(isChekced);
                            questionDto.setFlag(isChekced);
                            holder.qChoice.setChecked(newQuestionDto.isFlag());
                        }

                        @Override
                        public void onFailure(Call<NewQuestionDto> call, Throwable t) {
                            Log.v("즐찾 등록", t.getMessage());
                        }
                    });
                } else {
                    // 즐겨찾기 해제
                    RetrofitHelper.getInstance().favoriteDelete(id, token, item.getId(), new Callback<NewQuestionDto>() {
                        @Override
                        public void onResponse(Call<NewQuestionDto> call, Response<NewQuestionDto> response) {
                            holder.qChoice.setChecked(false);
                            questionDto.setFlag(false);
                            newQuestionDto.setFlag(false);
                        }
                        @Override
                        public void onFailure(Call<NewQuestionDto> call, Throwable t) {
                            Log.v("즐찾 해제", t.getMessage());
                        }
                    });
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        if (questionList != null) {
            return questionList.size();
        }
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mName;
        private CheckBox qChoice;

        ViewHolder(View itemView) {
            super(itemView);

            mName = itemView.findViewById(R.id.info_text);
            qChoice = itemView.findViewById(R.id.question_choice);

        }
    }
}
