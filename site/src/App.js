import React from "react";
import { Navigate, Route, Routes } from "react-router-dom";
import Home from "./pages/Home";
import Other from "./pages/Other";
import MovieSearch from "./pages/MovieSearch";
import Montage from "./pages/Montage";
import LoginSignUp from "./pages/Login-SignUp";

function App() {
    return (
        <div>
            <Routes>
                {/* Root pages, located in /pages/ */}
                <Route path="/" element={<Home />} />
                <Route path="/other" element={<Other />} />
                <Route path="/LoginSignUp" element={<LoginSignUp />} />
                <Route exact path="/MovieSearch" element={<MovieSearch />} />
                <Route exact path="/Montage" element={<Montage />} />
                {/* 404 page not found redirect */}
                <Route path="*" element={<Navigate to="/" replace />} />
            </Routes>
        </div>
    );
}

export default App;