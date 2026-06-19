import React, { useEffect, useState } from 'react';
import {
  getApplications,
  updateApplicationStatus,
  deleteApplication,
} from '../api/api';
import './Applications.css';

function Applications() {
  const [applications, setApplications] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  const user = JSON.parse(localStorage.getItem('skillbridgeUser'));

  useEffect(() => {
    fetchApplications();
  }, []);

  const fetchApplications = async () => {
    if (!user?.id) return;

    try {
      const response = await getApplications(user.id);
      setApplications(response.data);
    } catch (err) {
      setError('We could not load your applications right now.');
    } finally {
      setLoading(false);
    }
  };

  const handleStatusChange = async (applicationId, status) => {
    try {
      const response = await updateApplicationStatus(applicationId, status);
      setApplications(
        applications.map((application) =>
          application.id === applicationId ? response.data : application
        )
      );
    } catch (err) {
      setError('We could not update this application status.');
    }
  };

  const handleDelete = async (applicationId) => {
    try {
      await deleteApplication(applicationId);
      setApplications(applications.filter((application) => application.id !== applicationId));
    } catch (err) {
      setError('We could not remove this application.');
    }
  };

  if (loading) {
    return <div className="loading">Loading applications...</div>;
  }

  return (
    <div className="applications">
      <div className="applications-header">
        <h1>Applications</h1>
        <p>{applications.length} tracked applications</p>
      </div>

      {error && <p className="error">{error}</p>}

      {applications.length === 0 && !error ? (
        <div className="empty-state">
          <h2>No applications yet</h2>
          <p>Apply to opportunities and track their progress here.</p>
        </div>
      ) : (
        <div className="applications-list">
          {applications.map((application) => (
            <div key={application.id} className="application-card">
              <div className="application-top">
                <div>
                  <h2>{application.title}</h2>
                  <p className="application-org">{application.organization}</p>
                </div>

                <select
                  className="status-select"
                  value={application.status}
                  onChange={(e) => handleStatusChange(application.id, e.target.value)}
                >
                  <option value="APPLIED">Applied</option>
                  <option value="INTERVIEWING">Interviewing</option>
                  <option value="ACCEPTED">Accepted</option>
                  <option value="REJECTED">Rejected</option>
                </select>
              </div>

              <div className="application-meta">
                <span>{application.category}</span>
                <span>{application.location}</span>
                <span>Deadline: {application.deadline}</span>
              </div>

              <button
                className="remove-application-btn"
                onClick={() => handleDelete(application.id)}
              >
                Remove
              </button>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}

export default Applications;