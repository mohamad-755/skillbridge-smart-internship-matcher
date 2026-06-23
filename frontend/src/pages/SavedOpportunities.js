import React, { useEffect, useState } from 'react';
import { getSavedOpportunities } from '../api/api';
import { getErrorMessage } from '../api/errorUtils';
import './SavedOpportunities.css';

function SavedOpportunities() {
  const [savedOpportunities, setSavedOpportunities] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');


 useEffect(() => {
  const fetchSavedOpportunities = async () => {
    try {
      const response = await getSavedOpportunities();
      setSavedOpportunities(response.data);
    } catch (err) {
      setError(
        getErrorMessage(
          err,
          'We could not load your saved opportunities right now.'
        )
      );
    } finally {
      setLoading(false);
    }
  };

  fetchSavedOpportunities();
  }, []);

  if (loading) {
    return <div className="loading">Loading saved opportunities...</div>;
  }

  return (
    <div className="saved-opportunities">
      <div className="saved-header">
        <h1>Saved Opportunities</h1>
        <p>{savedOpportunities.length} saved opportunities</p>
      </div>

      {error && <p className="error">{error}</p>}

      {savedOpportunities.length === 0 && !error ? (
        <div className="empty-state">
          <h2>No saved opportunities yet</h2>
          <p>Save opportunities you want to revisit later.</p>
        </div>
      ) : (
        <div className="saved-list">
          {savedOpportunities.map((opportunity) => (
            <div key={opportunity.id} className="saved-card">
              <div className="saved-top">
                <div>
                  <h2>{opportunity.title}</h2>
                  <p className="saved-org">{opportunity.organization}</p>
                </div>
                <span className="saved-category">{opportunity.category}</span>
              </div>

              <p className="saved-description">{opportunity.description}</p>

              <div className="saved-meta">
                <span>{opportunity.location}</span>
                <span>Deadline: {opportunity.deadline}</span>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}

export default SavedOpportunities;
