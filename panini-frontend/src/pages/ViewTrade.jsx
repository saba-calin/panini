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
            console.log(response.data);
        }
        const fetchReceiverPlayers = async () => {
            const response = await axios.get(`${serverUrl}/trade/receiver-players?trade-id=${id}`, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            setReceiverPlayers(response.data);
            console.log(response.data);
        }

        fetchProposerPlayers();
        fetchReceiverPlayers();
    }, []);

    return (
        <Fragment>
            <BackToTradesNavbar />

            <div className="d-flex py-4">
                <div className="container d-flex flex-column align-items-center">
                    <h1 className="text-center">You Give</h1>

                    <div style={{width: "80%", marginTop: "50px"}}>
                        <div className="row py-4">
                            {proposerPlayers.map((player, index) => (
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
                            {receiverPlayers.map((player, index) => (
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
