import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import './Navbar.css';

function Navbar({ user, setUser }) {
  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem('skillbridgeUser');
    setUser(null);
    navigate('/login');
  };

  return (
    <nav className="navbar">
      <div className="navbar-brand">
        <Link to="/">SkillBridge</Link>
      </div>

      <div className="navbar-links">
        {user?.role === 'ADMIN' ? (
          <Link to="/admin">Admin Dashboard</Link>
        ) : (
          <Link to="/">Find Matches</Link>
        )}

        <Link to="/opportunities">Opportunities</Link>

        {user?.role === 'STUDENT' && (
          <>
            <Link to="/saved">Saved</Link>
            <Link to="/applications">Applications</Link>
          </>
        )}

        {user ? (
          <>
            <span className="navbar-user">Hi, {user.name}</span>
            <button className="logout-btn" onClick={handleLogout}>
              Logout
            </button>
          </>
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

export default Navbar;