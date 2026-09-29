import {useState} from "react";
import {useNavigate} from "react-router-dom";
import { Link } from "react-router-dom";


function LoginForm(){
    const [email, setEmail]= useState("");
    const [password, setPassword] = useState("");
    const [loggedIn, setLoggedIn] = useState(false);
    const [error, setError] = useState("");
    const navigate = useNavigate();

   async function handleSubmit(event){
        event.preventDefault();
        setError("");
        const response= await fetch("http://localhost:8080/api/auth/login",{
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({email, password})
        })
        const data = await response.json();
        if (data.success) {
            localStorage.setItem("token", data.data.token);
            localStorage.setItem("user", JSON.stringify(data.data.user));
            setLoggedIn(true);
            navigate("/dashboard");
        }else{
            setError(data.message);
        }
    }
    return (
        <div>
            <h2>Login Form</h2>
            <form onSubmit={handleSubmit}>
                {error && <p>{error}</p>}
               Email: <input
                    type="email"
                    placeholder="Email"
                    value={email}
                    onChange={(event)=> setEmail(event.target.value)}/><br/>
                Password: <input
                type="password"
                value={password}
                placeholder="Password"
                onChange={(event) => setPassword(event.target.value)}
            /><br/>
                <p> {email}</p>
                <p> {password}</p>
                <button>Login</button>
                <p>Don't have an account? <Link to="/register">Register here</Link></p>
            </form>
            {loggedIn && (
                <p>Login Successful!</p>
            )}
        </div>
    )
}
export default LoginForm;