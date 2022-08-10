package ddwucom.mobile.finalreport;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    ViewHolder viewHolder;

    private Context context;
    private int layout;
    private ArrayList<MyData> myDataList;
    private LayoutInflater inflater;

    public MyAdapter(Context context, int layout, ArrayList<MyData> myDataList) {
        this.context = context;
        this.layout = layout;
        this.myDataList = myDataList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return myDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return myDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return myDataList.get(position).get_id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;

        if (convertView == null) {
            convertView = inflater.inflate(layout, parent, false);

            // viewholder 사용
            viewHolder = new ViewHolder();
            viewHolder.tvId = convertView.findViewById(R.id.tvId);
            viewHolder.imageView = convertView.findViewById(R.id.imageView);
            viewHolder.tvBookTitle = convertView.findViewById(R.id.tvBookTitle);
            viewHolder.tvAuthor = convertView.findViewById(R.id.tvAuthor);
            viewHolder.tvPrice = convertView.findViewById(R.id.tvPrice);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // viewholder 사용
        viewHolder.tvId.setText(String.valueOf(myDataList.get(position).get_id()));
        viewHolder.imageView.setImageResource(myDataList.get(position).getImage());
        viewHolder.tvBookTitle.setText(String.valueOf(myDataList.get(position).getBook_title()));
        viewHolder.tvAuthor.setText(myDataList.get(position).getAuthor());
        viewHolder.tvPrice.setText(String.valueOf(myDataList.get(position).getPrice()));

        return convertView;
    }

    static class ViewHolder {
        TextView tvId;
        ImageView imageView;
        TextView tvBookTitle;
        TextView tvAuthor;
        TextView tvPrice;
    }
}
