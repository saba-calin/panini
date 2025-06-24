import {Link} from "react-router-dom";

const BackToTradesNavbar = () => {
    return (
        <nav className="navbar navbar-expand-lg navbar-dark bg-primary">
            <div className="container-fluid">
                <a className="navbar-brand">Panini</a>
                <Link to={'/trades'} className="btn btn-outline-light">Back</Link>
            </div>
        </nav>
    );
}

export default BackToTradesNavbar;
