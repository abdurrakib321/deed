
import React, { useState } from 'react';
import { TextField, Button, Grid, Box } from '@mui/material';
import { LocalizationProvider, DatePicker } from '@mui/x-date-pickers';
import { AdapterDateFns } from '@mui/x-date-pickers/AdapterDateFns';

const FilterComponent = ({ onSearch }) => {
  const [filters, setFilters] = useState({
    deedNo: '',
    volNo: '',
    pageNo: '',
    deedWriterName: '',
    buyerName: '',
    sellerName: '',
    createdAtStart: null,
    updatedAtStart: null
  });

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFilters({
      ...filters,
      [name]: value
    });
  };

  const handleDateChange = (name, date) => {
    setFilters({
      ...filters,
      [name]: date
    });
  };

  const handleSearch = () => {
    onSearch(filters);
  };

  return (
    <LocalizationProvider dateAdapter={AdapterDateFns}>
      <Box sx={{ backgroundColor: 'white', padding: '20px' }}>
        <Grid container spacing={2}>
          <Grid item xs={3}>
            <TextField
              variant="outlined"
              label="Deed No"
              fullWidth
              name="deedNo"
              value={filters.deedNo}
              onChange={handleInputChange}
              InputProps={{ style: { backgroundColor: '#f5f5f5', color: 'black' } }}
            />
          </Grid>
          <Grid item xs={3}>
            <TextField
              variant="outlined"
              label="Vol No"
              fullWidth
              name="volNumber"
              value={filters.volNo}
              onChange={handleInputChange}
              InputProps={{ style: { backgroundColor: '#f5f5f5', color: 'black' } }}
            />
          </Grid>
          <Grid item xs={3}>
            <TextField
              variant="outlined"
              label="Page No"
              fullWidth
              name="pageNo"
              value={filters.pageNo}
              onChange={handleInputChange}
              InputProps={{ style: { backgroundColor: '#f5f5f5', color: 'black' } }}
            />
          </Grid>
          <Grid item xs={3}>
            <TextField
              variant="outlined"
              label="Writer Name"
              fullWidth
              name="deedWriterName"
              value={filters.deedWriterName}
              onChange={handleInputChange}
              InputProps={{ style: { backgroundColor: '#f5f5f5', color: 'black' } }}
            />
          </Grid>
          <Grid item xs={3}>
            <TextField
              variant="outlined"
              label="Buyer Name"
              fullWidth
              name="buyerName"
              value={filters.buyerName}
              onChange={handleInputChange}
              InputProps={{ style: { backgroundColor: '#f5f5f5', color: 'black' } }}
            />
          </Grid>
          <Grid item xs={3}>
            <TextField
              variant="outlined"
              label="Seller Name"
              fullWidth
              name="sellerName"
              value={filters.sellerName}
              onChange={handleInputChange}
              InputProps={{ style: { backgroundColor: '#f5f5f5', color: 'black' } }}
            />
          </Grid>
          <Grid item xs={2}>
            <DatePicker
              label="Start Date"
              value={filters.createdAtStart}
              onChange={(date) => handleDateChange('createdAtStart', date)}
              renderInput={(params) => (
                <TextField
                  {...params}
                  variant="outlined"
                  fullWidth
                  InputProps={{ style: { backgroundColor: '#e0e0e0', color: 'black' } }}
                />
              )}
            />
          </Grid>
          <Grid item xs={2}>
            <DatePicker
              label="End Date"
              value={filters.updatedAtStart}
              onChange={(date) => handleDateChange('updatedAtStart', date)}
              renderInput={(params) => (
                <TextField
                  {...params}
                  variant="outlined"
                  fullWidth
                  InputProps={{ style: { backgroundColor: '#e0e0e0', color: 'black' } }}
                />
              )}
            />
          </Grid>
          <Grid item xs={2} style={{ textAlign: 'right' }}>
            <Button
              variant="contained"
              color="primary"
              style={{ backgroundColor: '#2C6EBE', color: 'white', fontWeight: 'bold', marginTop: '8px' }}
              onClick={handleSearch}
            >
              Search
            </Button>
          </Grid>
        </Grid>
      </Box>
    </LocalizationProvider>
  );
};

export default FilterComponent;
