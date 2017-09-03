package com.example.tjjunlintx.diyview;

import java.io.IOException;

/**
 * Created by tjjunlintx on 2017/6/24.
 */

public interface OkResultListener {
    void onSuccess(Object result);
    void onFail(IOException e);
}
