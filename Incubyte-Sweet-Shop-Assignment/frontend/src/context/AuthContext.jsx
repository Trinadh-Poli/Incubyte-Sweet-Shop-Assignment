import axios from 'axios';
import { createContext, useContext, useState } from 'react';

const AuthContext = createContext(null);

export const AuthProvider = ({ children }) => {
    const [token, setToken] = useState(localStorage.getItem('token'));
    const [user, setUser] = useState(() => {
        const token = localStorage.getItem('token');
        const role = localStorage.getItem('role');
        return token ? { role } : null;
    });

    const login = async (email, password) => {
        try {
            const response = await axios.post('/api/auth/login', { email, password });
            const newToken = response.data.token;
            const role = response.data.role;
            setToken(newToken);
            localStorage.setItem('token', newToken);
            localStorage.setItem('role', role);
            setUser({ email, role });
            return true;
        } catch (error) {
            console.error("Login failed", error);
            return false;
        }
    };

    const logout = () => {
        setToken(null);
        setUser(null);
        localStorage.removeItem('token');
    };

    return (
        <AuthContext.Provider value={{ user, token, login, logout }}>
            {children}
        </AuthContext.Provider>
    );
};

export const useAuth = () => useContext(AuthContext);
