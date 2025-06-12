import {Fragment, useState} from "react";
import LoginNavbar from "../../layout/auth/LoginNavbar.jsx";
import {useNavigate} from "react-router-dom";
import axios from "axios";
import {serverUrl} from "../../../serverUrl.js";

const Login = () => {

    const navigate = useNavigate();

    const [errorMessage, setErrorMessage] = useState("");

    const handleLogin = (e) => {
        e.preventDefault();

        const formData = new FormData(e.target);
        const formattedData = Object.fromEntries(formData.entries());

        const user = {
            username: formattedData.username,
            password: formattedData.password
        }

        axios.post(`${serverUrl}/auth/login`, user)
            .then(response => {
                const token = response.data.token;
                localStorage.setItem("token", token);
                navigate("/");
            })
            .catch(error => {
                setErrorMessage(error.response.data.error);
            });
    }

    return (
        <Fragment>
            <LoginNavbar />

            <div className="container py-4">
                <div className="row">
                    <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
                        <h2 className="text-center">
                            Login
                        </h2>
                        <form role="form" className="mb-3 text-center" onSubmit={handleLogin}>
                            <label htmlFor="username" className="form-label">Username</label>
                            <input type="text" id="username" name="username" className="form-control" placeholder="Username" style={{marginBottom: "10px"}} />

                            <label htmlFor="password" className="form-label">Password</label>
                            <input type="password" id="password" name="password" className="form-control" placeholder="Password" style={{marginBottom: "50px"}} />

                            <p className="text-danger">{errorMessage}</p>

                            <button type="submit" className="btn btn-outline-primary">Login</button>
                        </form>
                    </div>
                </div>
            </div>
        </Fragment>
    );
}

export default Login;
