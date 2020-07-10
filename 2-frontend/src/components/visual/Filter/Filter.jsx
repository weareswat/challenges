import React, { useState, useEffect } from "react";
import PropTypes from "prop-types";
import Select from "react-select";
import "./style.scss";

const Filter = ({
  options,
  onChangeFilter
}) => {
  const [filterBy, setFilterBy] = useState({
    label: "Filter by",
    value: null
  });
  const [filterVal, setFilterVal] = useState("");

  const handleChangeFilterBy = selected => {
    setFilterBy(selected);
  };

  const handleChangeFilterVal = ev => {
    setFilterVal(ev.target.value);
  };

  useEffect(() => {
    onChangeFilter(filterBy.value, filterVal);
  }, [filterBy, filterVal]);

  return (
    <div className="filter-container">
      <Select
        className="filter-select"
        value={filterBy}
        onChange={handleChangeFilterBy}
        options={options}
      />
      <input 
        className="filter-input"
        placeholder="Search for..."
        onChange={handleChangeFilterVal} 
      />
    </div>
  );
};

PropTypes.propTypes = {
  /**
   * Specify the options the user can select from
   */
  options: PropTypes.arrayOf(
    PropTypes.shape({
      label: PropTypes.string,
      value: PropTypes.string
    })
  ).isRequired,
  /**
   * Callback to notify parent component of when the filter changes
   */
  onFilterChange: PropTypes.func
};

PropTypes.defaultProps = {
  onFilterChange: () => {}
};

export default Filter;