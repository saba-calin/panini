import {Fragment, useEffect, useState} from "react";
import BackToHomeNavbar from "../layout/BackToHomeNavbar.jsx";
import axios from "axios";
import {serverUrl} from "../../serverUrl.js";

const Doubles = () => {

    const [doubles, setDoubles] = useState([]);
    useEffect(() => {
        const fetchDoubles = async () => {
            const token = localStorage.getItem("token");
            const response = await axios.get(`${serverUrl}/user-player/doubles`, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            })
            setDoubles(response.data);
        }
        fetchDoubles();
    }, []);

    return (
        <Fragment>
            <BackToHomeNavbar />

            <div className="container">
                <div className="row py-4">
                    {doubles.map(player => (
                        <div className="col-sm" key={player.id}>
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
        </Fragment>
    );
}

export default Doubles;
