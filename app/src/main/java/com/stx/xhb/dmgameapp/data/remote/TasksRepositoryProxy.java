package com.stx.xhb.dmgameapp.data.remote;

import com.stx.xhb.dmgameapp.config.ApiService;
import com.stx.xhb.dmgameapp.utils.RequestBodyHelper;
import com.stx.xhb.dmgameapp.data.TasksDataSource;
import com.stx.xhb.dmgameapp.data.entity.NewsContent;
import com.stx.xhb.dmgameapp.data.entity.NewsPageBean;
import com.stx.xhb.dmgameapp.http.HttpManager;
import com.stx.xhb.dmgameapp.http.HttpResult;
import com.stx.xhb.dmgameapp.http.TransformUtils;

import rx.Subscriber;
import rx.Subscription;

/**
 * Author: Mr.xiao on 2018/9/5
 *
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe: 数据仓库代理类，用于封装从不同来源处获取数据
 */
public class TasksRepositoryProxy implements TasksDataSource {

    private static TasksRepositoryProxy INSTANCE = null;

    private TasksRepositoryProxy() {
    }

    public static TasksRepositoryProxy getInstance() {
        if (INSTANCE == null) {
            synchronized (TasksRepositoryProxy.class) {
                if (INSTANCE == null) {
                    return new TasksRepositoryProxy();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void release() {
        INSTANCE = null;
    }

    @Override
    public Subscription getHowNews(int currentPage, Subscriber<? super HttpResult<NewsPageBean>> subscriber) {
        return HttpManager.getInstance().createService(ApiService.class)
                .getHotNews(RequestBodyHelper.creatRequestBody(new NewsContent(currentPage)))
                .compose(TransformUtils.<HttpResult<NewsPageBean>>defaultSchedulers())
                .subscribe(subscriber);
    }

    @Override
    public Subscription getNews(int currentPage, Subscriber<? super HttpResult<NewsPageBean>> subscriber) {
        return HttpManager.getInstance().createService(ApiService.class)
                .getNews(RequestBodyHelper.creatRequestBody(new NewsContent(currentPage)))
                .compose(TransformUtils.<HttpResult<NewsPageBean>>defaultSchedulers())
                .subscribe(subscriber);
    }

    @Override
    public Subscription getOriginalPage(int currentPage, Subscriber<? super HttpResult<NewsPageBean>> subscriber) {
        return HttpManager.getInstance().createService(ApiService.class)
                .getOriginalPage(RequestBodyHelper.creatRequestBody(new NewsContent(currentPage)))
                .compose(TransformUtils.<HttpResult<NewsPageBean>>defaultSchedulers())
                .subscribe(subscriber);
    }

    @Override
    public Subscription getVideoPage(int currentPage, Subscriber<? super HttpResult<NewsPageBean>> subscriber) {
        return HttpManager.getInstance().createService(ApiService.class)
                .getVideoPage(RequestBodyHelper.creatRequestBody(new NewsContent(currentPage)))
                .compose(TransformUtils.<HttpResult<NewsPageBean>>defaultSchedulers())
                .subscribe(subscriber);
    }

    @Override
    public Subscription getAmusePage(int currentPage, Subscriber<? super HttpResult<NewsPageBean>> subscriber) {
        return HttpManager.getInstance().createService(ApiService.class)
                .getAmusePage(RequestBodyHelper.creatRequestBody(new NewsContent(currentPage)))
                .compose(TransformUtils.<HttpResult<NewsPageBean>>defaultSchedulers())
                .subscribe(subscriber);
    }
}