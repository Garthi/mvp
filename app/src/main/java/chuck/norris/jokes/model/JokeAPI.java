package chuck.norris.jokes.model;

import chuck.norris.jokes.model.response.JokeResponse;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

public interface JokeAPI
{
    public static final String ENDPOINT = "http://api.icndb.com";

    @GET("/jokes/random/10")
    Observable<JokeResponse> getItems(@Query("firstName") String firstName, @Query("lastName") String lastName);
}
