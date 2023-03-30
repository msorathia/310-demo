import React from 'react';
import { render, screen, act } from '@testing-library/react';
import axios from 'axios';
import Montage from '../pages/Montage';
import { BrowserRouter as Router } from "react-router-dom";

jest.mock('axios');

beforeEach(() => {
    fetch.resetMocks();
    const mockedUsedNavigate = jest.fn();

    jest.mock('react-router-dom', () => ({
        ...jest.requireActual('react-router-dom'),
        useNavigate: () => mockedUsedNavigate,
    }));

});

test("image list is retrieved", async () => {
    const imageList = [
        "https://image.tmdb.org/t/p/w500/1m3W6cpgwuIyjtg5nSnPx7yFkXW.jpg",
        "https://image.tmdb.org/t/p/w500/u3EEVihxNbFIOvnUkBJIJK93Jcn.jpg",
        "https://image.tmdb.org/t/p/w500/tmlDFIUpGRKiuWm9Ixc6CYDk4y0.jpg",
        "https://image.tmdb.org/t/p/w500/edv5CZvWj09upOsy2Y6IwDhK8bt.jpg",
        "https://image.tmdb.org/t/p/w500/6FfCtAuVAW8XJjZ7eWeLibRLWTw.jpg",
        "https://image.tmdb.org/t/p/w500/kOVEVeg59E0wsnXmF9nrh6OmWII.jpg",
        "https://image.tmdb.org/t/p/w500/xfSAoBEm9MNBjmlNcDYLvLSMlnq.jpg",
        "https://image.tmdb.org/t/p/w500/RYMX2wcKCBAr24UyPD7xwmjaTn.jpg",
        "https://image.tmdb.org/t/p/w500/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg",
        "https://image.tmdb.org/t/p/w500/1mp4ViklKvA0WXXsNvNx0RBuiit.jpg"
    ];
    const idList = [
        "646385",
        "493922",
        "49018",
        "27205",
        "11",
        "181808",
        "1895",
        "24428",
        "299536",
        "1885"
    ]

    axios.get.mockResolvedValue({
        data: {
            "646385": "https://image.tmdb.org/t/p/w500/1m3W6cpgwuIyjtg5nSnPx7yFkXW.jpg",
            "493922": "https://image.tmdb.org/t/p/w500/u3EEVihxNbFIOvnUkBJIJK93Jcn.jpg",
            "49018": "https://image.tmdb.org/t/p/w500/tmlDFIUpGRKiuWm9Ixc6CYDk4y0.jpg",
            "27205": "https://image.tmdb.org/t/p/w500/edv5CZvWj09upOsy2Y6IwDhK8bt.jpg",
            "11": "https://image.tmdb.org/t/p/w500/6FfCtAuVAW8XJjZ7eWeLibRLWTw.jpg",
            "181808": "https://image.tmdb.org/t/p/w500/kOVEVeg59E0wsnXmF9nrh6OmWII.jpg",
            "1895": "https://image.tmdb.org/t/p/w500/xfSAoBEm9MNBjmlNcDYLvLSMlnq.jpg",
            "24428": "https://image.tmdb.org/t/p/w500/RYMX2wcKCBAr24UyPD7xwmjaTn.jpg",
            "299536": "https://image.tmdb.org/t/p/w500/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg",
            "1885": "https://image.tmdb.org/t/p/w500/1mp4ViklKvA0WXXsNvNx0RBuiit.jpg"
        }
    });

    await act(async () => {
        render(<Router><Montage /></Router>);
    });

    expect(screen.getByText('Montage')).toBeInTheDocument();
    expect(screen.getByRole('button')).toBeInTheDocument();
    expect(screen.getAllByRole('img')).toHaveLength(10);

    expect(axios.get).toHaveBeenCalledWith(
        'http://localhost:8080/getpicturemontage?user=',
    );

    expect(screen.getByAltText("movie image 1")).toHaveAttribute(
        'src',
        imageList[0],
    );
    expect(screen.getByAltText("movie image 2")).toHaveAttribute(
        'src',
        imageList[1],
    );
    expect(screen.getByAltText("movie image 3")).toHaveAttribute(
        'src',
        imageList[2],
    );
    expect(screen.getByAltText("movie image 4")).toHaveAttribute(
        'src',
        imageList[3],
    );
    expect(screen.getByAltText("movie image 5")).toHaveAttribute(
        'src',
        imageList[4],
    );
    expect(screen.getByAltText("movie image 6")).toHaveAttribute(
        'src',
        imageList[5],
    );
    expect(screen.getByAltText("movie image 7")).toHaveAttribute(
        'src',
        imageList[6],
    );
    expect(screen.getByAltText("movie image 8")).toHaveAttribute(
        'src',
        imageList[7],
    );
    expect(screen.getByAltText("movie image 9")).toHaveAttribute(
        'src',
        imageList[8],
    );
    expect(screen.getByAltText("movie image 10")).toHaveAttribute(
        'src',
        imageList[9],
    );


});

test("there's an error message if the backend does not respond", async () => {

    jest.spyOn(console, 'log');

    const errorMessage = "Fail!";
    axios.get.mockRejectedValue(new Error(errorMessage));

    await act(async () => {
        render(<Montage />);
    });

    expect(screen.getByText('Montage')).toBeInTheDocument();
    expect(screen.getByRole('button')).toBeInTheDocument();
    expect(screen.queryAllByRole('img')).toHaveLength(0);


    expect(axios.get).toHaveBeenCalledTimes(1);
    expect(axios.get).toHaveBeenCalledWith(
        'http://localhost:8080/getpicturemontage?user=',
    );

    expect(console.log.mock.calls.length).toHaveTextContent("Fail!");

});