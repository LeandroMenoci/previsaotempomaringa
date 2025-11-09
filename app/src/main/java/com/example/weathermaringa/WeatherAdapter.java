
package com.example.weathermaringa;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {

    private List<WeatherItem> weatherList;

    public WeatherAdapter(List<WeatherItem> weatherList) {
        this.weatherList = weatherList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WeatherItem item = weatherList.get(position);
        holder.txtWeekday.setText(item.weekday);
        holder.txtDesc.setText(item.description);
        holder.txtTemp.setText("Máx: " + item.max + "°  Mín: " + item.min + "°");
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtWeekday, txtDesc, txtTemp;

        ViewHolder(View itemView) {
            super(itemView);
            txtWeekday = itemView.findViewById(R.id.txtWeekday);
            txtDesc = itemView.findViewById(R.id.txtDesc);
            txtTemp = itemView.findViewById(R.id.txtTemp);
        }
    }
}
