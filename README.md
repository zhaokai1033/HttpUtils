# HttpUtils
1、Network request tool based on okhttp

- Support for custom Cookie and Header.
- Support for Get and Post.
- Support log output.
- Support output model.

2、Demonstration

```java
//初始化
Config config = new Config(true);
config.isFormat = true;
HttpHelper.init(getApplicationContext(), config);
```

```java
//使用
GitSearch result = HttpHelper.getResult(
        url,
        RequestBodyBuilder.newFormBody().add("q", name),
        GitSearch.class);
e.onNext(result);
e.onComplete();
```

```java
//配合 RXJAVA
Observable
        .create(new ObservableOnSubscribe<GitSearch>() {
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
```