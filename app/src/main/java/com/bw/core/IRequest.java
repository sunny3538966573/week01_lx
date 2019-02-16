package com.bw.core;

import com.bw.bean.Goods;
import com.bw.config.HttpConfig;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by 1607c王晴
 * date 2019/2/16
 * Describe:
 */
public interface IRequest {
    /**
     * 根据关键字查询
     * @param keyword
     * @param page
     * @param count
     * @return
     */
    @GET(HttpConfig.goods_url)
    Observable<Goods> getGoods(@Query("keyword") String keyword,
                                       @Query("page") String page,
                                       @Query("count") String count
    );
}
