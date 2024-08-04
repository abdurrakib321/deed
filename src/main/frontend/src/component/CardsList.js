

// import React from 'react';
// import Card from './Card'; // Adjust the path as necessary

// const CardsList = ({ cards }) => {
//   return (
//     <div className="cards-grid">
//       {cards.map((card, index) => (
//         <Card
//           key={index}
//           deedNo={card.deedNo}
//           volNo={card.volNo}
//           pageNo={card.pageNo}
//           buyerName={card.buyerName}
//           sellerName={card.sellerName}
//           deedWriterName={card.deedWriterName}
//         />
//       ))}
//     </div>
//   );
// };

// export default CardsList;

// import React from 'react';
// import Card from './Card'; // Adjust the path as necessary

// const CardsList = ({ cards }) => {
//   return (
//     <div className="cards-grid">
//       {cards.map((card, index) => (
//         <Card
//           key={index}
//           id={card.id}
//           deedNo={card.deedNo}
//           volNo={card.volNo}
//           pageNo={card.pageNo}
//           buyerName={card.buyerName}
//           sellerName={card.sellerName}
//           deedWriterName={card.deedWriterName}
//         />
//       ))}
//     </div>
//   );
// };

// export default CardsList;

import React from 'react';
import Card from './Card'; // Adjust the path as necessary

const CardsList = ({ cards }) => {
  return (
    <div className="cards-grid">
      {cards.map((card, index) => (
        <Card
          key={index}
          id={card.deedId}
          deedNo={card.deedNo}
          volNo={card.volNo}
          pageNo={card.pageNo}
          buyerName={card.buyerName}
          sellerName={card.sellerName}
          deedWriterName={card.deedWriterName}
        />
      ))}
    </div>
  );
};

export default CardsList;
