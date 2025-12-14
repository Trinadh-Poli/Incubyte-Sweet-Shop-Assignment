import axios from 'axios';
import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';

export default function SweetList() {
    const [sweets, setSweets] = useState([]);
    const { user, token } = useAuth();
    const navigate = useNavigate();

    useEffect(() => {
        fetchSweets();
    }, [token]);

    const fetchSweets = async () => {
        try {
            const config = token ? { headers: { Authorization: `Bearer ${token}` } } : {};
            const response = await axios.get('/api/sweets', config);
            setSweets(response.data);
        } catch (error) {
            console.error('Error fetching sweets:', error);
        }
    };

    const handlePurchase = async (id) => {
        try {
            await axios.post(`/api/sweets/${id}/purchase`, {}, {
                headers: { Authorization: `Bearer ${token}` }
            });
            alert('Purchase successful!');
            fetchSweets(); // Refresh list to update quantity
        } catch (error) {
            alert('Purchase failed: ' + (error.response?.data?.message || error.message));
        }
    };

    return (
        <div className="sweet-list">
            <h2>Available Sweets</h2>
            <div className="grid">
                {sweets.length === 0 && user?.role !== 'ROLE_ADMIN' ? (
                    // Fallback for empty state or loading (though usually handled by 'landing-page' logic if backend requires auth)
                    <div className="empty-state">
                        <p>No sweets found! (Wait for admin to restock)</p>
                    </div>
                ) : (
                    <>
                        {user?.role === 'ROLE_ADMIN' && (
                            <div className="admin-controls" style={{ marginBottom: '2rem', padding: '1.5rem', backgroundColor: '#f8f9fa', borderRadius: '8px', border: '1px solid #dee2e6' }}>
                                <h3 style={{ marginTop: 0, marginBottom: '1rem' }}>Add New Sweet</h3>
                                <form onSubmit={async (e) => {
                                    e.preventDefault();
                                    const formData = new FormData(e.target);
                                    try {
                                        await axios.post('/api/sweets',
                                            {
                                                name: formData.get('name'),
                                                category: formData.get('category'),
                                                price: parseFloat(formData.get('price')),
                                                quantity: parseInt(formData.get('quantity'))
                                            },
                                            { headers: { Authorization: `Bearer ${token}` } }
                                        );
                                        fetchSweets();
                                        e.target.reset();
                                        alert("Sweet added successfully!");
                                    } catch (err) {
                                        alert("Error adding sweet: " + err.message);
                                    }
                                }} style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fit, minmax(150px, 1fr))', gap: '1rem', alignItems: 'end' }}>
                                    <div>
                                        <label style={{ display: 'block', marginBottom: '0.5rem', fontSize: '0.9rem' }}>Name</label>
                                        <input name="name" required placeholder="e.g. Dark Chocolate" style={{ width: '100%', padding: '0.5rem' }} />
                                    </div>
                                    <div>
                                        <label style={{ display: 'block', marginBottom: '0.5rem', fontSize: '0.9rem' }}>Category</label>
                                        <input name="category" required placeholder="e.g. Chocolates" style={{ width: '100%', padding: '0.5rem' }} />
                                    </div>
                                    <div>
                                        <label style={{ display: 'block', marginBottom: '0.5rem', fontSize: '0.9rem' }}>Price (₹)</label>
                                        <input name="price" type="number" step="0.01" required placeholder="0.00" style={{ width: '100%', padding: '0.5rem' }} />
                                    </div>
                                    <div>
                                        <label style={{ display: 'block', marginBottom: '0.5rem', fontSize: '0.9rem' }}>Quantity</label>
                                        <input name="quantity" type="number" required placeholder="100" style={{ width: '100%', padding: '0.5rem' }} />
                                    </div>
                                    <button type="submit" style={{ padding: '0.6rem 1rem', backgroundColor: '#e91e63', color: 'white', border: 'none', borderRadius: '4px', cursor: 'pointer', fontWeight: 'bold' }}>
                                        + Add Sweet
                                    </button>
                                </form>
                            </div>
                        )}
                        {sweets.map(sweet => (
                            <div key={sweet.id} className="card">
                                <h3>{sweet.name}</h3>
                                <p className="category">{sweet.category}</p>
                                <p className="price">₹{sweet.price.toFixed(2)}</p>
                                <p className="quantity">In Stock: {sweet.quantity}</p>
                                <button
                                    onClick={() => {
                                        if (token) {
                                            handlePurchase(sweet.id);
                                        } else {
                                            alert("Please login to purchase sweets!");
                                            navigate('/login');
                                        }
                                    }}
                                    disabled={sweet.quantity === 0}
                                >
                                    {sweet.quantity === 0 ? 'Out of Stock' : 'Purchase'}
                                </button>
                            </div>
                        ))}
                    </>
                )}
            </div>
        </div>
    );
}
