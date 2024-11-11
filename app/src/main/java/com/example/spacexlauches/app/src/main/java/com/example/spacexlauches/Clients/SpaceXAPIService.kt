import com.example.spacexlaunches.Launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.Response

interface SpaceXApiService {
    @GET("launches")
    suspend fun getLaunches(): Response<List<Launch>>
}

object SpaceXApiClient {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.spacexdata.com/v5/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: SpaceXApiService = retrofit.create(SpaceXApiService::class.java)
}