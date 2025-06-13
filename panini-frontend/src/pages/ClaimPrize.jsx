import {Fragment, useState} from "react";
import BackToHomeNavbar from "../layout/BackToHomeNavbar.jsx";
import axios from "axios";
import {serverUrl} from "../../serverUrl.js";
import prizeImage from '../assets/prize.png';

const ClaimPrize = () => {

    const [prize, setPrize] = useState(null);

    const handleClaimPrize = () => {
        const claimPrize = async () => {
            const token = localStorage.getItem("token");
            const response = await axios.get(`${serverUrl}/user-player/claim-prize`, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            setPrize(response.data);
        }
        claimPrize();
    }

    return (
        <Fragment>
            <BackToHomeNavbar />

            <div className="container py-4 d-flex justify-content-center align-content-center flex-column">
                <div className="d-flex justify-content-center">
                    <button className="btn btn-success" onClick={handleClaimPrize} style={{width: "200px"}}>Claim Prize</button>
                </div>

                {prize === null ? null : (
                    prize === true ? (
                        <div className="container d-flex flex-column justify-content-center align-items-center">
                            <div className="mt-4 alert alert-success text-center" style={{ maxWidth: "400px", margin: "auto" }}>
                                🎉 Congratulations! You are moving to Chinteni!
                            </div>
                            <img className="py-4" src={prizeImage} alt="prize" style={{width: "300px"}}/>
                        </div>
                    ) : (
                        <div className="mt-4 alert alert-warning text-center" style={{ maxWidth: "400px", margin: "auto" }}>
                            😢 Sorry, you have no prize to claim.
                        </div>
                    )
                )}
            </div>
        </Fragment>
    );
}

export default ClaimPrize;
