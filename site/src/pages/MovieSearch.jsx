import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import "../styles/index.css";


const Movie = ({title, overview, id}) => {
    const [showPopup, setShowPopup] = useState(false);
    const [extraDetails, setExtraDetails] = useState([])
    const [actorList, setActorList] = useState([])

    let rating = null;
    let releaseDate = null;
    let genre = null;
    let studio = null;

    const handleDetailsRequest = async (event) => {
        setShowPopup(!showPopup);
        event.preventDefault();
        try {

            const response = await axios.get(
                `http://localhost:8080/getmoviedetails?id=${id}`
            );
            console.log(response.data)
            console.log("received response")
            setExtraDetails(response.data);
            console.log("Actors")
            console.log(response.data.actors)
            if (response.data.actors == "") {
                setActorList(["No actors found"]);
            }
            else {
                setActorList(response.data.actors.split(","));
            }
            // console.log(actorList);
        } catch (error) {
            console.error(error);
        }
    };

    return (

        <div key={id} className="movie-item" onClick ={handleDetailsRequest}>
            <h2>{title}</h2>
            <p>{overview}</p>

            {showPopup && (
                <div>
                    <p>Average rating: {extraDetails.rating}</p>
                    <p>Release date: {extraDetails.releaseDate}</p>
                    <p>Genre: {extraDetails.genre}</p>
                    <p>Director: {extraDetails.director}</p>
                    <p>Production Studio: {extraDetails.studio}</p>
                    <div>
                        <h4>Actors</h4>
                        <div style={{ maxHeight: '200px', overflowY: 'scroll' }}>
                            {actorList.map((actor, index) => (
                                <div key={index}>{actor}</div>
                            ))}
                        </div>
                    </div>
                </div>
            )}
        </div>


    )



};

function MovieSearch() {
    const [searchType, setSearchType] = useState("keyword");
    const [searchQuery, setSearchQuery] = useState("");
    const [page, setPage] = useState(1);
    const [totalPages, setTotalPages] = useState(1);
    const [results, setResults] = useState([]);
    const [showPopup, setShowPopup] = useState(false);
    const navigate = useNavigate();

    const handleSearchTypeChange = (event) => {
        setSearchType(event.target.value);
    };

    const handleSearchQueryChange = (event) => {
        setSearchQuery(event.target.value);
    };

    const handleSearch = async (event) => {
        event.preventDefault();
        try {
            console.log("waiting for repsonse");
            console.log(`query is ${searchQuery} and type is ${searchType} and page is ${page}`);
            const response = await axios.get(
                `http://localhost:8080/getmovies?query=${searchQuery}&type=${searchType}&page=${page}`
            );
            console.log(response)
            console.log("received response")
            setResults(response.data.results);
            setTotalPages(response.data.total_pages);
        } catch (error) {
            console.error(error);
        }
    };

    const handleLoadMore = async () => {
        try {
            console.log("waiting for repsonse")
            const response = await axios.get(
                `http://localhost:8080/getmovies?query=${searchQuery}&type=${searchType}&page=${page+1}`
            );
            console.log("received response")
            setResults([...results, ...response.data.results]);
            setPage(page + 1);
        } catch (error) {
            console.error(error);
        }
    };

    const expandDetailsHandler = () => {
        setShowPopup(!showPopup);
    };

    const handleClosePopup = () => {
        setShowPopup(false);
    };

    // function convertGenre (id) {
    //     switch(id) {
    //         case 28:
    //             return "Action;"
    //         case 12:
    //             return "Adventure"
    //         case 16:
    //             return "Animation"
    //         case 35:
    //             return "comedy";
    //         case 80:
    //             return "Crime"
    //         case 99:
    //             return "Documentary"
    //         case 18:
    //             return "Drama"
    //         case 10751:
    //             return "Family"
    //         case 14:
    //             return "Fantasy"
    //         case 36:
    //             return "History"
    //         case 27:
    //             return "Horror"
    //         case 10402:
    //             return "Music"
    //         case 9648:
    //             return "Mystery"
    //         case 10749:
    //             return "Romance"
    //         case 878:
    //             return "Science Fiction"
    //         case 10770:
    //             return "TV Movie"
    //         case 53:
    //             return "Thriller"
    //         case 10752:
    //             return "War"
    //         case 37:
    //             return "Western"
    //         default:
    //             return "None"
    //     }
    // }

    async function getDirector(id) {
        const response = await axios.get(`https://api.themoviedb.org/3/movie/${id}/credits?api_key=ff57dcbbb4e4d05ab0c3964b5590c76f`)
            .then(response => response.json())
            .then((jsonData)=>jsonData.crew.filter(({job})=> job ==='Director'));
        return response;
    }


    return (
        <div>
            <nav>
                <button onClick={() => navigate("/")}>Home</button>
            </nav>
            <h1>Movie Search</h1>
            <form onSubmit={handleSearch}>
                <div>
                    <label>
                        <input
                            type="radio"
                            value="keyword"
                            checked={searchType === "keyword"}
                            onChange={handleSearchTypeChange}
                        />
                        Keyword
                    </label>
                    <label>
                        <input
                            type="radio"
                            value="actor"
                            checked={searchType === "actor"}
                            onChange={handleSearchTypeChange}
                        />
                        Actor
                    </label>
                    <label>
                        <input
                            type="radio"
                            value="title"
                            checked={searchType === "title"}
                            onChange={handleSearchTypeChange}
                        />
                        Title
                    </label>
                </div>
                <div>
                    <input
                        type="text"
                        value={searchQuery}
                        onChange={handleSearchQueryChange}
                    />
                    <button type="submit">Search</button>
                </div>
            </form>
            {results.length === 0 ? (
                <div className="no-results">No results found.</div>
            ) : (
                //INSERT MOVIE CALL HERE
                <div className="movie-list">
                    {results.slice(0, page * 10).map((movie) => (
                        <Movie key={movie.id} id={movie.id} overview={movie.overview} title={movie.title}/>
                    ))}

                </div>
            )}
            {page < totalPages && (
                <button onClick={handleLoadMore}>Load More Results</button>
            )}
        </div>
    );
}

export default MovieSearch;