package chuck.norris.jokes.api;

import chuck.norris.jokes.model.JokeResponse;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

public interface JokeAPI
{
    public static final String ENDPOINT = "http://api.icndb.com";

    @GET("/jokes/random/10")
    Observable<JokeResponse> getItems(@Query("firstName") String firstName, @Query("lastName") String lastName);
}
