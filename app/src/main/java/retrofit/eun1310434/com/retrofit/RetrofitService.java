package retrofit.eun1310434.com.retrofit;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitService {

    public static final String URL = "http://jsonplaceholder.typicode.com/";
    //서버주소

    @GET("comments") // @GET("api주소")
    Call<ResponseBody>getComment(@Query("postId")int postId);
    //Call<ResponseBody> 함수이름 (@Query("변수이름") 안드로이드에서 보낼 변수);

    @GET("repos/{owner}/{repo}/contributors")
    Call<List<Item>> repoContributors(
            @Path("owner") String owner,
            @Path("repo") String repo);
}


/*=================================================================================================
    //인터페이스 : HTTP API를 자바 인터페이스 형태로 사용

    //REQUEST METHOD
    //- 모든 메소드에는 반드시 상대 URL과 요청 메소드를 명시하는 어노테이션을 가지고 있어야 함
    //- 기본으로 제공하는 요청 메소드 어노테이션 5가지 : GET, POST, PUT, DELETE, HEAD

    // URL을 지정
    //@GET("users/list")

    // static 쿼리 인자를 URL에 명시할 수 있음
    //@GET("users/list?sort=desc")



    //URL MANIPULATION : 요청 URL 동적 업데이트 가능.
    @GET("users/{user}/repos")
    Call<List<Item>> listRepos(
            @Path("user") String user
    );

    @GET("group/{id}/users")
    Call<List<Item>> groupList(
            @Path("id") int groupId
    );// 대응하는 @Path를 메소드 매개변수에 명시

    @GET("group/{id}/users")
    Call<List<Item>> groupList(
            @Path("id") int groupId,
            @Query("sort") String sort
    );// 쿼리 매개 변수 명시 가능

    @GET("group/{id}/users")
    Call<List<Item>> groupList(
            @Path("id") int groupId,
            @QueryMap Map<String, String> options
    );// 보다 동적이며 다양한 쿼리 매개 변수들을 Map으로 사용 가능



    //REQUEST BODY :
    @POST("users/new")
    Call<Item> createUser(@Body Item item);
    //HTTP 요청 본문에 객체를 @Body 어노테이션을 통해 명시 가능
    //@Body 어노테이션을 사용하여 데이터를 클래스로 보냄


 =================================================================================================*/