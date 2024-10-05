

import React, { useState } from 'react';
import axios from 'axios';
import './Deed.css'; // Import the CSS file

const Deed = () => {
  const [deedDto, setDeedDto] = useState({
    deedNo: '',
    volNo: '',
    pageNo: '',
    createdAt: '' // Added createdAt field
  });

  const [buyerDto, setBuyerDto] = useState({
    name: '',
    fatherName: '',
    motherName: '',
    villageName: '',
    postOffice: '',
    policeStation: '',
    district: '',
    pin: '',
    state: ''
  });

  const [sellerDto, setSellerDto] = useState({
    name: '',
    fatherName: '',
    motherName: '',
    villageName: '',
    postOffice: '',
    policeStation: '',
    district: '',
    pin: '',
    state: ''
  });

  const [errors, setErrors] = useState({});
  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState('');

  const validate = () => {
    const newErrors = {};

    Object.keys(deedDto).forEach(key => {
      if (!deedDto[key]) newErrors[`deedDto.${key}`] = 'This field is required';
    });

    Object.keys(buyerDto).forEach(key => {
      if (!buyerDto[key]) newErrors[`buyerDto.${key}`] = 'This field is required';
    });

    Object.keys(sellerDto).forEach(key => {
      if (!sellerDto[key]) newErrors[`sellerDto.${key}`] = 'This field is required';
    });

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleSubmit = async () => {
    if (!validate()) return;

    setLoading(true);
    setMessage('');
    try {
      await axios.post('/api/deed', {
        deedDto,
        buyerDto,
        sellerDto
      });
      setLoading(false);
      setMessage('Deed Submitted successfully');
      setTimeout(() => {
        window.location.href = '/';
      }, 2000); // Redirect after 2 seconds
    } catch (error) {
      console.error('Error saving data:', error);
      setLoading(false);
      setMessage('Failed to add Deed');
    }
  };

  return (
    <div className="deed-form-container">
      <h1>Deed Form</h1>
      <div className="form-columns">
        <div className="column">
          {/* DeedDto Section */}
          <section className="section">
            <h2>Deed Details</h2>
            <div className="form-group">
              <label>Deed No:</label>
              <input
                type="number"
                placeholder="Enter Deed No"
                value={deedDto.deedNo}
                onChange={(e) => setDeedDto({ ...deedDto, deedNo: e.target.value })}
                disabled={loading}
              />
              {errors['deedDto.deedNo'] && <span className="error">{errors['deedDto.deedNo']}</span>}
            </div>
            <div className="form-group">
              <label>Vol No:</label>
              <input
                type="number"
                placeholder="Enter Vol No"
                value={deedDto.volNo}
                onChange={(e) => setDeedDto({ ...deedDto, volNo: e.target.value })}
                disabled={loading}
              />
              {errors['deedDto.volNo'] && <span className="error">{errors['deedDto.volNo']}</span>}
            </div>
            <div className="form-group">
              <label>Page No:</label>
              <input
                type="number"
                placeholder="Enter Page No"
                value={deedDto.pageNo}
                onChange={(e) => setDeedDto({ ...deedDto, pageNo: e.target.value })}
                disabled={loading}
              />
              {errors['deedDto.pageNo'] && <span className="error">{errors['deedDto.pageNo']}</span>}
            </div>
            <div className="form-group">
              <label>Created At:</label>
              <input
                type="date"
                placeholder="Select Date"
                value={deedDto.createdAt}
                onChange={(e) => setDeedDto({ ...deedDto, createdAt: e.target.value })}
                disabled={loading}
              />
              {errors['deedDto.createdAt'] && <span className="error">{errors['deedDto.createdAt']}</span>}
            </div>
          </section>
        </div>

        <div className="column">
          {/* BuyerDto Section */}
          <section className="section">
            <h2>Buyer Details</h2>
            <div className="form-group">
              <label>Name:</label>
              <input
                type="text"
                placeholder="Enter Name"
                value={buyerDto.name}
                onChange={(e) => setBuyerDto({ ...buyerDto, name: e.target.value })}
                disabled={loading}
              />
              {errors['buyerDto.name'] && <span className="error">{errors['buyerDto.name']}</span>}
            </div>
            <div className="form-group">
              <label>Father's Name:</label>
              <input
                type="text"
                placeholder="Enter Father's Name"
                value={buyerDto.fatherName}
                onChange={(e) => setBuyerDto({ ...buyerDto, fatherName: e.target.value })}
                disabled={loading}
              />
              {errors['buyerDto.fatherName'] && <span className="error">{errors['buyerDto.fatherName']}</span>}
            </div>
            <div className="form-group">
              <label>Mother's Name:</label>
              <input
                type="text"
                placeholder="Enter Mother's Name"
                value={buyerDto.motherName}
                onChange={(e) => setBuyerDto({ ...buyerDto, motherName: e.target.value })}
                disabled={loading}
              />
              {errors['buyerDto.motherName'] && <span className="error">{errors['buyerDto.motherName']}</span>}
            </div>
            <div className="form-group">
              <label>Village Name:</label>
              <input
                type="text"
                placeholder="Enter Village Name"
                value={buyerDto.villageName}
                onChange={(e) => setBuyerDto({ ...buyerDto, villageName: e.target.value })}
                disabled={loading}
              />
              {errors['buyerDto.villageName'] && <span className="error">{errors['buyerDto.villageName']}</span>}
            </div>
            <div className="form-group">
              <label>Post Office:</label>
              <input
                type="text"
                placeholder="Enter Post Office"
                value={buyerDto.postOffice}
                onChange={(e) => setBuyerDto({ ...buyerDto, postOffice: e.target.value })}
                disabled={loading}
              />
              {errors['buyerDto.postOffice'] && <span className="error">{errors['buyerDto.postOffice']}</span>}
            </div>
            <div className="form-group">
              <label>Police Station:</label>
              <input
                type="text"
                placeholder="Enter Police Station"
                value={buyerDto.policeStation}
                onChange={(e) => setBuyerDto({ ...buyerDto, policeStation: e.target.value })}
                disabled={loading}
              />
              {errors['buyerDto.policeStation'] && <span className="error">{errors['buyerDto.policeStation']}</span>}
            </div>
            <div className="form-group">
              <label>District:</label>
              <input
                type="text"
                placeholder="Enter District"
                value={buyerDto.district}
                onChange={(e) => setBuyerDto({ ...buyerDto, district: e.target.value })}
                disabled={loading}
              />
              {errors['buyerDto.district'] && <span className="error">{errors['buyerDto.district']}</span>}
            </div>
            <div className="form-group">
              <label>PIN:</label>
              <input
                type="number"
                placeholder="Enter PIN"
                value={buyerDto.pin}
                onChange={(e) => setBuyerDto({ ...buyerDto, pin: e.target.value })}
                disabled={loading}
              />
              {errors['buyerDto.pin'] && <span className="error">{errors['buyerDto.pin']}</span>}
            </div>
            <div className="form-group">
              <label>State:</label>
              <input
                type="text"
                placeholder="Enter State"
                value={buyerDto.state}
                onChange={(e) => setBuyerDto({ ...buyerDto, state: e.target.value })}
                disabled={loading}
              />
              {errors['buyerDto.state'] && <span className="error">{errors['buyerDto.state']}</span>}
            </div>
          </section>
        </div>

        <div className="column">
          {/* SellerDto Section */}
          <section className="section">
            <h2>Seller Details</h2>
            <div className="form-group">
              <label>Name:</label>
              <input
                type="text"
                placeholder="Enter Name"
                value={sellerDto.name}
                onChange={(e) => setSellerDto({ ...sellerDto, name: e.target.value })}
                disabled={loading}
              />
              {errors['sellerDto.name'] && <span className="error">{errors['sellerDto.name']}</span>}
            </div>
            <div className="form-group">
              <label>Father's Name:</label>
              <input
                type="text"
                placeholder="Enter Father's Name"
                value={sellerDto.fatherName}
                onChange={(e) => setSellerDto({ ...sellerDto, fatherName: e.target.value })}
                disabled={loading}
              />
              {errors['sellerDto.fatherName'] && <span className="error">{errors['sellerDto.fatherName']}</span>}
            </div>
            <div className="form-group">
              <label>Mother's Name:</label>
              <input
                type="text"
                placeholder="Enter Mother's Name"
                value={sellerDto.motherName}
                onChange={(e) => setSellerDto({ ...sellerDto, motherName: e.target.value })}
                disabled={loading}
              />
              {errors['sellerDto.motherName'] && <span className="error">{errors['sellerDto.motherName']}</span>}
            </div>
            <div className="form-group">
              <label>Village Name:</label>
              <input
                type="text"
                placeholder="Enter Village Name"
                value={sellerDto.villageName}
                onChange={(e) => setSellerDto({ ...sellerDto, villageName: e.target.value })}
                disabled={loading}
              />
              {errors['sellerDto.villageName'] && <span className="error">{errors['sellerDto.villageName']}</span>}
            </div>
            <div className="form-group">
              <label>Post Office:</label>
              <input
                type="text"
                placeholder="Enter Post Office"
                value={sellerDto.postOffice}
                onChange={(e) => setSellerDto({ ...sellerDto, postOffice: e.target.value })}
                disabled={loading}
              />
              {errors['sellerDto.postOffice'] && <span className="error">{errors['sellerDto.postOffice']}</span>}
            </div>
            <div className="form-group">
              <label>Police Station:</label>
              <input
                type="text"
                placeholder="Enter Police Station"
                value={sellerDto.policeStation}
                onChange={(e) => setSellerDto({ ...sellerDto, policeStation: e.target.value })}
                disabled={loading}
              />
              {errors['sellerDto.policeStation'] && <span className="error">{errors['sellerDto.policeStation']}</span>}
            </div>
            <div className="form-group">
              <label>District:</label>
              <input
                type="text"
                placeholder="Enter District"
                value={sellerDto.district}
                onChange={(e) => setSellerDto({ ...sellerDto, district: e.target.value })}
                disabled={loading}
              />
              {errors['sellerDto.district'] && <span className="error">{errors['sellerDto.district']}</span>}
            </div>
            <div className="form-group">
              <label>PIN:</label>
              <input
                type="number"
                placeholder="Enter PIN"
                value={sellerDto.pin}
                onChange={(e) => setSellerDto({ ...sellerDto, pin: e.target.value })}
                disabled={loading}
              />
              {errors['sellerDto.pin'] && <span className="error">{errors['sellerDto.pin']}</span>}
            </div>
            <div className="form-group">
              <label>State:</label>
              <input
                type="text"
                placeholder="Enter State"
                value={sellerDto.state}
                onChange={(e) => setSellerDto({ ...sellerDto, state: e.target.value })}
                disabled={loading}
              />
              {errors['sellerDto.state'] && <span className="error">{errors['sellerDto.state']}</span>}
            </div>
          </section>
        </div>
      </div>
      <button className="save-button" onClick={handleSubmit} disabled={loading}>
        {loading ? 'Saving...' : 'Save'}
      </button>
      {message && <div className="message">{message}</div>}
    </div>
  );
};

export default Deed;

