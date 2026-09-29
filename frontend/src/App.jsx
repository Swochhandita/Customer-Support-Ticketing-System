import Navbar from "./components/Navbar";
import {useState} from "react";
import TicketList from "./components/TicketList";
import LoginForm from "./components/LoginForm";
import Dashboard from "./components/Dashboard";

function App() {
    const username = "Swochhandita Ghimire";
    const [status, setStatus] = useState("Open");
  return (
      <div>
      <Navbar name={username}/>
      <h1>
        Customer Support Ticketing System
      </h1>
      <p> Welcome {username}! How can we help you?</p>
      <TicketList />
      <p> Ticket Status : {status}</p>
          {status === "Open" ? (
              <button onClick={() => setStatus("Resolved")}>
                  Mark as Resolved
              </button>) :
              (
                  <button onClick={() => setStatus("Open")}>
                      Reopen Ticket
                  </button>
              )
          }
          <LoginForm/>
      </div>
  );
}
export default App;