
// import React from 'react';
// import AppBar from '@mui/material/AppBar';
// import Toolbar from '@mui/material/Toolbar';
// import Typography from '@mui/material/Typography';
// import Button from '@mui/material/Button';

// const Navbar = () => {
//   return (
//     <AppBar position="static">
//       <Toolbar>
//         <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
//           Deed-Writer Dashboard
//         </Typography>
//         <Button 
//           color="inherit" 
//           sx={{ 
//             backgroundColor: '#FF5733', // Custom background color
//             color: '#FFFFFF', // Custom text color
//             '&:hover': {
//               backgroundColor: '#C70039' // Custom hover color
//             }
//           }}
//         >
//           Add Deed
//         </Button>
//       </Toolbar>
//     </AppBar>
//   );
// };

// export default Navbar;
import React from 'react';
import { useNavigate } from 'react-router-dom';
import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';

const Navbar = () => {
  const navigate = useNavigate();

  const handleAddDeedClick = () => {
    navigate('/deed');
  };

  return (
    <AppBar position="static">
      <Toolbar>
        <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
          Deed-Writer Dashboard
        </Typography>
        <Button 
          color="inherit" 
          onClick={handleAddDeedClick} // Navigate to /deed on click
          sx={{ 
            backgroundColor: '#FF5733', // Custom background color
            color: '#FFFFFF', // Custom text color
            '&:hover': {
              backgroundColor: '#C70039' // Custom hover color
            }
          }}
        >
          Add Deed
        </Button>
      </Toolbar>
    </AppBar>
  );
};

export default Navbar;

