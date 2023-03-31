import React from "react";
import { render, screen, fireEvent } from "@testing-library/react";
import Movie from '../pages/MovieSearch.jsx';
import axios from "axios";

describe("Movie component", () => {
  const mockDetails = {
    rating: 7.5,
    releaseDate: "2021-01-01",
    genre: "Action",
    director: "Christopher Nolan",
    studio: "Warner Bros.",
    actors: "John Doe, Jane Doe"
  };

  beforeEach(() => {
    jest.spyOn(global, "localStorage", "get").mockReturnValue({
      "1": true
    });
    jest.spyOn(global.Storage.prototype, 'setItem').mockImplementation(() => {});
  });

  afterEach(() => {
    jest.restoreAllMocks();
  });

  test("renders movie title and overview", () => {
    render(<Movie title="Test Movie" overview="Test Overview" id="1" />);
    expect(screen.getByText("Test Movie")).toBeInTheDocument();
    expect(screen.getByText("Test Overview")).toBeInTheDocument();
  });

  test("clicking on movie requests additional details", async () => {
    const axiosMock = jest.spyOn(axios, "get").mockResolvedValue({
      data: mockDetails
    });
    render(<Movie title="Test Movie" overview="Test Overview" id="1" />);
    fireEvent.click(screen.getByText("Test Movie"));
    expect(axiosMock).toHaveBeenCalledWith(
      "http://localhost:8080/getmoviedetails?id=1"
    );
    expect(screen.getByText("Average rating: 7.5")).toBeInTheDocument();
    expect(screen.getByText("Release date: 2021-01-01")).toBeInTheDocument();
    expect(screen.getByText("Genre: Action")).toBeInTheDocument();
    expect(screen.getByText("Director: Christopher Nolan")).toBeInTheDocument();
    expect(screen.getByText("Production Studio: Warner Bros.")).toBeInTheDocument();
    expect(screen.getByText("John Doe")).toBeInTheDocument();
    expect(screen.getByText("Jane Doe")).toBeInTheDocument();
  });

  test("clicking on add button toggles watchlist", async () => {
    render(<Movie title="Test Movie" overview="Test Overview" id="1" />);
    fireEvent.mouseEnter(screen.getByTestId("movie-item"));
    expect(screen.getByText("+")).toBeInTheDocument();
    fireEvent.click(screen.getByText("+"));
    expect(screen.getByText("Add to existing watchlist:")).toBeInTheDocument();
    expect(screen.getByText("Create a new watchlist:")).toBeInTheDocument();
    fireEvent.click(screen.getByText("Create"));
    expect(localStorage.setItem).toHaveBeenCalledWith(
      "watchlists",
      JSON.stringify([{ name: "", movies: ["1"] }])
    );
    expect(screen.getByText("+")).toHaveClass("added");
  });
});
