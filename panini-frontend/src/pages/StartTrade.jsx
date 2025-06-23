import {Fragment, useEffect, useState} from "react";
import BackToHomeNavbar from "../layout/BackToHomeNavbar.jsx";
import axios from "axios";
import {serverUrl} from "../../serverUrl.js";

const StartTrade = () => {

    const [proposerDoubles, setProposerDoubles] = useState([]);
    const [proposerTradeDoubles, setProposerTradeDoubles] = useState([]);

    const [receiverDoubles, setReceiverDoubles] = useState([]);
    const [receiverTradeDoubles, setReceiverTradeDoubles] = useState([]);

    const [usernames, setUsernames] = useState([]);
    const [currentUsername, setCurrentUsername] = useState(null);

    useEffect(() => {
        getProposerDoubles();
        getUsernames();
    }, []);

    useEffect(() => {
        if (currentUsername === null) {
            return;
        }
        getReceiverDoubles();
    }, [currentUsername]);

    const handleProposerClick = (id) => {
        const double = proposerDoubles.find(d => d.id === id);
        setProposerDoubles(proposerDoubles.filter(d => d.id !== id));
        setProposerTradeDoubles([...proposerTradeDoubles, double]);
    }

    const handleProposerTradeClick = (id) => {
        const double = proposerTradeDoubles.find(d => d.id === id);
        setProposerTradeDoubles(proposerTradeDoubles.filter(d => d.id !== id));
        setProposerDoubles([...proposerDoubles, double]);
    }

    const handleReceiverClick = (id) => {
        const double = receiverDoubles.find(d => d.id === id);
        setReceiverDoubles(receiverDoubles.filter(d => d.id !== id));
        setReceiverTradeDoubles([...receiverTradeDoubles, double]);
    }

    const handleReceiverTradeClick = (id) => {
        const double = receiverTradeDoubles.find(d => d.id === id);
        setReceiverTradeDoubles(receiverTradeDoubles.filter(d => d.id !== id));
        setReceiverDoubles([...receiverDoubles, double]);
    }

    const getProposerDoubles = async () => {
        const token = localStorage.getItem("token");
        const response = await axios.get(`${serverUrl}/user-player/doubles`, {
            headers: {
                Authorization: `Bearer ${token}`
            }
        });
        setProposerDoubles(response.data);
    }

    const getReceiverDoubles = async () => {
        const token = localStorage.getItem("token");
        const response = await axios.get(`${serverUrl}/user-player/doubles/by-username?username=${currentUsername}`, {
            headers: {
                Authorization: `Bearer ${token}`
            }
        });
        setReceiverDoubles(response.data);
    }

    const getUsernames = async () => {
        const token = localStorage.getItem("token");
        const response = await axios.get(`${serverUrl}/user`, {
            headers: {
                Authorization: `Bearer ${token}`
            }
        });
        setUsernames(response.data);
    }

    const handleTrade = () => {
        const proposerTradeIds = proposerTradeDoubles.map(player => player.id);
        const receiverTradeIds = receiverTradeDoubles.map(player => player.id);

        const trade = {
            proposerTradeIds: proposerTradeIds,
            receiverTradeIds: receiverTradeIds,
            receiver: currentUsername
        };
        console.log(trade);

        const token = localStorage.getItem("token");
        axios.post(`${serverUrl}/trade/make-trade`, trade, {
            headers: {
                Authorization: `Bearer ${token}`
            }
        });
    }

    return (
        <Fragment>
            <BackToHomeNavbar />

            <div className="container d-flex flex-column py-4 align-items-center">
                <button className="btn btn-success" onClick={handleTrade}>Make Offer</button>

                <div className="dropdown py-4">
                    <button className="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                        {currentUsername === null ? "Select Trading Partner" : currentUsername }
                    </button>
                    <ul className="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                        {usernames.map(username => (
                            <li key={username}>
                                <button className="dropdown-item" onClick={() => setCurrentUsername(username)}>{username}</button>
                            </li>
                        ))}
                    </ul>
                </div>
            </div>


            <div className="d-flex py-4">
                <div className="container d-flex flex-column align-items-center">
                    <h1 className="text-center">You Give</h1>

                    <div style={{width: "80%", marginTop: "50px"}}>
                        <div className="row py-4">
                            {proposerTradeDoubles.map((player, index) => (
                                <div className="col-sm" key={index} onClick={() => handleProposerTradeClick(player.id)}>
                                    <div className="card" style={{width: "150px"}}>
                                        <img className="card-img-top" src={`data:image/jpg;base64,${player.photo}`} alt="Card image cap"/>
                                        <div className="card-body d-flex justify-content-center">
                                            <p className="card-text">{player.name} ({player.shirtNumber})</p>
                                        </div>
                                    </div>
                                </div>
                            ))}
                        </div>
                    </div>

                    <div style={{width: "80%", marginTop: "150px"}}>
                        <div className="row py-4">
                            {proposerDoubles.map((player, index) => (
                                <div className="col-sm" key={index} onClick={() => handleProposerClick(player.id)}>
                                    <div className="card" style={{width: "150px"}}>
                                        <img className="card-img-top" src={`data:image/jpg;base64,${player.photo}`} alt="Card image cap"/>
                                        <div className="card-body d-flex justify-content-center">
                                            <p className="card-text">{player.name} ({player.shirtNumber})</p>
                                        </div>
                                    </div>
                                </div>
                            ))}
                        </div>
                    </div>
                </div>

                <div className="container d-flex flex-column align-items-center">
                    <h1 className="text-center">You Get</h1>

                    <div style={{width: "80%", marginTop: "50px"}}>
                        <div className="row py-4">
                            {receiverTradeDoubles.map((player, index) => (
                                <div className="col-sm" key={index} onClick={() => handleReceiverTradeClick(player.id)}>
                                    <div className="card" style={{width: "150px"}}>
                                        <img className="card-img-top" src={`data:image/jpg;base64,${player.photo}`} alt="Card image cap"/>
                                        <div className="card-body d-flex justify-content-center">
                                            <p className="card-text">{player.name} ({player.shirtNumber})</p>
                                        </div>
                                    </div>
                                </div>
                            ))}
                        </div>
                    </div>

                    <div style={{width: "80%", marginTop: "150px"}}>
                        <div className="row py-4">
                            {receiverDoubles.map((player, index) => (
                                <div className="col-sm" key={index} onClick={() => handleReceiverClick(player.id)}>
                                    <div className="card" style={{width: "150px"}}>
                                        <img className="card-img-top" src={`data:image/jpg;base64,${player.photo}`} alt="Card image cap"/>
                                        <div className="card-body d-flex justify-content-center">
                                            <p className="card-text">{player.name} ({player.shirtNumber})</p>
                                        </div>
                                    </div>
                                </div>
                            ))}
                        </div>
                    </div>
                </div>
            </div>

        </Fragment>
    );
}

export default StartTrade;
