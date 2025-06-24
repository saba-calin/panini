import {Fragment, useEffect, useState} from "react";
import BackToHomeNavbar from "../layout/BackToHomeNavbar.jsx";
import axios from "axios";
import {serverUrl} from "../../serverUrl.js";
import {useNavigate} from "react-router-dom";

const Trades = () => {

    const navigate = useNavigate();

    const [trades, setTrades] = useState([]);
    useEffect(() => {
        const fetchTrades = async () => {
            const token = localStorage.getItem("token");
            const response = await axios.get(`${serverUrl}/trade`, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            setTrades(response.data);
        }
        fetchTrades();
    }, []);

    const handleViewTrade = (tradeId) => {
        navigate(`/trades/${tradeId}`);
    }

    return (
        <Fragment>
            <BackToHomeNavbar />

            <div className="container">
                <div className="py-4">
                    <table className="table border shadow">
                        <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Proposer</th>
                            <th scope="col">Receiver</th>
                            <th scope="col">Status</th>
                            <th scope="col" className="text-center">Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        {trades.map(trade => (
                            <tr key={trade.id}>
                                <td>{trade.id}</td>
                                <td>{trade.proposerUsername}</td>
                                <td>{trade.receiverUsername}</td>
                                <td>{trade.status}</td>
                                <td className="text-center">
                                    <button className="btn btn-primary" onClick={() => handleViewTrade(trade.id)}>View</button>
                                </td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                </div>
            </div>
        </Fragment>
    );
}

export default Trades;
