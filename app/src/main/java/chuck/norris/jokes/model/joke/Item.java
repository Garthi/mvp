package chuck.norris.jokes.model.joke;

import android.text.Html;

import com.google.gson.annotations.SerializedName;

public class Item
{
    @SerializedName("joke")
    public String text;

    @Override
    public String toString()
    {
        return Html.fromHtml(text).toString();
    }
}
