import com.gk.kmpwallpaperapp.domain.model.Movie
import com.gk.kmpwallpaperapp.data.local.movie.MovieEntity


fun MovieDto.toMovieEntity(
    category: String
): MovieEntity {
    return MovieEntity(
        adult = adult ?: false,
        backdrop_path = backdropPath ?: "",
        original_language = originalLanguage ?: "",
        original_title = originalTitle ?: "",
        overview = overview ?: "",
        popularity = popularity ?: 0.0,
        poster_path = posterPath ?: "",
        release_date = releaseDate ?: "",
        title = title ?: "",
        video = video ?: false,
        vote_average = voteAverage ?: 0.0,
        vote_count = voteCount ?: 0,
        id = id ?: -1,
        category = category,
        genre_ids = try {
            genreIds?.joinToString(",") ?: "-1,-2"
        } catch (e: Exception) {
            "-1,-2"
        }
    )
}

fun MovieEntity.toMovie(
    category: String
): Movie {
    return Movie(
        adult = adult,
        backdrop_path = backdrop_path,
        original_language = original_language,
        original_title = original_title,
        overview = overview,
        popularity = popularity,
        poster_path = poster_path,
        release_date = release_date,
        title = title,
        video = video,
        vote_average = vote_average,
        vote_count = vote_count,
        id = id,
        category = category,
        genre_ids = try {
                    genre_ids.split(",").map { it.toInt() }
        } catch (e: Exception){
            listOf(-1, -2)
        }
    )
}