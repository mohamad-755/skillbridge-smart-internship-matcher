import React from 'react';
import Opportunities from './Opportunities';
import './AdminDashboard.css';

function AdminDashboard({ user }) {
  return (
    <div className="admin-dashboard">
      <div className="admin-header">
        <h1>Admin Dashboard</h1>
        <p>Manage internship opportunities available to students.</p>
      </div>

      <Opportunities user={user} />
    </div>
  );
}

export default AdminDashboard;