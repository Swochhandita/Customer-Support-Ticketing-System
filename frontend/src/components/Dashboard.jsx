import {useEffect}  from "react";
import {useNavigate} from "react-router-dom";

function Dashboard() {
    const user = JSON.parse(localStorage.getItem("user"));
    const navigate = useNavigate();

    function handleLogout() {
        localStorage.removeItem("token");
        localStorage.removeItem("user");
        navigate("/login");
    }
    return(
        <div>
            <h1>Dashboard</h1>
            <p>Welcome to the Dashboard {user?.firstName}!</p>
            <p>You are successfully logged in.</p>
            <br/>
            <br/>
            <button onClick={handleLogout}>Logout</button>
            </div>
    );
}
export default Dashboard;