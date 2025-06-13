import {Fragment, useState} from "react";
import BackToHomeNavbar from "../layout/BackToHomeNavbar.jsx";
import axios from "axios";
import {serverUrl} from "../../serverUrl.js";

const BuyPack = () => {

    const [players, setPlayers] = useState([]);

    const handleBuyPack = () => {
        const token = localStorage.getItem("token");

        axios.post(`${serverUrl}/user-player/buy-pack`, {} ,{
            headers: {
                Authorization: `Bearer ${token}`
            }
        })
        .then(response => {
            setPlayers(response.data);
        })
    }

    return (
        <Fragment>
            <BackToHomeNavbar />

            <div className="container py-4 d-flex justify-content-center align-content-center flex-column">
                <div className="d-flex justify-content-center">
                    <button className="btn btn-success" onClick={handleBuyPack} style={{width: "200px"}}>Buy Pack</button>
                </div>

                <div className="row py-4">
                    {players.map((player, index) => (
                        <div className="col-sm" key={index}>
                            <div className="card" style={{width: "150px"}}>
                                <img className="card-img-top" src={`data:image/jpg;base64,${player.photo}`} alt="Card image cap"/>
                                <div className="card-body d-flex justify-content-center">
                                    <p className="card-text">{player.name}</p>
                                </div>
                            </div>
                        </div>
                    ))}
                </div>
            </div>
        </Fragment>
    );
}

export default BuyPack;
