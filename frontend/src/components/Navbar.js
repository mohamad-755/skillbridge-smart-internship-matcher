import React from 'react';
import { Link } from 'react-router-dom';
import './Navbar.css';

function Navbar() {
  return (
    <nav className="navbar">
      <div className="navbar-brand">
        <Link to="/">SkillBridge</Link>
      </div>
      <div className="navbar-links">
        <Link to="/">Find Matches</Link>
        <Link to="/opportunities">Opportunities</Link>
      </div>
    </nav>
  );
}

export default Navbar;