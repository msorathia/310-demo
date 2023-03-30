import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import "../styles/index.css";

function Montage() {

    const [userName, setUserName] = useState("");
    const [imageList, setImageList] = useState([]);
    const [idList, setIdList] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        getImageList(userName);
        }, []
    );

    const getImageList = async (userName) => {
        try {
            const response = await axios.get(
                `http://localhost:8080/getpicturemontage?user=${userName}`
            );
            console.log("Received response!");
            console.log(typeof response.data)
            console.log(response.data);
            let parsedResponse = response.data;
            let responseIds = Object.keys(parsedResponse);
            let responseUrls = Object.values(parsedResponse);
            console.log("parsed response length");
            console.log(parsedResponse.length);

            setImageList(responseUrls);
            console.log("image list!");
            console.log(imageList);
            setIdList(responseIds);
        } catch (error) {
            console.error(error);
        }
    }

    return (
        <div>
            <nav>
                <button onClick={() => navigate("/")}>Home</button>
            </nav>
            <h1>Montage</h1>
            <div className="montage">
                <img src={imageList[0]} alt= "movie image 1" className="montage-image" id={idList[0]} style={{transform: 'rotate(-10deg)', zIndex: 10}} />
                <img src={imageList[1]} alt= "movie image 2" className="montage-image" id={idList[1]} style={{transform: 'rotate(27deg)', zIndex: 9, top: '10px', left: '10px'}} />
                <img src={imageList[2]} alt= "movie image 3" className="montage-image" id={idList[2]} style={{transform: 'rotate(-36deg)', zIndex: 8, top: '20px', left: '20px'}} />
                <img src={imageList[3]} alt= "movie image 4" className="montage-image" id={idList[3]} style={{transform: 'rotate(44deg)', zIndex: 7, top: '30px', left: '30px'}} />
                <img src={imageList[4]} alt= "movie image 5" className="montage-image" id={idList[4]} style={{transform: 'rotate(-32deg)', zIndex: 6, top: '40px', left: '40px'}} />
                <img src={imageList[5]} alt= "movie image 6" className="montage-image" id={idList[5]} style={{transform: 'rotate(43deg)', zIndex: 5, top: '50px', left: '50px'}} />
                <img src={imageList[6]} alt= "movie image 7" className="montage-image" id={idList[6]} style={{transform: 'rotate(-35deg)', zIndex: 4, top: '60px', left: '60px'}} />
                <img src={imageList[7]} alt= "movie image 8" className="montage-image" id={idList[7]} style={{transform: 'rotate(12deg)', zIndex: 3, top: '70px', left: '70px'}} />
                <img src={imageList[8]} alt= "movie image 9" className="montage-image" id={idList[8]} style={{transform: 'rotate(-36deg)', zIndex: 2, top: '80px', left: '80px'}} />
                <img src={imageList[9]} alt= "movie image 10" className="montage-image" id={idList[9]} style={{transform: 'rotate(22deg)', zIndex: 1, top: '90px', left: '90px'}} />
            </div>


        </div>
    );
}

export default Montage;