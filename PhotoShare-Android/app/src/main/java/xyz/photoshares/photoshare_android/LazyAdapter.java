package xyz.photoshares.photoshare_android;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class LazyAdapter extends BaseAdapter {

    private Activity activity;
    private static String[] data;
    private String[] titleandphotographers;
    private static LayoutInflater inflater=null;
    public ImageLoader imageLoader;

    public LazyAdapter(Activity a, String[] d, String[] tap) {
        activity = a;
        data=d;
        titleandphotographers = tap;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader=new ImageLoader(activity.getApplicationContext());
    }

    public int getCount() {
        return data.length;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.row_listview_item, null);

        TextView text=(TextView)vi.findViewById(R.id.text);
        ImageView image=(ImageView)vi.findViewById(R.id.image);

        String[] datas = titleandphotographers[position].split(",");
        String photographer = datas[datas.length - 1];
        String title = "";
        for(int i = 0; i < datas.length - 1; i++){
            title += datas[i] + ",";
        }
        title = title.substring(0,title.length()-1);
        text.setText("\"" + title + "\"" + "\n" + photographer);

        imageLoader.DisplayImage(data[position], image);
        return vi;
    }
}