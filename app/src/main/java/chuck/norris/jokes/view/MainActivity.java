package chuck.norris.jokes.view;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import chuck.norris.jokes.R;
import chuck.norris.jokes.model.response.joke.Item;
import chuck.norris.jokes.presenter.MainPresenter;
import chuck.norris.jokes.view.adapter.MainAdapter;

public class MainActivity extends AppCompatActivity
{
    private MainAdapter adapter;
    private Toolbar mToolbar;
    private RecyclerView mListView;
    private CoordinatorLayout mLayout;
    private FloatingActionButton mFab;

    private static MainPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (RecyclerView)findViewById(R.id.listView);
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        mLayout = (CoordinatorLayout)findViewById(R.id.coordinator);
        mFab = (FloatingActionButton)findViewById(R.id.fab);

        if (presenter == null) {
            presenter = new MainPresenter();
        }

        initToolbar();
        initList();
        initFab();
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        presenter.onTakeView(this);
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        presenter.onTakeView(null);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();

        if (isFinishing()) {
            presenter = null;
        }
    }

    private void initToolbar()
    {
        setSupportActionBar(mToolbar);
    }

    private void initList()
    {
        mListView.setAdapter(adapter = new MainAdapter(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                presenter.onItemClick(view);
            }
        }));
        mListView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initFab()
    {
        mFab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                presenter.onFabClick();
            }
        });
    }

    public void onItemsNext(Item[] items)
    {
        adapter.clear();
        adapter.addAll(items);
    }

    public void onItemsError(Throwable throwable)
    {
        showSnackMessage(throwable.getMessage());
    }

    public void showSnackMessage(CharSequence text)
    {
        Snackbar.make(mLayout, text, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
