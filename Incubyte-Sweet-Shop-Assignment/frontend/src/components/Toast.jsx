import { useEffect } from 'react';

export default function Toast({ message, type, onClose }) {
    useEffect(() => {
        const timer = setTimeout(() => {
            onClose();
        }, 3000);
        return () => clearTimeout(timer);
    }, [onClose]);

    const styles = {
        position: 'fixed',
        bottom: '20px',
        right: '20px',
        backgroundColor: type === 'error' ? '#ff5252' : '#4caf50',
        color: 'white',
        padding: '1rem 2rem',
        borderRadius: '8px',
        boxShadow: '0 4px 12px rgba(0,0,0,0.15)',
        zIndex: 1000,
        animation: 'slideIn 0.3s ease-out',
        fontWeight: 'bold',
        display: 'flex',
        alignItems: 'center',
        gap: '10px'
    };

    return (
        <div style={styles}>
            <span>{type === 'error' ? '⚠️' : '✅'}</span>
            {message}
        </div>
    );
}
