import axios from 'axios';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';

export default function Register() {
    const [formData, setFormData] = useState({
        email: '',
        password: '',
        address: ''
    });
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await axios.post('/api/auth/register', formData);
            alert('Registration successful! Please login.');
            navigate('/login');
        } catch (error) {
            console.error(error);
            alert('Registration failed');
        }
    };

    return (
        <div className="auth-form-container">
            <h2>Register</h2>
            <form onSubmit={handleSubmit}>
                <input
                    type="email"
                    placeholder="Email"
                    value={formData.email}
                    onChange={(e) => setFormData({ ...formData, email: e.target.value })}
                    required
                />
                <input
                    type="password"
                    placeholder="Password"
                    value={formData.password}
                    onChange={(e) => setFormData({ ...formData, password: e.target.value })}
                    required
                />
                <input
                    type="text"
                    placeholder="Address"
                    value={formData.address}
                    onChange={(e) => setFormData({ ...formData, address: e.target.value })}
                    required
                />
                <button type="submit">Register</button>
            </form>
        </div>
    );
}
