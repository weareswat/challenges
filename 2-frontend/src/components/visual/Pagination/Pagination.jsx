import React, { useState } from 'react';
import PropTypes from 'prop-types';
import { canGoBack, canGoForward } from "./paginationUtils";
import './style.scss';

const Pagination = ({
  totalItems,
  page,
  offset,
  onPageChange
}) => {
  const [currentPage, setCurrentPage] = useState(0);

  // If receiving a specific page from the parent, set that page
  if (page !== currentPage) {
    setCurrentPage(page);
  }

  const handlePageChange = jump => {
    setCurrentPage(prev => {
      if (jump === 1 && !canGoForward(prev, totalItems, offset)) {
        return prev;
      }
      else if (jump === -1 && !canGoBack(prev)) {
        return prev;
      }
      const nextPage = prev + jump;
      onPageChange(nextPage);
      return nextPage;
    });
  };

  const totalPages = totalItems > 0 ? Math.ceil(totalItems / offset) : 1;

  return (
    <div className="pagination-container">
      {
        canGoBack(currentPage) && (
          <button id="prevButton" onClick={() => handlePageChange(-1)}>Previous</button>
        )
      }
      {totalItems > 0 && 
        <span id="totalItems" className="pagination-text">{page + 1} of {totalPages}</span>
      }
      {
        canGoForward(currentPage, totalItems, offset) && (
          <button id="nextButton" onClick={() => handlePageChange(1)}>Next</button>
        )
      }
    </div>
  );
};

Pagination.propTypes = {
  /**
   * Total number of items to paginate
   */
  totalItems: PropTypes.number,
  /**
   * Current page. Starts at 0
   */
  page: PropTypes.number,
  /**
   * Number of items per page
   */
  offset: PropTypes.number,
  /**
   * Callback to notify parent component of when the page changes
   */
  onPageChange: PropTypes.func
};

Pagination.defaultProps = {
  totalItems: 0,
  page: 0,
  offset: 7,
  onPageChange: () => {}
};

export default Pagination;