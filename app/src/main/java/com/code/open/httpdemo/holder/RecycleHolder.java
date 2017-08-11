package com.code.open.httpdemo.holder;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.code.open.http.HttpHelper;
import com.code.open.http.RequestBodyBuilder;
import com.code.open.httpdemo.model.GitSearch;
import com.code.open.httpdemo.view.MainActivity;
import com.code.open.httpdemo.view.RecycleAdapter;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * ================================================
 * Created by zhaokai on 2017/3/27.
 * Email zhaokai1033@126.com
 * Describe :
 * ================================================
 */

public class RecycleHolder implements MainActivity.RecycleHolderFace {
    private static final String TAG = "RecycleHolder";
    private static RecycleHolder holder;
    private final RecycleAdapter adapter;

    private RecycleHolder() {
        adapter = new RecycleAdapter();
    }

    /**
     * 共用一个Holder
     */
    public static RecycleHolder getInstance() {
        if (holder == null) {
            synchronized (TAG) {
                if (holder == null) {
                    holder = new RecycleHolder();
                }
            }
        }
        return holder;
    }

    //    https://github.com/search?q=zhaokai&type=Users&utf8=✓
    private String url = "https://api.github.com/search/users";

    @Override
    public void onSearchClick(View view, final String name) {

        Log.d(TAG, "name:" + name);
        Observable.create(new ObservableOnSubscribe<GitSearch>() {
            @Override
            public void subscribe(ObservableEmitter<GitSearch> e) throws Exception {
                GitSearch result = HttpHelper.getResult(
                        url,
                        RequestBodyBuilder.newFormBody().add("q", name),
                        GitSearch.class);
                e.onNext(result);
                e.onComplete();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GitSearch>() {
                    @Override
                    public void accept(@NonNull GitSearch result) throws Exception {
                        adapter.setItems(result.getItems());
                        Log.d(TAG, "count:" + result.getTotal_count());
                    }
                });
    }

    @Override
    public RecyclerView.Adapter getAdapter() {
        return adapter;
    }
}
