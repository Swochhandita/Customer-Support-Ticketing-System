import {useState} from "react";

function LoginForm(){
    const [email, setEmail]= useState("");
    const [password, setPassword] = useState("");

   async function handleSubmit(event){
        event.preventDefault();
        const response= await fetch("http://localhost:8080/api/auth/login",{
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({email, password})
        })
        const data = await response.json();
        if (data.success){
            localStorage.setItem("token", data.data.token);
            console.log("Login Success");
        }
    }
    return (
        <div>
            <h2>Login Form</h2>
            <form onSubmit={handleSubmit}>
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
            </form>
        </div>
    )
}
export default LoginForm;