import React, { useContext, useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "../assets/css/Login.css";
import { getData } from "../assets/JSON/API";
import Navbar from "./Navbar";
import "../assets/css/Navbar.css";
import Footer from "./Footer";
import { Context } from "./GlobeData";
import ToasterFunc from "./ToasterFunc";
import toast from "react-hot-toast";
import Loginimage from "../assets/images/Loginimage.jpg";
import axios from "axios";

export const Login = () => {
    const { LogIn } = useContext(Context);

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [role, setRole] = useState("user");
    const [data, setData] = useState([]);

    useEffect(() => {
        const fetch = async () => {
            const res = await getData();
            setData(res.data);
        };
        fetch();
    }, []);

    const navigate = useNavigate();

    const onhandleUsername = (e) => {
        setUsername(e.target.value);
    };

    const onhandlePassword = (e) => {
        setPassword(e.target.value);
    };

    const onhandleRole = (e) => {
        setRole(e.target.value);
    };

    const onhandleLogin = (e) => {
        navigate("/Signup");
    };

    // const onhandleSubmit = async (e) => {
    //     e.preventDefault();
    //     if (username === "") {
    //         toast.error("Please enter Username");
    //         return;
    //     } else if (password === "") {
    //         toast.error("Please enter Password");
    //         return;
    //     }

    //     // Standard admin credentials check
    //     if (username === "Admin" && password === "Admin@123") {
    //         navigate("/adminUsers");
    //         return;
    //     }

    //     // Check if role is admin but credentials are not standard admin credentials
    //     if (role === "admin") {
    //         toast.error("Invalid admin credentials");
    //         return;
    //     }

    //     try {
    //         const response = await axios.get("http://localhost:3000/UserData", {
    //             params: {
    //                 username,
    //                 password,
    //             },
    //         });

    //         if (response.data.length > 0) {
    //             const user = response.data.find(
    //                 (user) => user.uname === username && user.password === password
    //             );

    //             if (user) {
    //                 LogIn(user);
    //                 toast.success("Login Successful");
    //                 setTimeout(() => {
    //                     if (role === "admin") {
    //                         navigate("/adminUsers");
    //                     } else {
    //                         navigate("/home");
    //                     }
    //                 }, 2000);
    //             } else {
    //                 toast.error("Invalid credentials");
    //             }
    //         } else {
    //             toast.error("Invalid credentials");
    //         }
    //     } catch (error) {
    //         toast.error("An error occurred. Please try again later.");
    //     }
    // };

    // const onhandleSubmit = async (e) => {
    //     e.preventDefault();
    
    //     if (username === "") {
    //         toast.error("Please enter Username");
    //         return;
    //     } else if (password === "") {
    //         toast.error("Please enter Password");
    //         return;
    //     }
    
        // Standard admin credentials check
    //     if (username === "Admin" && password === "Admin@123") {
    //         navigate("/adminUsers");
    //         return;
    //     }
    
    //     try {
    //         const response = await axios.get("http://localhost:3000/UserData");
    
    //         if (response.data.length > 0) {
    //             const user = response.data.find(
    //                 (user) => user.username === username && user.password === password
    //             );
    
    //             if (user) {
    //                 LogIn(user);
    //                 toast.success("Login Successful");
    //                 setTimeout(() => {
    //                     if (user.userRole === "Admin") {
    //                         navigate("/adminUsers");
    //                     } else {
    //                         navigate("/home");
    //                     }
    //                 }, 2000);
    //             } else {
    //                 toast.error("Invalid credentials");
    //             }
    //         } else {
    //             toast.error("Invalid credentials");
    //         }
    //     } catch (error) {
    //         console.error("Error during login attempt:", error);
    //         toast.error("An error occurred. Please try again later.");
    //     }
    // };
    const onhandleSubmit = async (e) => {
        e.preventDefault();
    
        if (username === "") {
            toast.error("Please enter Username");
            return;
        } else if (password === "") {
            toast.error("Please enter Password");
            return;
        }
    
        // Standard admin credentials check
        if (username === "Admin" && password === "Admin@123") {
            navigate("/adminUsers");
            return;
        }
    
        try {
            const response = await axios.get("http://localhost:3000/UserData");
    
            if (response.data.length > 0) {
                const user = response.data.find(
                    (user) => user.username === username && user.password === password
                );
    
                if (user) {
                    // Check if the user is attempting to use an admin role
                    if (role === "admin") {
                        toast.error("Invalid admin credentials");
                        return;
                    }
    
                    LogIn(user);
                    toast.success("Login Successful");
                    setTimeout(() => {
                        navigate("/home");
                    }, 2000);
                } else {
                    toast.error("Invalid credentials");
                }
            } else {
                toast.error("Invalid credentials");
            }
        } catch (error) {
            toast.error("An error occurred. Please try again later.");
        }
    };
    
    

    return (
        <div>
            <Navbar />
            <ToasterFunc />
            <h1>Login</h1>
            <div className="login-container">
                <img src={Loginimage} alt="Login Image" />
                <div className="Login">
                    <form>
                        <label>Username</label>
                        <input
                            type="text"
                            placeholder="Username"
                            onChange={onhandleUsername}
                            autoComplete="off"
                        />
                        <label>Password</label>
                        <input
                            type="password"
                            placeholder="Enter Password"
                            onChange={onhandlePassword}
                            autoComplete="new-password"
                        />
                        <div className="for-group">
                            <label htmlFor="role">Role:</label>
                            <select id="role" name="role" onChange={onhandleRole}>
                                <option value="user">User</option>
                                <option value="admin">Admin</option>
                            </select>
                        </div>
                        <button onClick={onhandleSubmit}>Login</button>
                        <p>
                            Don't have an account? <span onClick={onhandleLogin}>Sign Up</span>
                        </p>
                    </form>
                </div>
            </div>
            <div>
                <Footer />
            </div>
        </div>
    );
};

export default Login;
