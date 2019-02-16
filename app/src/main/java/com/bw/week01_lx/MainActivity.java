package com.bw.week01_lx;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.adapter.GoodsAdapter;
import com.bw.bean.Goods;
import com.bw.core.IRequest;
import com.bw.week01_lx.util.RetrofitUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActivity {


    @BindView(R.id.img_menu)
    ImageView imgMenu;
    @BindView(R.id.edit)
    EditText edit;
    @BindView(R.id.search_text)
    TextView searchText;
    @BindView(R.id.xrecy)
    XRecyclerView xrecy;
    int page=1;
    int count=5;
    private GoodsAdapter goodsAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initview() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {

    }



    @OnClick(R.id.search_text)
    public void onViewClicked() {
        String edit_content = edit.getText().toString().trim();

        if (TextUtils.isEmpty(edit_content)){
            Toast.makeText(this, "输入内容不可为空", Toast.LENGTH_SHORT).show();
        }else {
            IRequest iRequest = RetrofitUtils.getInstance().create(IRequest.class);
            iRequest.getGoods(edit_content,""+page,"5")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Goods>() {
                        @Override
                        public void accept(Goods goods) throws Exception {
                            if (goods!=null){
                                List<Goods.ResultBean> list = goods.getResult();
                                GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 2, GridLayoutManager.VERTICAL, false);
                                xrecy.setLayoutManager(gridLayoutManager);
                                goodsAdapter = new GoodsAdapter(MainActivity.this, list);
                                xrecy.setAdapter(goodsAdapter);
                                goodsAdapter.setOnGoodItemClick(new GoodsAdapter.onGoodItemClick() {
                                    @Override
                                    public void onItemClick(int i) {
                                        Intent intent=new Intent(MainActivity.this,DetailsActivity.class);
                                        intent.putExtra("commodityId",i);
                                        startActivity(intent);
                                    }
                                });
                            }
                        }
                    });

        }
    }


}
