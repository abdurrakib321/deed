

// import React from 'react';
// import './App.css';
// import Navbar from './component/Navbar'; // Adjust the path if necessary
// import Login from './component/Login';
// import Deed from './component/Deed';
// import FilterComponent from './component/FilterComponent';
// import Card from './component/Card';
// import DeedDetails from './component/DeedDetails';
// import CardFetcher from './component/CardFetcher';
// import { BrowserRouter as Router, Route, Routes, useLocation } from 'react-router-dom';

// const App = () => {
//   return (
//     <Router>
//       <AppContent />
//     </Router>
//   );
// };

// const AppContent = () => {
//   const location = useLocation();
//   const hideNavbarPaths = ['/deed', '/another-path-to-hide-navbar']; // Add any paths where Navbar should be hidden

//   const shouldHideNavbar = hideNavbarPaths.includes(location.pathname);

//   return (
//     <div className="App">
//       {!shouldHideNavbar && <Navbar />}
//       <Routes>
//         <Route path="/" element={<Home />} />
//         <Route path="/deed" element={<Deed />} />
//         <Route path="/deed/:id" element={<DeedDetails />} />
//       </Routes>
//     </div>
//   );
// };

// const Home = () => (
//   <>
//     <CardFetcher />
//   </>
// );

// export default App;

import React from 'react';
import './App.css';
import Navbar from './component/Navbar'; // Adjust the path if necessary
import Deed from './component/Deed';
import DeedDetails from './component/DeedDetails';
import CardFetcher from './component/CardFetcher';
import { BrowserRouter as Router, Route, Routes, useLocation } from 'react-router-dom';

const App = () => {
  return (
    <Router>
      <AppContent />
    </Router>
  );
};

const AppContent = () => {
  const location = useLocation();

  // Regular expression to match paths like '/deed/:id'
  const deedIdPattern = /^\/deed\/[a-f\d]{24}$/i;
  const hideNavbarPaths = ['/deed', '/another-path-to-hide-navbar'];

  const shouldHideNavbar = hideNavbarPaths.includes(location.pathname) || deedIdPattern.test(location.pathname);

  return (
    <div className="App">
      {!shouldHideNavbar && <Navbar />}
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/deed" element={<Deed />} />
        <Route path="/deed/:id" element={<DeedDetails />} />
      </Routes>
    </div>
  );
};

const Home = () => (
  <>
    <CardFetcher />
  </>
);

export default App;
