package com.bw.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.bean.Goods;
import com.bw.week01_lx.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import retrofit2.http.POST;

/**
 * Created by 1607c王晴
 * date 2019/2/16
 * Describe:
 */
public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.ViewHolder> {

    public Context context;
    public List<Goods.ResultBean> list;

    public GoodsAdapter(Context context, List<Goods.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    public interface onGoodItemClick{
        void onItemClick(int i);

    }

    public onGoodItemClick monGoodItemClick;

    public void setOnGoodItemClick(onGoodItemClick onGoodItemClick){
       monGoodItemClick=onGoodItemClick;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.goods_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        viewHolder.goods_item_img.setImageURI(list.get(i).getMasterPic());
        viewHolder.goods_item_title.setText(list.get(i).getCommodityName());
        viewHolder.goods_item_price.setText("¥:"+list.get(i).getPrice());
        viewHolder.goods_item_shou.setText("已售"+list.get(i).getSaleNum()+"件");
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int commodityId = list.get(i).getCommodityId();
                if (monGoodItemClick!=null){
                    monGoodItemClick.onItemClick(commodityId);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        private final TextView goods_item_title;
        private final TextView goods_item_price;
        private final TextView goods_item_shou;
        private final SimpleDraweeView goods_item_img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            goods_item_img = itemView.findViewById(R.id.goods_item_img);
            goods_item_title = itemView.findViewById(R.id.goods_item_title);
            goods_item_price = itemView.findViewById(R.id.goods_item_price);
            goods_item_shou = itemView.findViewById(R.id.goods_item_shou);
        }
    }
}
