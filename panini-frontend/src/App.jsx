import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Home from "./pages/Home.jsx";
import Login from "./pages/auth/Login.jsx";
import Register from "./pages/auth/Register.jsx";
import BuyPack from "./pages/BuyPack.jsx";
import Doubles from "./pages/Doubles.jsx";

function App() {

  return (
    <BrowserRouter>
        <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/login" element={<Login /> } />
            <Route path="/register" element={<Register /> } />
            <Route path="/buy-pack" element={<BuyPack /> } />
            <Route path="/doubles" element={<Doubles /> } />
        </Routes>
    </BrowserRouter>
  );
}

export default App;
