import {useEffect}  from "react";

function Dashboard() {

    useEffect(() => {
        console.log("Dashboard Appeared")
    }, []);
    return(
        <div>
            <h1>Dashboard</h1>
            </div>
    )
}
export default Dashboard;