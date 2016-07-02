package com.dsliusar.web.controller;

import com.dsliusar.persistence.entity.Movie;
import com.dsliusar.services.security.AuthenticationService;
import com.dsliusar.services.service.CurrencyService;
import com.dsliusar.services.service.MovieMarkService;
import com.dsliusar.services.service.MovieService;
import com.dsliusar.tools.annotations.SecurityRolesAllowed;
import com.dsliusar.tools.enums.Roles;
import com.dsliusar.tools.http.entities.AllMoviesRequestDto;
import com.dsliusar.tools.http.entities.MovieAddRequest;
import com.dsliusar.tools.http.entities.UserSecureTokenEntity;
import com.dsliusar.web.dto.converter.MovieToDtoTransformer;
import com.dsliusar.web.dto.movies.AllMovieDto;
import com.dsliusar.web.dto.movies.AllMovieListDto;
import com.dsliusar.web.dto.movies.MovieByIdDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.dsliusar.web.dto.converter.RequestToMovieDtoTransformer.convertToDto;

@RestController
@RequestMapping(value = "/v1")
public class MoviesController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private MovieService genericMovieService;

    @Autowired
    private MovieToDtoTransformer movieToDtoTransformer;

    @Autowired
    private MovieMarkService genericMovieMarkService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private CurrencyService genericCurrencyService;


    /**
     * Provides XML format of the message for getting movies
     *
     * @param ratingOrder
     * @param priceOrder
     * @param pageNumber
     * @return
     */
    @RequestMapping(value = "/movies",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public AllMovieListDto getAllMoviesXml(@RequestParam(value = "rating", required = false) String ratingOrder
            , @RequestParam(value = "price", required = false) String priceOrder
            , @RequestParam(value = "page", required = false) Integer pageNumber
            , @RequestParam(value = "currency", required = false) String currency) {
        return new AllMovieListDto(getAllMovies(convertToDto(ratingOrder, priceOrder, pageNumber,currency)));
    }

    /**
     * Provides JSON format for getting movies
     *
     * @param ratingOrder
     * @param priceOrder
     * @param pageNumber
     * @return
     */
    @RequestMapping(value = "/movies",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<AllMovieDto> getAllMoviesJson(@RequestParam(value = "rating", required = false) String ratingOrder
            , @RequestParam(value = "price", required = false) String priceOrder
            , @RequestParam(value = "page", required = false) Integer pageNumber
            , @RequestParam(value = "currency", required = false) String currency) {

        return getAllMovies(convertToDto(ratingOrder, priceOrder, pageNumber,currency));
    }

    /**
     * Get movie by ID
     *
     * @param movieId
     * @return
     */
    @SecurityRolesAllowed(roles = {Roles.USER})
    @RequestMapping(value = "/movie/{movieId}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public ResponseEntity<?> getMovieById(@PathVariable Integer movieId,
                                          @RequestHeader(value = "security-token") String token,
                                          @RequestParam(value = "currency", required = false) String requestedCurrency) {
        LOGGER.info("Sending request to get movie with id = {}", movieId);
        long startTime = System.currentTimeMillis();
        UserSecureTokenEntity userSecureTokenEntity = authenticationService.getUserByToken(token);

        //get requested movie
        Movie movie = genericMovieService.getMovieById(movieId);

        //get user rating for requested movie if exists
        Double userRating = genericMovieService.getUserRating(userSecureTokenEntity.getUserId(),movieId);

        //get requested currency exchange
        String currencyExchanged =  genericCurrencyService.convertCurrencyToRequested(movie, requestedCurrency);

        //convert to DTO
        MovieByIdDto movieByIdDto = movieToDtoTransformer.transformMovieByIdToDto(movie,userRating, currencyExchanged);
        if (userSecureTokenEntity.getUserId() != null) {
            movieByIdDto.setUserRating(genericMovieService.getUserRating(userSecureTokenEntity.getUserId(),
                    movieId));
        }
        LOGGER.info("movie received, it took {} ms", System.currentTimeMillis() - startTime);
        return new ResponseEntity<>(movieByIdDto, HttpStatus.OK);
    }

    /**
     * Adding posted movie to DB
     *
     * @param movieAddRequest
     * @return
     */
    @SecurityRolesAllowed(roles = {Roles.ADMIN})
    @RequestMapping(value = "/movies/add",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> addMovie(@RequestBody MovieAddRequest movieAddRequest) {
        genericMovieService.addMovie(movieAddRequest);
        return new ResponseEntity<>("Movie " + movieAddRequest.getMovieId() + " were added successfully", HttpStatus.OK);
    }

    /**
     * Updating movie in DB
     * Previous movie will be marked as not eligible
     *
     * @param movieUpdateRequest
     * @return
     */
    @SecurityRolesAllowed(roles = {Roles.ADMIN})
    @RequestMapping(value = "/movies/update",
            method = RequestMethod.PUT,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> updateMovie(@RequestBody MovieAddRequest movieUpdateRequest) {
        genericMovieService.updateMovie(movieUpdateRequest);
        return new ResponseEntity<>("Movie " + movieUpdateRequest.getMovieId() + " were updated successfully", HttpStatus.OK);
    }

    /**
     * mark movie for deletion
     * This marked movied will be deleted at mid-night
     *
     * @param movieId
     * @return
     */
    @SecurityRolesAllowed(roles = {Roles.ADMIN})
    @RequestMapping(value = "/movies/mark/{movieId}",
            method = RequestMethod.DELETE,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> markMovie(@PathVariable Integer movieId) {
        genericMovieMarkService.markMovieToDelete(movieId);
        return new ResponseEntity<>("Movie were mark for deletion, movieId " + movieId, HttpStatus.OK);
    }

    /**
     * Un mark movie.
     * If movie was marked for deletion than un-mark it.
     *
     * @param movieId
     * @return
     */
    @SecurityRolesAllowed(roles = {Roles.ADMIN})
    @RequestMapping(value = "/movies/unmark/{movieId}",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> unMarkMovie(@PathVariable Integer movieId) {
        genericMovieMarkService.unMarkMovieToDelete(movieId);
        return new ResponseEntity<>("Movie were Un - marked from deletion, movieId " + movieId, HttpStatus.OK);
    }

    /**
     * Get all Movies from DB
     *
     * @param movieAllRequest
     * @return
     */
    private List<AllMovieDto> getAllMovies(AllMoviesRequestDto movieAllRequest) {
        LOGGER.info("Sending request to get all movies");
        long startTime = System.currentTimeMillis();
        List<Movie> movieList = genericMovieService.getAllMovies(movieAllRequest);
        String currency = genericCurrencyService.convertCurrencyToRequested(movieList, movieAllRequest.getCurrency());
        List<AllMovieDto> movieDtoList = movieToDtoTransformer.transformAllMovieToDto(movieList, currency);
        LOGGER.info("All movies received, it took {} ms", System.currentTimeMillis() - startTime);
        return movieDtoList;
    }
}
