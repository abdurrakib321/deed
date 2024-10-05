

import axios from 'axios';
import CardsList from './CardsList'; // Adjust the path as necessary
import FilterComponent from './FilterComponent'; // Adjust the path as necessary
import React, { useState } from 'react';

const CardFetcher = () => {
  const [cards, setCards] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const [filterError, setFilterError] = useState(null);
  const [searchClicked, setSearchClicked] = useState(false);

  const fetchCards = async (filters) => {
    setLoading(true);
    setError(null);
    setFilterError(null);
    setSearchClicked(true);

    const hasFilter = Object.values(filters).some(value => value !== null && value !== '');

    if (!hasFilter) {
      setFilterError('Please choose at least one filter.');
      setLoading(false);
      return;
    }

    if ((filters.createdAtStart && !filters.updatedAtStart) || (!filters.createdAtStart && filters.updatedAtStart)) {
      setFilterError('Please choose both Created At and Updated At filters.');
      setLoading(false);
      return;
    }

    const params = {};
    Object.keys(filters).forEach(key => {
      if (filters[key] !== null && filters[key] !== '') {
        if (filters[key] instanceof Date) {
          params[key] = filters[key].toISOString().split('T')[0];
        } else {
          params[key] = filters[key];
        }
      }
    });

    try {
      const response = await axios.get('/api/deed/filter', { params });
      setCards(response.data);
    } catch (err) {
      setError(err.message);
    }
    setLoading(false);
  };

  return (
    <div>
      <FilterComponent onSearch={fetchCards} />
      {loading && <div className="p-4">Loading...</div>}
      {filterError && <div className="p-4">{filterError}</div>}
      {error && <div className="p-4">Error: {error}</div>}
      {!loading && !error && searchClicked && cards.length === 0 && filterError === null && (
        <div className="p-4">Data not Available</div>
      )}
      {!loading && !error && cards.length > 0 && <CardsList cards={cards} />}
    </div>
  );
};

export default CardFetcher;

