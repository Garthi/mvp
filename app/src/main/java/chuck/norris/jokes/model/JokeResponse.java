package chuck.norris.jokes.model;

import com.google.gson.annotations.SerializedName;

import chuck.norris.jokes.model.joke.Item;

public class JokeResponse
{
    @SerializedName("value")
    public Item[] items;
}
