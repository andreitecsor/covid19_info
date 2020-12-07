package ie.dam.covid19_info.async_task;


import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AsyncTaskRunner {
    private final Handler handler = new Handler(Looper.getMainLooper());
    private final Executor executor = Executors.newCachedThreadPool();

    public <R> void executeAsync(Callable<R> asyncOperation, Callback<R> mainThreadOperation) {
        try {
            executor.execute(new RunnableTask<>(handler, asyncOperation, mainThreadOperation));
        } catch (Exception ex) {
            Log.i("AsyncTaskRunner", "Failed call executeAsync " + ex.getMessage());
        }
    }
}
