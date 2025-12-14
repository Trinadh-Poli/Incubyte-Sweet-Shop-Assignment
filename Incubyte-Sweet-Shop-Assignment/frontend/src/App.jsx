import { Route, BrowserRouter as Router, Routes } from 'react-router-dom';
import './App.css';
import NavBar from './components/NavBar';
import { AuthProvider } from './context/AuthContext';
import Login from './pages/Login';
import Register from './pages/Register';

import SweetList from './components/SweetList';

function Home() {
    return (
        <div className="home">
            <h2>Welcome to the Sweet Shop!</h2>
            <SweetList />
        </div>
    );
}

function App() {
    return (
        <AuthProvider>
            <Router>
                <NavBar />
                <div className="container">
                    <Routes>
                        <Route path="/" element={<Home />} />
                        <Route path="/login" element={<Login />} />
                        <Route path="/register" element={<Register />} />
                    </Routes>
                </div>
            </Router>
        </AuthProvider>
    );
}

export default App;
