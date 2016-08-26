package chuck.norris.jokes.presenter;

import android.view.View;

import java.util.concurrent.TimeUnit;

import chuck.norris.jokes.App;
import chuck.norris.jokes.R;
import chuck.norris.jokes.model.JokeResponse;
import chuck.norris.jokes.model.joke.Item;
import chuck.norris.jokes.view.MainActivity;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class MainPresenter
{
    public static final String DEFAULT_NAME = "Chuck Norris";

    private Item[] items;
    private Throwable error;

    private MainActivity view;

    public MainPresenter()
    {
        requestData();
    }

    private void requestData()
    {
        App.getServerAPI()
                .getItems(DEFAULT_NAME.split("\\s+")[0], DEFAULT_NAME.split("\\s+")[1])
                .delay(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<JokeResponse>()
                {
                    @Override
                    public void call(JokeResponse response)
                    {
                        items = response.items;
                        publish();
                    }
                }, new Action1<Throwable>()
                {
                    @Override
                    public void call(Throwable throwable)
                    {
                        error = throwable;
                        publish();
                    }
                });
    }

    public void onTakeView(MainActivity view)
    {
        this.view = view;
        publish();
    }

    private void resetData()
    {
        items = null;
        error = null;
        if (this.view != null) {
            this.view.clearList();
        }
    }

    public void onFabClick()
    {
        if (view != null) {
            // reset data
            resetData();

            // get data
            requestData();

            // add new content
            publish();

            // show message
            this.view.showSnackMessage(
                    String.format(view.getString(R.string.fab_message), DEFAULT_NAME)
            );
        }
    }

    public void onItemClick(View view)
    {
        if (this.view != null) {
            this.view.showSnackMessage("Item Click");
        }
    }

    private void publish()
    {
        if (view != null) {
            if (items != null) {
                view.onItemsNext(items);
                this.view.stopWaitAnimation();
            } else if (error != null) {
                view.onItemsError(error);
            } else {
                this.view.startWaitAnimation();
            }
        }
    }
}
