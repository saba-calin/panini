import {Fragment, useEffect, useState} from "react";
import BackToTradesNavbar from "../layout/BackToTradesNavbar.jsx";
import {useParams} from "react-router-dom";
import axios from "axios";
import {serverUrl} from "../../serverUrl.js";

const ViewTrade = () => {

    const {id} = useParams();

    const [proposerPlayers, setProposerPlayers] = useState([]);
    const [receiverPlayers, setReceiverPlayers] = useState([]);
    useEffect(() => {
        const token = localStorage.getItem("token");
        const fetchProposerPlayers = async () => {
            const response = await axios.get(`${serverUrl}/trade/proposer-players?trade-id=${id}`, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            setProposerPlayers(response.data);
        }
        const fetchReceiverPlayers = async () => {
            const response = await axios.get(`${serverUrl}/trade/receiver-players?trade-id=${id}`, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            setReceiverPlayers(response.data);
        }

        fetchProposerPlayers();
        fetchReceiverPlayers();
    }, []);

    const [currentUsername, setCurrentUsername] = useState("");
    const [trade, setTrade] = useState({});
    useEffect(() => {
        const token = localStorage.getItem("token");
        const fetchCurrentUsername = async () => {
            const response = await axios.get(`${serverUrl}/auth/me`, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            setCurrentUsername(response.data);
        }
        const fetchTrade = async () => {
            const response = await axios.get(`${serverUrl}/trade/by-id?trade-id=${id}`, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            setTrade(response.data);
        }

        fetchCurrentUsername();
        fetchTrade();
    }, []);

    const handleAcceptOffer = () => {
        const token = localStorage.getItem("token");
        axios.post(`${serverUrl}/trade/accept-trade`, {
            id: id
        }, {
            headers: {
                Authorization: `Bearer ${token}`
            }
        })
        .then(() => {
            setTrade({
                ...trade,
                status: "ACCEPTED"
            })
        })
    }

    const handleDeclineOffer = () => {
        const token = localStorage.getItem("token");
        axios.post(`${serverUrl}/trade/decline-trade`, {
            id: id
        }, {
            headers: {
                Authorization: `Bearer ${token}`
            }
        })
        .then(() => {
            setTrade({
                ...trade,
                status: "REJECTED"
            })
        })
    }

    return (
        <Fragment>
            <BackToTradesNavbar />

            {(currentUsername === trade.receiverUsername && trade.status === "PENDING") ? (
                <div className="container d-flex align-items-center justify-content-center py-4">
                    <button className="btn btn-success" onClick={handleAcceptOffer} style={{marginRight: "10px"}}>Accept Offer</button>
                    <button className="btn btn-danger" onClick={handleDeclineOffer}>Decline Offer</button>
                </div>
            ) : (
                <div className="container d-flex align-items-center justify-content-center py-4">
                    <h3>Status: {trade.status}</h3>
                </div>
            )}

            <div className="d-flex py-4">
                <div className="container d-flex flex-column align-items-center">
                    <h1 className="text-center">You Give</h1>

                    <div style={{width: "80%", marginTop: "50px"}}>
                        <div className="row py-4">
                            {(currentUsername === trade.proposerUsername ? proposerPlayers : receiverPlayers).map((player, index) => (
                                <div className="col-sm" key={index}>
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
                    <h1 className="text-center">You Receive</h1>

                    <div style={{width: "80%", marginTop: "50px"}}>
                        <div className="row py-4">
                            {(currentUsername === trade.proposerUsername ? receiverPlayers : proposerPlayers).map((player, index) => (
                                <div className="col-sm" key={index}>
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

export default ViewTrade;
