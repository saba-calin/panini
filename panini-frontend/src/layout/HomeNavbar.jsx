import {Link} from "react-router-dom";

const HomeNavbar = () => {
    return (
        <nav className="navbar navbar-expand-lg navbar-dark bg-primary">
            <div className="container-fluid">
                <a className="navbar-brand">Panini</a>
                <div>
                    <Link to={'/claim-prize'} className="btn btn-outline-light" style={{marginRight: "10px"}}>Claim Prize</Link>
                    <Link to={'/trades'} className="btn btn-outline-light" style={{marginRight: "10px"}}>View Trades</Link>
                    <Link to={'/start-trade'} className="btn btn-outline-light" style={{marginRight: "10px"}}>Start Trade</Link>
                    <Link to={'/doubles'} className="btn btn-outline-light" style={{marginRight: "10px"}}>See Doubles</Link>
                    <Link to={'/buy-pack'} className="btn btn-outline-light" style={{marginRight: "10px"}}>Buy Pack</Link>
                    <Link to={'/login'} className="btn btn-outline-light">Logout</Link>
                </div>
            </div>
        </nav>
    );
}

export default HomeNavbar;
