import {Link} from "react-router-dom";

const RegisterNavbar = () => {
    return (
        <nav className="navbar navbar-expand-lg navbar-dark bg-primary">
            <div className="container-fluid">
                <a className="navbar-brand">Panini</a>
                <Link to={'/login'} className="btn btn-outline-light">Login</Link>
            </div>
        </nav>
    );
}

export default RegisterNavbar;
