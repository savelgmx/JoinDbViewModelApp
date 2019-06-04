package fb.fandroid.adv.joindbviewmodelapp;


import java.util.List;

import fb.fandroid.adv.joindbviewmodelapp.model.Album;
import fb.fandroid.adv.joindbviewmodelapp.model.Comment;
import fb.fandroid.adv.joindbviewmodelapp.model.Song;
import fb.fandroid.adv.joindbviewmodelapp.model.User;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by marat.taychinov
 */

public interface AcademyApi {

    @POST("registration")
    Completable registration(@Body User user);

    @GET("user")
    Single<User> getUser();

    @GET("albums")
    Flowable<List<Album>> getAlbums(); //сделал чтобы был сортовка

    @GET("albums/{id}")
    Single<Album> getAlbum(@Path("id") int id);

    @GET("songs")
    Call<List<Song>> getSongs();

    @GET("songs/{id}")
    Call<Song> getSong(@Path("id") int id);


    @POST("comments")
    Completable postComment(@Body Comment comment);

    @GET("comments")
    Single<List<Comment>> getComments();

    @GET("albums/{id}/comments")
    Single<List<Comment>> getCommentsAlbum(@Path("id") int id);

}
