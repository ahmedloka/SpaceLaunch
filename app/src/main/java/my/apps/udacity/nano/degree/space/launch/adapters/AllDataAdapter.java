package my.apps.udacity.nano.degree.space.launch.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;
import my.apps.udacity.nano.degree.space.launch.R;
import my.apps.udacity.nano.degree.space.launch.utilites.CairoBoldTextView;
import my.apps.udacity.nano.degree.space.launch.utilites.CairoRegularTextView;

public class AllDataAdapter extends RecyclerView.Adapter<AllDataAdapter.ViewHolder> {

    private final AllDataAdapter.OnClickHandler onClickHandler;
    private final List<String> listNames, listCities, listImg, listDates;

    private Context context;

    public AllDataAdapter(Context context, List<String> listNames, List<String> listCities, List<String> listImg, List<String> listDates, AllDataAdapter.OnClickHandler onClickHandler) {
        this.context = context;
        this.listNames = listNames;
        this.listCities = listCities;
        this.listImg = listImg;
        this.listDates = listDates;
        this.onClickHandler = onClickHandler;
    }

    @Override
    public int getItemCount() {
        return listNames == null ? 0 : listNames.size();
    }

    @NonNull
    @Override
    public AllDataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_all_data, parent, false);
        return new AllDataAdapter.ViewHolder(view);
    }

    private void removeAt(int position) {
        listNames.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, listNames.size());
    }

    @Override
    public void onBindViewHolder(@NonNull final AllDataAdapter.ViewHolder holder, final int listPosition) {
        try {

            holder.name.setText(listNames.get(listPosition));
            holder.dateAndCity.setText(listDates.get(listPosition) + " , "+ listCities.get(listPosition));

            Picasso.get()
                    .load(listImg.get(listPosition))
                    .fit()
                    .centerCrop()
                    .into(holder.img);
        } catch (Exception ignored) {

        }
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public interface OnClickHandler {
        void onClick(int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private AppCompatImageView img;
        private CairoBoldTextView name;
        private CairoRegularTextView dateAndCity;



        ViewHolder(View itemView) {
            super(itemView);

            initViews(itemView);
        }

        private void initViews(View itemView) {

            img = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.name);
            dateAndCity = itemView.findViewById(R.id.date_and_city);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            onClickHandler.onClick(position);
        }
    }
}




