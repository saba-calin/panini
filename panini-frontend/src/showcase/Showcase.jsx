import {Fragment} from "react";
import haaland from "./haaland.jpg";
import messi from "./messi.jpg";
import neymar from "./neymar.jpg";
import ronaldo from "./ronaldo.jpg";
import mbappe from "./mbappe.jpg";

const Showcase = () => {
    const players = [
        {
            name: "Erling Haaland",
            photo: haaland
        },
        {
            name: "Lionel Messi",
            photo: messi
        },
        {
            name: "Neymar Junior",
            photo: neymar
        },
        {
            name: "Cristiano Ronaldo",
            photo: ronaldo
        },
        {
            name: "Kylian Mbappe",
            photo: mbappe
        }
    ];

    return (
        <Fragment>
            <div className="min-h-screen flex flex-col items-center justify-start py-12 bg-gradient-to-br from-sky-50 via-blue-100 to-indigo-200">
                {/* Buy Pack Button */}
                <button
                    className="mb-10 px-10 py-6 rounded-full bg-gradient-to-r from-indigo-500 to-purple-600 text-white font-semibold text-xl shadow-xl hover:shadow-2xl hover:scale-105 transition-all duration-300 ease-in-out"
                    style={{marginBottom: "150px"}}
                >
                    🎁 Buy Pack
                </button>

                {/* "You got:" Title */}
                <h2 className="text-3xl font-extrabold text-gray-800 mb-10 tracking-wide" style={{marginBottom: "50px"}}>
                    🎉 You got:
                </h2>

                {/* Player Cards */}
                <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-5 gap-8 px-4 w-full max-w-6xl">
                    {players.map((player, index) => (
                        <div
                            key={index}
                            className="relative backdrop-blur-xl bg-white/30 border border-white/40 rounded-3xl shadow-lg overflow-hidden transform hover:scale-105 hover:-rotate-1 transition duration-300"
                        >
                            <img
                                className="w-full h-44 object-cover"
                                src={player.photo}
                                alt={player.name}
                            />
                            <div className="p-4 text-center">
                                <p className="text-gray-900 font-bold text-lg tracking-wide">
                                    {player.name}
                                </p>
                            </div>
                            {/* Decorative Gradient Overlay */}
                            <div className="absolute inset-0 bg-gradient-to-t from-indigo-300/20 to-transparent pointer-events-none"></div>
                        </div>
                    ))}
                </div>
            </div>
        </Fragment>
    );
};

export default Showcase;
