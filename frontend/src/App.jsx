import Navbar from "./components/Navbar";
import LoginForm from "./components/LoginForm";
import Dashboard from "./components/Dashboard";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import ProtectedRoute from "./components/ProtectedRoute.jsx";
import RegisterForm from "./components/RegisterForm.jsx";

function App() {
    const username = "Swochhandita Ghimire";
  return (
      <div>
      <Navbar name={username}/>
        <BrowserRouter>
            <Routes>
                <Route path="/login" element={<LoginForm/>}/>
                <Route path="/dashboard" element={
                    <ProtectedRoute>
                        <Dashboard/>
                    </ProtectedRoute>}/>
                <Route path="/register" element={<RegisterForm/>}/>
            </Routes>
        </BrowserRouter>
      </div>
  );
}
export default App;