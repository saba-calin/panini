import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import '../node_modules/bootstrap/dist/js/bootstrap.bundle.min.js';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Home from "./pages/Home.jsx";
import Login from "./pages/auth/Login.jsx";
import Register from "./pages/auth/Register.jsx";
import BuyPack from "./pages/BuyPack.jsx";
import Doubles from "./pages/Doubles.jsx";
import ClaimPrize from "./pages/ClaimPrize.jsx";
import StartTrade from "./pages/StartTrade.jsx";

function App() {

  return (
    <BrowserRouter>
        <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/login" element={<Login /> } />
            <Route path="/register" element={<Register /> } />
            <Route path="/buy-pack" element={<BuyPack /> } />
            <Route path="/doubles" element={<Doubles /> } />
            <Route path="/claim-prize" element={<ClaimPrize /> } />
            <Route path="/start-trade" element={<StartTrade /> } />
        </Routes>
    </BrowserRouter>
  );
}

export default App;
