package com.soft.newstree.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.soft.newstree.HomeActivity;
import com.soft.newstree.R;
import com.soft.newstree.bean.MenuBean;

import java.util.ArrayList;


public class GridItemAdapter extends BaseAdapter {

    private HomeActivity homeActivity;
    private ArrayList<MenuBean> menuItemsAr;

    public GridItemAdapter(HomeActivity context, ArrayList<MenuBean> menuList) {
        super();
        homeActivity=context;
        menuItemsAr=menuList;

    }

    @Override
    public int getCount() {
        return menuItemsAr.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        LayoutInflater inflater = (LayoutInflater) homeActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.menu_list_row, null);
        ImageView menu_image = view.findViewById(R.id.menu_image);
        TextView lbl_menuname = view.findViewById(R.id.lbl_menuname);
        LinearLayout ll_menu_main_layout = view.findViewById(R.id.ll_menu_main_layout);
        lbl_menuname.setText(menuItemsAr.get(position).getItemName());
        ll_menu_main_layout.setTag(position);
        ll_menu_main_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index=Integer.parseInt(v.getTag()+"");
                MenuBean menuBean1=menuItemsAr.get(index);
                homeActivity.showWebViewFragment(menuBean1.getItemLink()+"");
            }
        });
        return view;

    }

}