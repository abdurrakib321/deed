
// export default Card;
import React, { useState } from 'react';
import './Card.css'; // Adjust the path as necessary
import { useNavigate } from 'react-router-dom';

const Card = ({ id, deedNo, volNo, pageNo, deedWriterName, buyerName, sellerName }) => {
  const [clicked, setClicked] = useState(false);
  console.log(id)
  const navigate = useNavigate();

  const handleClick = () => {
    setClicked(true);
//    setTimeout(() => {
      navigate(`/deed/${id}`);
//    }, 200); // Adjust the delay as necessary
  };

  return (
    <div
      className={`card ${clicked ? 'clicked' : ''}`}
      onClick={handleClick}
    >
      <h3 className="card-heading">Deed No: {deedNo}</h3>
      <p className="card-text"><strong>Volume No:</strong> {volNo}</p>
      <p className="card-text"><strong>Page No:</strong> {pageNo}</p>
      <p className="card-text"><strong>Writer Name:</strong> {deedWriterName}</p>
      <p className="card-text"><strong>Buyer Name:</strong> {buyerName}</p>
      <p className="card-text"><strong>Seller Name:</strong> {sellerName}</p>
    </div>
  );
};

export default Card;
