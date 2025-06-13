import {Fragment, useEffect, useState} from "react";
import HomeNavbar from "../layout/HomeNavbar.jsx";
import axios from "axios";
import {serverUrl} from "../../serverUrl.js";

const Home = () => {
    const handleNext = () => {
        setCurrentTeam(currentTeam + 1);
    }

    const handlePrev = () => {
        setCurrentTeam(currentTeam - 1);
    }

    const handleInput = (e) => {
        const value = parseInt(e.target.value);
        if (!isNaN(value) && value >= 1 && value <= 24) {
            setCurrentTeam(value);
        }
    }

    const [currentTeam, setCurrentTeam] = useState(1);
    useEffect(() => {
        fetchCoach();
        fetchPlayers();
    }, [currentTeam]);

    const [coach, setCoach] = useState();
    const [players, setPlayers] = useState();

    const fetchCoach = async () => {
        const token = localStorage.getItem("token");
        const response = await axios.get(`${serverUrl}/coach?id=${currentTeam}`, {
            headers: {
                Authorization: `Bearer ${token}`
            }
        });
        setCoach(response.data);
    }

    const fetchPlayers = async () => {
        const token = localStorage.getItem("token");
        const response = await axios.get(`${serverUrl}/team?team_id=${currentTeam}`, {
            headers: {
                Authorization: `Bearer ${token}`
            }
        });
        setPlayers(response.data);
        console.log(response.data);
    }

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
                    {currentTeam > 1 ? (<button className="btn btn-outline-primary" style={{marginRight: "10px"}} onClick={handlePrev}>prev</button>) : null }
                    <input type="number" min="1" max="24" className="form-control" value={currentTeam} style={{width: "70px"}} onInput={handleInput} />
                    <h5 style={{marginTop: "5px"}}>/24</h5>
                    {currentTeam < 24 ? (<button className="btn btn-outline-primary" style={{marginLeft: "10px"}} onClick={handleNext}>Next</button>) : null }
                </div>

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
                                <div className={`card ${player.titular ? 'bg-warning' : 'bg-dark-subtle'}`} style={{width: "150px"}}>
                                    {player.unlocked ? (
                                        <img className="card-img-top" src={`data:image/jpg;base64,${player.photo}`} alt="Card image cap"/>
                                    ) : (
                                        <div className="card-img-top" style={{height: "150px"}} />
                                    )}
                                    <div className="card-body d-flex justify-content-center">
                                        <p className="card-text">{player.name} ({player.shirtNumber})</p>
                                    </div>
                                </div>
                            </div>
                        ))}
                    </div>

                    <div className="row py-4">
                        {players.slice(10, 20).map((player, index) => (
                            <div className="col-sm" key={index}>
                                <div className={`card ${player.titular ? 'bg-warning' : 'bg-dark-subtle'}`} style={{width: "150px"}}>
                                    {player.unlocked ? (
                                        <img className="card-img-top" src={`data:image/jpg;base64,${player.photo}`} alt="Card image cap"/>
                                    ) : (
                                        <div className="card-img-top" style={{height: "150px"}} />
                                    )}
                                    <div className="card-body d-flex justify-content-center">
                                        <p className="card-text">{player.name} ({player.shirtNumber})</p>
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
