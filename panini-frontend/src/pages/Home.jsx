import {Fragment, useEffect, useState} from "react";
import HomeNavbar from "../layout/HomeNavbar.jsx";
import axios from "axios";

const Home = () => {
    const [coach, setCoach] = useState();
    useEffect(() => {
        const fetchCoach = async () => {
            const response = await axios.get("http://localhost:8080/api/v1/coach?id=1");
            setCoach(response.data);
            console.log(response.data);
        }
        fetchCoach();
    }, []);

    const [players, setPlayers] = useState();
    useEffect(() => {
        const fetchPlayers = async () => {
            const response = await axios.get("http://localhost:8080/api/v1/player?team_id=1");
            setPlayers(response.data);
            console.log(response.data);
        }
        fetchPlayers();
    }, []);

    if (!coach || !players) {
        return (
            <Fragment>
                <HomeNavbar />

                <div>Loading...</div>
            </Fragment>
        );
    } else {
        return (
            <Fragment>
                <HomeNavbar/>

                <div className="d-flex justify-content-center py-4">
                    <div className="card" style={{width: "250px"}}>
                        <img className="card-img-top" src={`data:image/jpg;base64,${coach.photo}`} alt="Card image cap"/>
                        <div className="card-body d-flex justify-content-center">
                            <p className="card-text">{coach.name}</p>
                        </div>
                    </div>
                </div>

                <div className="container-fluid">
                    <div className="row py-4">
                        {players.slice(0, 10).map((player, index) => (
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

                    <div className="row py-4">
                        {players.slice(10, 20).map((player, index) => (
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
}

export default Home;
