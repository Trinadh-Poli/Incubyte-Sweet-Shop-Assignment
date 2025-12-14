import { Link } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';

export default function NavBar() {
    const { user, logout } = useAuth();

    return (
        <nav className="navbar">
            <h1>Sweet Shop</h1>
            <div className="links">
                <Link to="/">Home</Link>
                {user ? (
                    <button onClick={logout}>Logout</button>
                ) : (
                    <>
                        <Link to="/login">Login</Link>
                        <Link to="/register">Register</Link>
                    </>
                )}
            </div>
        </nav>
    );
}
