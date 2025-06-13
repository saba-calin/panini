import {Link} from "react-router-dom";

const BackToHomeNavbar = () => {
    return (
        <nav className="navbar navbar-expand-lg navbar-dark bg-primary">
            <div className="container-fluid">
                <a className="navbar-brand">Panini</a>
                <Link to={'/'} className="btn btn-outline-light">Back</Link>
            </div>
        </nav>
    );
}

export default BackToHomeNavbar;
