
// import React, { useState, useEffect } from 'react';
// import { useParams } from 'react-router-dom';
// import axios from 'axios';
// import './DeedDetails.css'; // Import the CSS file

// const DeedDetails = () => {
//   const { id } = useParams(); // Get the ID from the URL
//   const [deedDto, setDeedDto] = useState({
//     deedNo: '',
//     volNo: '',
//     pageNo: '',
//     deedWriterName: ''
//   });

//   const [buyerDto, setBuyerDto] = useState({
//     name: '',
//     fatherName: '',
//     motherName: '',
//     villageName: '',
//     postOffice: '',
//     policeStation: '',
//     district: '',
//     pin: '',
//     state: ''
//   });

//   const [sellerDto, setSellerDto] = useState({
//     name: '',
//     fatherName: '',
//     motherName: '',
//     villageName: '',
//     postOffice: '',
//     policeStation: '',
//     district: '',
//     pin: '',
//     state: ''
//   });

//   const [errors, setErrors] = useState({});
//   const [loading, setLoading] = useState(false);
//   const [message, setMessage] = useState('');
//   const [editable, setEditable] = useState({
//     deedDto: false,
//     buyerDto: false,
//     sellerDto: false
//   });

//   useEffect(() => {
//     const fetchDeedDetails = async () => {
//       setLoading(true);
//       try {
//         const response = await axios.get(`http://localhost:4000/api/deed/${id}`);
//         setDeedDto(response.data.deedDto);
//         setBuyerDto(response.data.buyerDto);
//         setSellerDto(response.data.sellerDto);
//       } catch (err) {
//         console.error('Error fetching data:', err);
//       }
//       setLoading(false);
//     };

//     fetchDeedDetails();
//   }, [id]);

//   const validate = (form) => {
//     const newErrors = {};

//     Object.keys(form).forEach(key => {
//       if (!form[key]) newErrors[key] = 'This field is required';
//     });

//     setErrors(newErrors);
//     return Object.keys(newErrors).length === 0;
//   };

//   const handleSave = async (formName) => {
//     let form;
//     let url;

//     switch (formName) {
//       case 'deedDto':
//         form = deedDto;
//         url = `http://localhost:4000/api/deed/?deedId=${id}`;
//         break;
//       case 'buyerDto':
//         form = buyerDto;
//         url = `http://localhost:4000/api/deed/buyer?deedId=${id}`;
//         break;
//       case 'sellerDto':
//         form = sellerDto;
//         url = `http://localhost:4000/api/deed/seller?deedId=${id}`;
//         break;
//       default:
//         return;
//     }

//     if (!validate(form)) return;

//     setLoading(true);
//     setMessage('');
//     try {
//       await axios.patch(url, form);
//       setLoading(false);
//       setMessage('Form saved successfully');
//       setEditable({ ...editable, [formName]: false });
//     } catch (err) {
//       console.error('Error saving data:', err);
//       setLoading(false);
//       setMessage('Failed to save form');
//     }
//   };

//   const handleEdit = (formName) => {
//     setEditable({ ...editable, [formName]: true });
//   };

//   const renderForm = (form, setForm, formName) => (
//     <>
//       {Object.keys(form).map((key) => (
//         <div className="form-group" key={key}>
//           <label>{key.split(/(?=[A-Z])/).join(' ')}:</label>
//           <input
//             type="text"
//             value={form[key]}
//             onChange={(e) => setForm({ ...form, [key]: e.target.value })}
//             disabled={loading || !editable[formName]}
//           />
//           {errors[key] && <span className="error">{errors[key]}</span>}
//         </div>
//       ))}
//       <div className="button-group">
//         <button className="edit-button" onClick={() => handleEdit(formName)} disabled={loading}>
//           Edit
//         </button>
//         <button className="edit-button" onClick={() => handleSave(formName)} disabled={loading || !editable[formName]}>
//           {loading ? 'Saving...' : 'Save'}
//         </button>
//       </div>
//     </>
//   );

//   return (
//     <div className="deed-details-container">
//       <h1>Deed Details</h1>
//       <div className="form-columns">
//         <div className="column">
//           <section className="section">
//             <h2>Deed Details</h2>
//             {renderForm(deedDto, setDeedDto, 'deedDto')}
//           </section>
//         </div>

//         <div className="column">
//           <section className="section">
//             <h2>Buyer Details</h2>
//             {renderForm(buyerDto, setBuyerDto, 'buyerDto')}
//           </section>
//         </div>

//         <div className="column">
//           <section className="section">
//             <h2>Seller Details</h2>
//             {renderForm(sellerDto, setSellerDto, 'sellerDto')}
//           </section>
//         </div>
//       </div>
//       {message && <div className="message">{message}</div>}
//     </div>
//   );
// };

// export default DeedDetails;


import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';
import './DeedDetails.css'; // Import the CSS file

const DeedDetails = () => {
  const { id } = useParams(); // Get the ID from the URL
  const [deedDto, setDeedDto] = useState({
    deedNo: '',
    volNo: '',
    pageNo: '',
    deedWriterName: '',
    updatedAt: '' // Added updatedAt field
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
  const [editable, setEditable] = useState({
    deedDto: false,
    buyerDto: false,
    sellerDto: false
  });

  useEffect(() => {
    const fetchDeedDetails = async () => {
      setLoading(true);
      try {
        const response = await axios.get(`http://localhost:4000/api/deed/${id}`);
        setDeedDto(response.data.deedDto);
        setBuyerDto(response.data.buyerDto);
        setSellerDto(response.data.sellerDto);
      } catch (err) {
        console.error('Error fetching data:', err);
      }
      setLoading(false);
    };

    fetchDeedDetails();
  }, [id]);

  const validate = (form) => {
    const newErrors = {};

    Object.keys(form).forEach(key => {
      if (!form[key]) newErrors[key] = 'This field is required';
    });

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleSave = async (formName) => {
    let form;
    let url;

    switch (formName) {
      case 'deedDto':
        form = deedDto;
        url = `http://localhost:4000/api/deed/?deedId=${id}`;
        break;
      case 'buyerDto':
        form = buyerDto;
        url = `http://localhost:4000/api/deed/buyer?deedId=${id}`;
        break;
      case 'sellerDto':
        form = sellerDto;
        url = `http://localhost:4000/api/deed/seller?deedId=${id}`;
        break;
      default:
        return;
    }

    if (!validate(form)) return;

    setLoading(true);
    setMessage('');
    try {
      await axios.patch(url, form);
      setLoading(false);
      setMessage('Form saved successfully');
      setEditable({ ...editable, [formName]: false });
    } catch (err) {
      console.error('Error saving data:', err);
      setLoading(false);
      setMessage('Failed to save form');
    }
  };

  const handleEdit = (formName) => {
    setEditable({ ...editable, [formName]: true });
  };

  const renderForm = (form, setForm, formName) => (
    <>
      {Object.keys(form).map((key) => (
        <div className="form-group" key={key}>
          <label>{key.split(/(?=[A-Z])/).join(' ')}:</label>
          <input
            type={key === 'updatedAt' ? 'text' : 'text'}
            value={form[key]}
            onChange={(e) => setForm({ ...form, [key]: e.target.value })}
            disabled={loading || (key === 'updatedAt') || !editable[formName]}
          />
          {errors[key] && <span className="error">{errors[key]}</span>}
        </div>
      ))}
      <div className="button-group">
        <button className="edit-button" onClick={() => handleEdit(formName)} disabled={loading}>
          Edit
        </button>
        <button className="edit-button" onClick={() => handleSave(formName)} disabled={loading || !editable[formName]}>
          {loading ? 'Saving...' : 'Save'}
        </button>
      </div>
    </>
  );

  return (
    <div className="deed-details-container">
      <h1>Deed Details</h1>
      <div className="form-columns">
        <div className="column">
          <section className="section">
            <h2>Deed Details</h2>
            {renderForm(deedDto, setDeedDto, 'deedDto')}
          </section>
        </div>

        <div className="column">
          <section className="section">
            <h2>Buyer Details</h2>
            {renderForm(buyerDto, setBuyerDto, 'buyerDto')}
          </section>
        </div>

        <div className="column">
          <section className="section">
            <h2>Seller Details</h2>
            {renderForm(sellerDto, setSellerDto, 'sellerDto')}
          </section>
        </div>
      </div>
      {message && <div className="message">{message}</div>}
    </div>
  );
};

export default DeedDetails;
