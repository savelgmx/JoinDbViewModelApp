package fb.fandroid.adv.joindbviewmodelapp.comments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fb.fandroid.adv.joindbviewmodelapp.ApiUtils;
import fb.fandroid.adv.joindbviewmodelapp.App;
import fb.fandroid.adv.joindbviewmodelapp.R;
import fb.fandroid.adv.joindbviewmodelapp.albums.AlbumsActivity;
import fb.fandroid.adv.joindbviewmodelapp.db.MusicDao;
import fb.fandroid.adv.joindbviewmodelapp.model.Album;
import fb.fandroid.adv.joindbviewmodelapp.model.Comment;
import fb.fandroid.adv.joindbviewmodelapp.model.User;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Azret Magometov
 */

public class CommentsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    private static final String ALBUM_KEY = "ALBUM_KEY";
    private static final String USER_KEY = "ALBUM_KEY";

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mRefresher;
    private Album mAlbum;
    private User mUser;
    private View mErrorView;

    private EditText mComment;
    private Button mButton;

    public Integer mSize = null;

    @NonNull
    private final CommentsAdapter mCommentsAdapter = new CommentsAdapter();

    public static CommentsFragment newInstance(Album album) {
        Bundle args = new Bundle();
        args.putSerializable(ALBUM_KEY, album);

        CommentsFragment fragment = new CommentsFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fr_comments, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mRecyclerView = view.findViewById(R.id.recycler);
        mRefresher = view.findViewById(R.id.refresher);
        mRefresher.setOnRefreshListener(this);
        mErrorView = view.findViewById(R.id.errorViewComments);

        mComment = view.findViewById(R.id.etComment);
        mComment.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                pastComment();
                return true;
            }
            return false;
        });

        mButton = view.findViewById(R.id.btnPostComment);
        mButton.setOnClickListener(mButtonOnClickListener);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAlbum = (Album) getArguments().getSerializable(ALBUM_KEY);
        AlbumsActivity activity = (AlbumsActivity) getActivity();
        mUser = activity.getCurrentUser();

        activity.setTitle("Комментарии к альбому:'\n'" + mAlbum.getName());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mCommentsAdapter);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        mRefresher.post(() -> {
            mRefresher.setRefreshing(true);
            getComments();
            mRecyclerView.scrollToPosition(mCommentsAdapter.getItemCount() - 1);
        });
    }

    @SuppressLint("CheckResult")
    private void getComments() {
        ApiUtils.getApiService().getCommentsAlbum(mAlbum.getId())
                .subscribeOn(Schedulers.io())
                .doOnSuccess(comments -> getMusicDao().insertComments(comments))
                .onErrorReturn(throwable -> {
                    if (ApiUtils.NETWORK_EXCEPTIONS.contains(throwable.getClass())) {
                        getActivity().runOnUiThread(() -> Toast.makeText(getActivity(), "Нет подключения!", Toast.LENGTH_SHORT).show());
                        return getMusicDao().getCommentsByAlbumId(mAlbum.getId());
                    } else return null;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> mRefresher.setRefreshing(true))
                .doFinally(() -> mRefresher.setRefreshing(false))
                .subscribe(comments -> {
                    mErrorView.setVisibility(View.GONE);
                    mRecyclerView.setVisibility(View.VISIBLE);
                    if (comments.size() > 0){
                        mCommentsAdapter.addData(comments, true);
                        if (mSize != null){
                            if (comments.size() != mSize){
                                Toast.makeText(getActivity(), "Комментарии обновлены!", Toast.LENGTH_LONG).show();
                            } else{
                                Toast.makeText(getActivity(), "Новых комментариев нет!", Toast.LENGTH_LONG).show();
                            }
                        }
                    } else{
                        if (mSize != null){
                            Toast.makeText(getActivity(), "Новых комментариев нет!", Toast.LENGTH_LONG).show();
                        }
                        mErrorView.setVisibility(View.VISIBLE);
                        mRecyclerView.setVisibility(View.GONE);
                    }
                    mSize = comments.size();
                });
    }

    private MusicDao getMusicDao() {
        return ((App) getActivity().getApplication()).getDatabase().getMusicDao();
    }

    View.OnClickListener mButtonOnClickListener = new View.OnClickListener() {
        @SuppressLint("CheckResult")
        @Override
        public void onClick(View view) {
            pastComment();
        }
    };

    @SuppressLint("CheckResult")
    private void pastComment() {
        if (!TextUtils.isEmpty(mComment.getText())){
            mRefresher.setRefreshing(true);
            Comment comment = new Comment();
            comment.setAlbumId(mAlbum.getId());
            comment.setText(mComment.getText().toString());

            ApiUtils.getApiService(mUser.getEmail(), mUser.getPassword())
                    .postComment(comment)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            () -> {
                                onRefresh();
                                Toast.makeText(getActivity(), "Комментарии обновлены!", Toast.LENGTH_LONG).show();
                            }, throwable -> {
                                Toast.makeText(getActivity(), "Ошибка!", Toast.LENGTH_LONG).show();
                            });
        } else{
            Toast.makeText(getActivity(), "Текст комментария пустой!", Toast.LENGTH_LONG).show();
        }
    }
}