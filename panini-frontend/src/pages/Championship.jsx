import {Fragment, useEffect, useState} from "react";
import SockJS from "sockjs-client/dist/sockjs"
import { Client } from "@stomp/stompjs";
import BackToHomeNavbar from "../layout/BackToHomeNavbar.jsx";

const Championship = () => {
    const [roundOf16, setRoundOf16] = useState([]);
    const [quarterFinal, setQuarterFinal] = useState([]);
    const [semiFinal, setSemiFinal] = useState([]);
    const [final, setFinal] = useState([]);
    const [winner, setWinner] = useState([]);

    useEffect(() => {
        const socket = new SockJS("http://localhost:8080/api/v1/ws");
        const client = new Client({
            webSocketFactory: () => socket,
            onConnect: () => {
                console.log("Connected to WebSocket");

                client.subscribe("/topic/greeting", (message) => {
                    const content = new TextDecoder("utf-8").decode(message._binaryBody);
                    const data = JSON.parse(content);

                    setRoundOf16(data[0]);
                    setQuarterFinal(data[1]);
                    setSemiFinal(data[2]);
                    setFinal(data[3]);
                    setWinner(data[4]);
                });
            },
        });

        client.activate();

        return () => {
            client.deactivate();
        };
    }, []);

    return (
        <Fragment>
            <BackToHomeNavbar />

            <div className="container d-flex py-4 justify-content-sm-between align-content-center">
                <ul className="list-group">
                    <li className="list-group-item active text-center">Round Of 16</li>
                    {roundOf16.map((team, index) => (
                        <li key={index} className="list-group-item">{team.name}</li>
                    ))}
                </ul>

                <ul className="list-group">
                    <li className="list-group-item active text-center">Quarter-Final</li>
                    {quarterFinal.map((team, index) => (
                        <li key={index} className="list-group-item">{team.name} ({team.homeScore}-{team.awayScore})</li>
                    ))}
                </ul>

                <ul className="list-group">
                    <li className="list-group-item active text-center">Semi-Final</li>
                    {semiFinal.map((team, index) => (
                        <li key={index} className="list-group-item">{team.name} ({team.homeScore}-{team.awayScore})</li>
                    ))}
                </ul>

                <ul className="list-group">
                    <li className="list-group-item active text-center">Final</li>
                    {final.map((team, index) => (
                        <li key={index} className="list-group-item">{team.name} ({team.homeScore}-{team.awayScore})</li>
                    ))}
                </ul>

                <ul className="list-group">
                    <li className="list-group-item active text-center">Winner</li>
                    {winner.map((team, index) => (
                        <li key={index} className="list-group-item">{team.name} ({team.homeScore}-{team.awayScore})</li>
                    ))}
                </ul>
            </div>
        </Fragment>
    );
}

export default Championship;
