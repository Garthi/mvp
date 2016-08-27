package chuck.norris.jokes.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import chuck.norris.jokes.R;
import chuck.norris.jokes.model.joke.Item;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainHolder>
{
    private Item[] data;
    private LayoutInflater inflater;
    private View.OnClickListener listener;

    public MainAdapter(View.OnClickListener listener)
    {
        this.listener = listener;
        clear();
    }

    @Override
    public MainHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }

        TextView text = (TextView)inflater.inflate(R.layout.item, parent, false);
        text.setClickable(true);
        text.setOnClickListener(listener);

        return new MainHolder(text);
    }

    @Override
    public void onBindViewHolder(MainHolder holder, int position)
    {
        holder.text.setText(data[position].toString());
    }

    @Override
    public int getItemCount()
    {
        if (data == null) {
            return 0;
        }

        return data.length;
    }

    public void clear()
    {
        this.data = null;
        this.notifyDataSetChanged();
    }

    public void addAll(Item[] items)
    {
        this.data = items;
        this.notifyDataSetChanged();
    }

    class MainHolder extends RecyclerView.ViewHolder
    {
        protected TextView text;

        public MainHolder(TextView itemView)
        {
            super(itemView);

            text = itemView;
        }
    }
}
