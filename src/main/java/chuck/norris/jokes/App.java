package chuck.norris.jokes;

import android.app.Application;
import android.util.Log;

import chuck.norris.jokes.api.JokeAPI;
import retrofit.RestAdapter;

public class App extends Application
{
    private static JokeAPI serverAPI;

    @Override
    public void onCreate()
    {
        super.onCreate();

        serverAPI = new RestAdapter.Builder()
                .setEndpoint(JokeAPI.ENDPOINT)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setLog(new RestAdapter.Log()
                {
                    @Override
                    public void log(String message)
                    {
                        Log.v("Retrofit", message);
                    }
                })
                .build().create(JokeAPI.class);
    }

    public static JokeAPI getServerAPI()
    {
        return serverAPI;
    }
}
