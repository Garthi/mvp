package chuck.norris.jokes.model.response;

import com.google.gson.annotations.SerializedName;

import chuck.norris.jokes.model.response.joke.Item;

public class JokeResponse
{
    @SerializedName("value")
    public Item[] items;
}
