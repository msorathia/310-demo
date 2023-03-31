import React from "react";
import {render, screen, fireEvent, act, waitFor, findByDisplayValue} from "@testing-library/react";
import Movie from '../pages/MovieSearch.jsx';
import axios from "axios";
import { BrowserRouter as Router } from "react-router-dom";
import MovieSearch from "../pages/MovieSearch.jsx";
import Montage from "../pages/Montage";
jest.mock('axios');
describe("Movie component", () => {
    jest.mock('axios');
    const mockDetails = "{\n" +
        "        rating: 7.5,\n" +
        "        releaseDate: \"2021-01-01\",\n" +
        "        genre: \"Action\",\n" +
        "        director: \"Christopher Nolan\",\n" +
        "        studio: \"Warner Bros.\",\n" +
        "        actors: \"John Doe, Jane Doe\"\n" +
        "    }";



    beforeEach(() => {
        jest.spyOn(Storage.prototype, 'setItem').mockImplementation(() => {});
        jest.spyOn(Storage.prototype, 'getItem').mockReturnValue({"1": true});


    });

    afterEach(() => {
        jest.restoreAllMocks();
    });

    test("renders search box", async () => {
        render(<Router><MovieSearch /></Router>);

        expect(screen.getByText("Search")).toBeInTheDocument();

    });

    // test("renders movie title and overview", async () => {
    //
    //     axios.get.mockResolvedValue({
    //         data: {
    //             page: 1,
    //             results: [
    //                 {
    //                     adult: false,
    //                     backdrop_path: '/vYJRSrZmUcL7FPhOeCk3n6rFRRp.jpg',
    //                     genre_ids: [18, 9648],
    //                     id: 846190,
    //                     original_language: 'fa',
    //                     original_title: 'Aghaz',
    //                     overview:
    //                         'Young man (Erfan) wants to do something important,It has a lot of risk for him But he must make a decision,Though everyone around them opposes doing so,The boy decides that...',
    //                     popularity: 0.987,
    //                     poster_path: '/jYxQqa3ZaXhhG89YEUZkxUfKFdm.jpg',
    //                     release_date: '2014-02-12',
    //                     title: 'Inception',
    //                     video: false,
    //                     vote_average: 10,
    //                     vote_count: 3,
    //                 },
    //             ],
    //             total_pages: 1,
    //             total_results: 1,
    //         }
    //     });
    //     render(<Router><MovieSearch /></Router>);
    //
    //     const input = screen.getByPlaceholderText('Search for movies');
    //
    //     fireEvent.change(input, { target: { value: 'inception' } });
    //
    //     fireEvent.click(screen.getByText('Search'));
    //
    //     jest.setTimeout(10000);
    //
    //     await (waitFor(() => screen.findByDisplayValue("corporate espionage"), {timeout:8000}));
    //
    //     expect(screen.getByText("Inception")).toBeInTheDocument();
    //
    // });
    //
    // test("clicking on movie requests additional details", async () => {
    //     const axiosMock = jest.spyOn(axios, "get").mockResolvedValue({
    //         data: mockDetails
    //     });
    //     render(<Router><Movie title="Test Movie" overview="Test Overview" id="1" /></Router>);
    //     fireEvent.click(screen.getByText("Test Movie"));
    //     expect(axiosMock).toHaveBeenCalledWith(
    //         "http://localhost:8080/getmoviedetails?id=1"
    //     );
    //     expect(screen.getByText("Average rating: 7.5")).toBeInTheDocument();
    //     expect(screen.getByText("Release date: 2021-01-01")).toBeInTheDocument();
    //     expect(screen.getByText("Genre: Action")).toBeInTheDocument();
    //     expect(screen.getByText("Director: Christopher Nolan")).toBeInTheDocument();
    //     expect(screen.getByText("Production Studio: Warner Bros.")).toBeInTheDocument();
    //     expect(screen.getByText("John Doe")).toBeInTheDocument();
    //     expect(screen.getByText("Jane Doe")).toBeInTheDocument();
    // });
    //
    // test("clicking on add button toggles watchlist", async () => {
    //     render(<Router><Movie title="Test Movie" overview="Test Overview" id="1" data-testid="movie-item" /></Router>);
    //     fireEvent.mouseEnter(screen.getByTestId("movie-item"));
    //     expect(screen.getByText("+")).toBeInTheDocument();
    //     fireEvent.click(screen.getByText("+"));
    //     expect(screen.getByText("Add to existing watchlist:")).toBeInTheDocument();
    //     expect(screen.getByText("Create a new watchlist:")).toBeInTheDocument();
    //     fireEvent.click(screen.getByText("Create"));
    //     expect(localStorage.setItem).toHaveBeenCalledWith(
    //         "watchlists",
    //         JSON.stringify([{ name: "", movies: ["1"] }])
    //     );
    //     expect(screen.getByText("+")).toHaveClass("added");
    // });
});